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
        myIterator = new MyIterator();
    }

    public Object add(Object element) {
        int currentSize = size;//使用临时变量
        Object[] currentData = data;
//        System.out.println("add,currentSize = " + currentSize + ",capacity = " + capacity);
        //增加元素之前判断元素个数是否超过数组容量
        if (overCapacity(currentSize, 1)) {
            //扩容
            expandCapacity(capacity, 1);
            add(element);
        } else {
            currentData[currentSize] = element;
            currentSize++;
            System.arraycopy(currentData, 0, data, 0, currentSize);
            size = currentSize;
        }
        return element;
    }

    public void add(MyArrayList list) {
        if ((list == null) || (list.size() == 0)) {
            return;
        }
        Object[] elements = list.iterator().values();
        if (elements.length == 0) {
            return;
        }
        int currentSize = size;//使用临时变量
        if (overCapacity(currentSize, elements.length)) {
            expandCapacity(capacity, elements.length);
            add(elements);
        } else {
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
        //TODO 扩容因子的选择策略可以优化
        //扩容因子直接选择了默认容量DEFAULT_CAPACITY
//        int expandFactor = DEFAULT_CAPACITY >> 1;
        int afterAddSize = currentSize + elementsSize;
        if (afterAddSize > expandCapacity) {
            expandCapacity(expandCapacity, elementsSize);
        } else {
            Object[] currentData = data;
            Object[] addData = new Object[expandCapacity];
            System.arraycopy(currentData, 0, addData, 0, currentSize);
            data = addData;
            capacity = expandCapacity;
            System.out.println("扩容后大小：" + capacity);
        }
    }

    private boolean overCapacity(int currentSize, int elementsSize) {
        return currentSize + elementsSize > capacity;
    }

    public Object remove(int index) {
        return null;
    }

    public Object set(int index, Object element) {
        return null;
    }

    public Object get(int index) {
        if (index < 0) {
            throw new IllegalStateException("index is illegal,index = " + index + ",is");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("index is " + index + ",size = " + size);
        } else {
            Object[] currentData = data;
            return currentData[index];
        }
    }

    public int size() {
        return size;
    }

    public MyIterator iterator() {
        return myIterator;
    }

    public class MyIterator {
        private Object[] values() {
            Object[] currentData = data;
            int currentSize = size;
            Object[] temp = new Object[currentSize];
            System.arraycopy(currentData, 0, temp, 0, currentSize);
            return temp;
        }

        private Object getValue(int index) {
            return get(index);
        }
    }


    private abstract class MyAbstractIterator {
        abstract boolean hasNext();

        abstract Object next();
    }


    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        for (int i = 0; i < 12; i++) {
            list.add(i + 1);
        }
        MyArrayList list2 = new MyArrayList(3);
        list2.add(13);
        list2.add(14);
        list2.add(15);
        list.add(list2);
        System.out.println("list size = " + list.size());

        MyIterator iterator = list.iterator();
        for (Object obj : iterator.values()) {
            System.out.println("obj = " + obj);
        }


    }
}
