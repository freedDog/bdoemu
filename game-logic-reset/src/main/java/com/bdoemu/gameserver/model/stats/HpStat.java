package com.bdoemu.gameserver.model.stats;

import com.bdoemu.commons.concurrent.CloseableReentrantLock;
import com.bdoemu.commons.thread.ThreadPool;
import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.stats.elements.Element;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName HpStat
 * @Description 血量状态
 * @Author JiangBangMing
 * @Date 2019/7/6 10:46
 * VERSION 1.0
 */

public class HpStat extends Stat{
    /**治愈缓存数*/
    private long healCacheCount;
    /**hp 回复任务*/
    private ScheduledFuture<?> hpRegenTask;

    public HpStat(final Creature owner) {
        super(owner);
        this.healCacheCount = 0L;
    }

    public void startHpRegenTask() {
        if (this.owner.isDead() || (this.hpRegenTask != null && !this.hpRegenTask.isDone()) || this.owner.getGameStats().getHPRegen().getIntMaxValue() <= 0) {
            return;
        }
        this.hpRegenTask = ThreadPool.getInstance().scheduleEffectAtFixedRate(() -> {
            if (this.owner.isDead()) {
                this.stopHpRegenTask();
            } else {
                this.addHP(this.owner.getGameStats().getHPRegen().getIntMaxValue(), null);
            }
        }, 10L, 10L, TimeUnit.SECONDS);
    }

    public void stopHpRegenTask() {
        if (this.hpRegenTask != null) {
            this.hpRegenTask.cancel(true);
        }
    }

    @Override
    public boolean removeElement(final Element element) {
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            this.maxValue -= element.getValue();
            if (this.value > this.maxValue) {
                this.value = this.maxValue;
            }
            return this.elements.remove(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public double fill() {
        double diff=0;
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            diff = this.getMaxHp() - this.getCurrentHp();
            this.value = this.maxValue;
            this.broadcastHPPackets(this.owner, (int) diff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diff;
    }

    @Override
    public void fill(int percentage) {
        percentage = Math.min(percentage, 100);
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            this.value = this.getMaxValue() * percentage / 100.0;
            if (this.value == this.getMaxValue()) {
                this.stopHpRegenTask();
            } else {
                this.startHpRegenTask();
            }
            this.broadcastHPPackets(null, 0);
            ++this.healCacheCount;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean kill(final Creature attacker) {
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            if (this.getCurrentHp() <= 0.0f) {
                return false;
            }
            this.value = 0.0;
            ++this.healCacheCount;
            if (!this.owner.getAi().HandleDead(attacker, null).isChangeState()) {
                this.owner.onDie(attacker, 2544805566L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public int getHpPercentage() {
        return (int) (this.value / this.maxValue * 100.0);
    }

    public long getHealCacheCount() {
        return this.healCacheCount;
    }

    public boolean isDead() {
        return this.value == 0.0;
    }

    public float getCurrentHp() {
        return (float) this.value;
    }

    public void setCurrentHp(final double currentHp) {
        this.value = currentHp;
    }

    public float getMaxHp() {
        return (float) this.maxValue;
    }

    public boolean addHP(final double addHp, final Creature applyOwner) {
        final float hpDiff = this.owner.getGameStats().getHp().updateHp(addHp, applyOwner);
        return hpDiff != 0.0f;
    }

    public float updateHp(final double hp, final Creature applyOwner) {
        return this.updateHp(hp, applyOwner, false);
    }

    public float updateHp(final double hp, final Creature applyOwner, final boolean isDoPhysicalAttack) {
        if (this.owner.getAi() != null && this.owner.getAi().getBehavior().isReturn()) {
            return 0.0f;
        }
        double hpDiff = hp;
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            if (this.owner.isDead()) {
                return 0.0f;
            }
            final double newValue = hpDiff + this.value;
            if (newValue > this.maxValue) {
                hpDiff = this.maxValue - this.value;
            } else if (newValue < 0.0) {
                hpDiff = -this.value;
            }
            ++this.healCacheCount;
            this.value += hpDiff;
            if (this.value != 0.0) {
                if (this.value == this.getMaxValue()) {
                    this.stopHpRegenTask();
                } else {
                    this.startHpRegenTask();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (hpDiff != 0.0 && !isDoPhysicalAttack) {
            this.broadcastHPPackets(applyOwner, (int) hpDiff);
        }
        if (applyOwner != null && this.owner != applyOwner && hpDiff < 0.0) {
            this.owner.getAggroList().addDmg(applyOwner, Math.abs(hp)); // used to be -hpDiff
            if (this.owner.getAi() != null) {
                this.owner.getAi().HandleTakeDamage(applyOwner, null);
//                if (this.owner.getParty() != null) {
//                    final IParty<Creature> party = (IParty<Creature>) this.owner.getParty();
//                    party.getMembers(this.owner).stream().filter(member -> member.getAi() != null).forEach(member -> {
//                        member.getAggroList().addCreature(applyOwner);
//                        member.getAi().HandleTakeTeamDamage(applyOwner, null);
//                    });
//                }
            }
        }
        if (this.value <= 0.0 && this.owner.getAi() != null && !this.owner.getAi().HandleDead(applyOwner, null).isChangeState()) {
            this.owner.onDie(applyOwner, 2544805566L);
        }
        return (float) hpDiff;
    }

    /**
     * 广播血量变化消息
     * @param applyOwner
     * @param hpDiff
     */
    private void broadcastHPPackets(final Creature applyOwner, final int hpDiff) {
//        this.owner.sendBroadcastItSelfPacket(new SMSetCharacterPublicPoints(this.owner, (applyOwner == null) ? -1024 : applyOwner.getGameObjectId(), hpDiff));
//        if (this.owner.isVehicle() && this.owner.isSpawned()) {
//            final Player masterOwner = (Player) this.owner.getOwner();
//            if (masterOwner != null && masterOwner.isPlayer()) {
//                masterOwner.sendPacket(new SMSetMyServantPoints(masterOwner));
//            }
//        }
    }
}
