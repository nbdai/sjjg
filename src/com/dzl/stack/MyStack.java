package com.dzl.stack;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author DZL
 * @desc 数组实现的栈。
 *    Stack stack = new Stack();
 */
public class MyStack<T>{
    private Object[] elementData;
    private Integer size;
    public MyStack(){
        size = 0;
        elementData = new Object[10];
    }
    //push
    public T push(Object obj){
        addCapacity();
        elementData[size++] = obj;
        return (T)obj;
    }
    //pop
    public T pop(){
       if(size==0){
           return null;
       }
          return(T)elementData[(size--)-1];
    }
    //peek
    public T peek(){
        if (size==0){
            return null;
        }
           return (T)elementData[size-1];

    }
     public Iterator<T> iterator(){
        return new Iterator<T>() {
            private int count = size;
            @Override
            public boolean hasNext() {
                return count>0;
            }

            @Override
            public T next() {
               return (T)elementData[--count];
            }
        };
     }
    private void addCapacity(){
        if(size >= elementData.length*0.9){
            Object[] temp = new Object[size];
            for(int i = 0;i<size;i++){
                temp[i] = elementData[i];
            }
                elementData = new Object[2*size+1];
            for(int i = 0;i<size;i++){
                elementData[i] = temp[i];
            }
        }
    }

    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<>();
        for(int i = 0;i<18;i++){
            myStack.push(i+"");
        }
        System.out.println(myStack.pop());
        Iterator<String> iterator = myStack.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
