package com.demo.datastructure.stack;

/**
 * 线性表
 * Array 数组
 * List 列表,清单
 */
public class MyArrayList {
    private Object[] data;
    private int capacity;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private MyIterator myIterator;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
    }

    public Object add(Object element) {
        int currentSize = size;//使用临时变量
        Object[] currentData = data;
        //增加元素之前判断元素个数是否超过数组容量
        if (overCapacity(1)) {
            //扩容
            expandCapacity(capacity,1);
            add(element);
        } else {
            currentData[currentSize] = element;
            currentSize++;
            System.arraycopy(currentData, 0, data, 0, currentSize);
            size = currentSize;
        }
        return element;
    }

    public void add(Object[] elements) {
        if ((elements == null) || (elements.length == 0)) {
            return;
        }
        if (overCapacity(elements.length)) {
            expandCapacity(capacity,elements.length);
            add(elements);
        } else {
            int currentSize = size;//使用临时变量
            Object[] currentData = data;
            for (Object obj : elements) {
                currentData[currentSize] = obj;
                currentSize++;
            }
            System.arraycopy(currentData, 0, data, 0, currentSize);
            size = currentSize;
        }
    }

    /**
     * 扩容
     */
    private void expandCapacity(int currentCapacity, int elementsSize) {
        int currentSize = size;
        int expandCapacity = currentCapacity + DEFAULT_CAPACITY;
        //扩容因子取默认容量DEFAULT_CAPACITY
//        int expandFactor = DEFAULT_CAPACITY >> 1;
        int afterAddSize = currentSize+ elementsSize;
        if (afterAddSize > expandCapacity) {
            expandCapacity(expandCapacity,elementsSize);
        } else {
            Object[] currentData = data;
            Object[] addData = new Object[afterAddSize];
            System.arraycopy(currentData, 0, addData, 0, currentSize);
            data = addData;
            capacity = expandCapacity;
        }
    }

    private boolean overCapacity(int elementsSize) {
        return size + elementsSize > capacity;
    }

    public Object remove(int index) {
        return null;
    }

    public Object set(int index, Object element) {
        return null;
    }

    public Object get(int index) {
        return null;
    }

    public int size() {
        return size;
    }

    public MyIterator iterator() {
        return myIterator;
    }

    public static class MyIterator {

    }

    private abstract class MyAbstractIterator {
        abstract boolean hasNext();

        abstract Object next();
    }
}
