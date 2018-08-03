package com.dzl.array;

import javax.jws.Oneway;
import java.util.ArrayList;

/**
 * @author DZL
 * @desc 数组实现arraylist
 */
public class MyArrayList<T> {
    private Object[] elementData;
    private Integer size;

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int initCapacity) {
        if (initCapacity < 0) {
            try {
                throw new Exception("数组长度不能小于0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        elementData = new Object[initCapacity];
        this.size = 0;
    }

    //插入增加数据add set
    public boolean add(Object obj) {
        if (null == obj) {
            return false;
        }
        //数组扩容
        addCapacity();
        elementData[size++] = obj;
        return true;
    }

    public T set(Object obj, int index) {
        if (index < 0 || index > size) {
            return null;
        }
        //扩容
        addCapacity();
        // 0 1 2 ....  -> 3 4 6 ---- 3 5 4 6 从index整体向后移动.
        for (int i = size - 1; i >= index; i--) {
            elementData[i + 1] = elementData[i];
        }
        elementData[index] = obj;
        size++;
        return (T) obj;
    }

    //遍历数组indexOf get
    public int indexOf(Object obj) {
        if (size == 0) {
            return -1;
        }
        if (obj == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (obj.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public T get(int index) {
        //
        if (size == 0 || index < 0 || index >= size) {
            return null;
        }
        return (T) elementData[index];
    }

    //remove
    public boolean remove(Object obj) {
        if (null == obj) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (obj.equals(elementData[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public String printArray() {
        return null;
    }

    //删除当前元素。
    public void remove(int index) {
        if (index < 0 || index >= size) {
            try {
                throw new Exception("下标越界");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = index; i <=size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[--size] = null;
    }

    //print
    public void print() {
        String str = "";
        for (int i = 0; i < size; i++) {
            if (i == size - 1)
                str += elementData[i] + ".";
            else
                str += elementData[i] + ",";
        }
        System.out.println(str);
    }

    //数组扩容;
    private void addCapacity() {
        if (size >= elementData.length * 0.9) {
            Object[] tempData = new Object[size];
            for (int i = 0; i < size; i++) {
                tempData[i] = elementData[i];
            }
            elementData = new Object[size * 2 + 1];
            for (int i = 0; i < size; i++) {
                elementData[i] = tempData[i];
            }
        }
    }

    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        for (int i = 0; i < 16; i++) {
            myArrayList.add(i + "");
        }
        myArrayList.set("dai",10);
        myArrayList.remove("dai");
/*        System.out.println(myArrayList.indexOf("dai"));*/
         myArrayList.print();
    }
}
