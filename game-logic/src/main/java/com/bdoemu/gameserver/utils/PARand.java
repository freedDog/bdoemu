package com.bdoemu.gameserver.utils;

import org.apache.commons.math3.random.MersenneTwister;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 * @author H1X4
 */
public class PARand {
    private static final Random SEEDER = new SecureRandom();
    private static final ThreadLocal<MersenneTwister> LOCAL_RANDOM = ThreadLocal.withInitial(() -> {
        synchronized (SEEDER) {
            return new MersenneTwister(SEEDER.nextLong());
        }
    });

    private static double randExc(double n) {
        return LOCAL_RANDOM.get().nextDouble() * n;
    }

    private static double randChance() {
        return randExc(100.0);
    }

    public static boolean PARollChance(int chance) {
        return chance > randExc(1_000_000);
    }

    public static boolean PARollChance(double chance) {
        return chance > randExc(1_000_000);
    }

    public static <E> E PARollList(List<E> list) {
        return list.get((int) randChance() % list.size());
    }
}
