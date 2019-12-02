package com.bdoemu.commons.collection;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName ListSplitter
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/15 16:40
 * VERSION 1.0
 */

public class ListSplitter<T> {
    private T[] objects;
    private Class<?> componentType;
    private int splitCount;
    private int currentIndex = 0;
    private int length = 0;


    public ListSplitter(Collection<T> collection, int splitCount) {
        if (collection != null) {
            this.splitCount = splitCount;
            this.length = collection.size();
            this.objects = collection.toArray((T[])new Object[this.length]);
            this.componentType = this.objects.getClass().getComponentType();
        }
    }

    public List<T> getNext(int splitCount) {
        this.splitCount = splitCount;
        return getNext();
    }


    public List<T> getNext() {
        T[] subArray = (T[])(Object[]) Array.newInstance(this.componentType, Math.min(this.splitCount, this.length - this.currentIndex));
        if (subArray.length > 0) {
            System.arraycopy(this.objects, this.currentIndex, subArray, 0, subArray.length);
            this.currentIndex += subArray.length;
        }
        return Arrays.asList(subArray);
    }


    public int size() { return this.length; }



    public boolean isFirst() {
        return (this.currentIndex <= this.splitCount);
    }



    public boolean isLast() {
        return (this.currentIndex == this.length);
    }



    public boolean hasNext() {
        return (this.currentIndex < this.length);
    }
}
