package com.bdoemu.commons.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName Rnd
 * @Description 随机
 * @Author JiangBangMing
 * @Date 2019/6/24 15:13
 * VERSION 1.0
 */

public class Rnd {
    public static double get() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public static int get(final int n) {
        return ThreadLocalRandom.current().nextInt(Math.abs(n));
    }

    public static long get(final long n) {
        return (long)(ThreadLocalRandom.current().nextDouble() * n);
    }

    public static double get(final double n) {
        return ThreadLocalRandom.current().nextDouble() * n;
    }

    public static int get(final int min, final int max) {
        return min + get(max - min + 1);
    }

    public static long get(final long min, final long max) {
        return min + get(max - min + 1L);
    }

    public static double get(final double min, final double max) {
        return min + get(max - min + 1.0);
    }

    public static int nextInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static double nextDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public static double nextGaussian() {
        return ThreadLocalRandom.current().nextGaussian();
    }

    public static boolean nextBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static byte[] nextBytes(final byte[] bytes) {
        ThreadLocalRandom.current().nextBytes(bytes);
        return bytes;
    }

    public static boolean getChance(final int chance) {
        return chance >= 1 && (chance > 99 || ThreadLocalRandom.current().nextInt(99) + 1 <= chance);
    }

    public static boolean getChance(final double chance) {
        return ThreadLocalRandom.current().nextDouble() * 100.0 <= chance;
    }

    public static boolean getChance(final int chance, final int divider) {
        return getChance(chance / (double)divider);
    }

    public static <E> E get(final E[] list) {
        return list[get(list.length)];
    }

    public static int get(final int[] list) {
        return list[get(list.length)];
    }

    public static <E> E get(final List<E> list) {
        return list.get(get(list.size()));
    }
}
