package com.bdoemu.gameserver.model.stats;

import com.bdoemu.commons.concurrent.CloseableReentrantLock;
import com.bdoemu.gameserver.model.creature.Creature;

/**
 * @ClassName AdrenalinStat
 * @Description  肾上腺素状态
 * @Author JiangBangMing
 * @Date 2019/7/9 20:20
 * VERSION 1.0
 */

public class AdrenalinStat extends Stat{

    public AdrenalinStat(final Creature owner) {
        super(owner);
    }

    public boolean addAdrenalin(int adrenalin, final boolean useCap) {
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            if (adrenalin > 0 && this.value >= (useCap ? this.maxValue : 100.0)) {
                return false;
            }
            final double newValue = adrenalin + this.value;
            if (newValue < 0.0) {
                return false;
            }
            if (newValue > this.maxValue) {
                adrenalin = (int) (this.maxValue - this.value);
            }
//            this.owner.sendPacket(new SMVaryAdrenalin(adrenalin));
            this.value += adrenalin;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
