package com.bdoemu.gameserver.model.stats;

import com.bdoemu.commons.concurrent.CloseableReentrantLock;
import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName SubResourcePointStat
 * @Description 子资源点状态
 * @Author JiangBangMing
 * @Date 2019/7/9 20:26
 * VERSION 1.0
 */

public class SubResourcePointStat extends Stat{

    private long subResourcePointCacheCount;

    public SubResourcePointStat(final Creature owner) {
        super(owner);
    }

    public boolean addSubResourcePoints(final int points) {
        double pointsDiff = points;
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            final double newValue = pointsDiff + this.value;
            if (newValue > this.maxValue) {
                pointsDiff = this.maxValue - this.value;
            } else if (newValue < 0.0) {
                pointsDiff = -this.value;
            }
            this.value += pointsDiff;
            if (this.owner.isPlayer()) {
//                this.owner.sendPacket(new SMSetSubResourcePoints(this.owner));
            } else {
                this.owner.getAi().HandleUpdateCombineWave(null, null);
            }
            ++this.subResourcePointCacheCount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public long getSubResourcePointCacheCount() {
        return this.subResourcePointCacheCount;
    }
}
