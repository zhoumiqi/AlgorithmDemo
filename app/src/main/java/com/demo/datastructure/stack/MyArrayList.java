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
    private static final Object[] EMPTY_DATA = new Object[]{};

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

    public Object set(int index, Object element) {
        if (index < 0) {
            throw new IllegalStateException("index is illegal,index = " + index + ",is");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("index is " + index + ",size = " + size);
        }
        int currentSize = size;
        Object[] currentData = data;
        Object[] newData = new Object[currentSize];
        System.arraycopy(currentData, 0, newData, 0, index);
        newData[index] = element;
        System.arraycopy(currentData, index + 1, newData, index + 1, currentSize - index - 1);
        data = newData;
        return element;
    }

    /**
     * 自定义在指定位置添加集合，且原有的元素不被删除而是顺序后移
     *
     * @param index
     * @param list
     */
    public void set(int index, MyArrayList list) {
        int currentSize = size;
        Object[] currentData = data;
        int currentCapacity = capacity;
        int expandCapacitySize = list.size();
        println("当前集合长度:" + currentData.length + ",size = " + currentSize);
        for (Object obj : currentData) {
            println("set 集合之前 : " + obj + " ");
        }
        if (overCapacity(currentSize, expandCapacitySize)) {
            expandCapacity(currentCapacity, expandCapacitySize);
            set(index, list);
        } else {
            //TODO 优化不用三次集合拷贝
            int newSize = currentSize + expandCapacitySize;
            Object[] newData = new Object[newSize];
            //1、复制0-index之前的数据到新集合
            System.arraycopy(currentData, 0, newData, 0, index);
            //2、复制index位置新增的集合到新集合
            Object[] values = list.iterator().values();
            System.arraycopy(values, 0, newData, index, expandCapacitySize);
            //3、复制原集合index位置到末尾的数据到新集合
            int nextDataSize = currentSize - index;
            System.arraycopy(currentData, index, newData, index+expandCapacitySize, nextDataSize);
            data = newData;
            size = newSize;
        }
    }

    public void print(String msg) {
        System.out.print(msg);
    }

    public void println(String msg) {
        System.out.println(msg);
    }

    public Object remove(int index) {
        if ((index >= size) || (index < 0)) {
            throw new IllegalArgumentException("index is illegal,index = " + index + " size = " + size);
        }
        int currentSize = size;
        //获取index后的数组
        Object[] currentData = data;
        Object removeData = currentData[index];
        if (currentSize == 1) {
            currentData[0] = null;
            currentSize--;
            data = EMPTY_DATA;
        } else {
            int afterSize = currentSize - index - 1;
            for (int i = 0; i < afterSize; i++) {
                currentData[index + i] = currentData[index + i + 1];
            }
            currentSize--;
            //原来集合中的最后一个元素是否一定要置空
            currentData[currentSize] = null;
            Object[] newData = new Object[currentSize];
            System.arraycopy(currentData, 0, newData, 0, currentSize);
            data = newData;
        }
        size = currentSize;
        return removeData;
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
            //当前data大小为capacity，超过size大小未赋值的数组元素默认为null
            Object[] currentData = data;
            int currentSize = size;
            //大小为当前size，而不是取的capacity
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
        //迭代方式一
//        for (Object obj : iterator.values()) {
//            System.out.println("obj = " + obj);
//        }
        //迭代方式二
        for (int i = 0; i < list.size(); i++) {
            Object value = iterator.getValue(i);
            System.out.println("obj = " + value);
        }
        list.set(3, "test method set(index,element)");
        for (int i = 0; i < list.size(); i++) {
            Object value = iterator.getValue(i);
            System.out.println("修改后：obj = " + value);
        }

        System.out.println("调用remove,当前size= " + list.size());
        for (int i = 0; i < list.size(); i++) {
            Object value = iterator.getValue(i);
            System.out.println("当前：obj = " + value);
        }
        list.remove(7);
        for (int i = 0; i < list.size(); i++) {
            Object value = iterator.getValue(i);
            System.out.println("remove之后：obj = " + value);
        }
        System.out.println("调用remove后,size= " + list.size());
        MyArrayList addList = new MyArrayList();
        addList.add("add1");
        addList.add("add2");
        addList.add("add3");

        list.set(5, addList);
        System.out.println("调用set 集合,当前size= " + list.size());
        for (int i = 0; i < list.size(); i++) {
            Object value = iterator.getValue(i);
            System.out.println("set集合后：obj = " + value);
        }
        System.out.println("set集合后,size= " + list.size());

    }

}
