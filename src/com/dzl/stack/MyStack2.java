package com.dzl.stack;

import java.util.Iterator;

/**
 * @author DZL
 * @desc 链表实现stack
 */
public class MyStack2<T>{
    private int size;
    private Node top;
    public class Node{
        Object value;
        Node next;
    }
    public MyStack2(){
        size = 0;
        top = null;
    }
    public T push(Object obj){
        if(size == 0){
            top = new Node();
            top.value = obj;
            size++;
            return (T)obj;
        }
            Node newNode = new Node();
            newNode.value = obj;
            newNode.next = top;
            top = newNode;
            size++;
            return (T)newNode;
    }
    public T pop(){
        if(size==0){
            return null;
        }
        T temp = (T)top.value;
        top = top.next;
        size--;
        return temp;
    }
    public T peek(){
        if(size==0){
            return null;
        }
        T temp = (T)top.value;
        return temp;
    }
   public Iterator<T> iterator(){
        return new Iterator<T>() {
            Node n = top;
            @Override
            public boolean hasNext() {
                return n!=null;
            }

            @Override
            public T next() {
                T temp = (T) n.value;
                n = n.next;
                return temp;
            }
        };
   }

    public static void main(String[] args) {
        MyStack2<String> myStack2 = new MyStack2<>();
        for(int i = 0;i<10;i++){
            myStack2.push(i+"");
        }
        System.out.println(myStack2.pop());
        Iterator<String> iterator = myStack2.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
