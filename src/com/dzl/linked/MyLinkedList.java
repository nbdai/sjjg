package com.dzl.linked;

import java.util.LinkedList;

/**
 * 双向链表
 */
public class MyLinkedList {
    private LinkedNode root;
    private LinkedNode first;
    private LinkedNode last;
    private Integer size;
    private class LinkedNode{
        private Object value;
        private LinkedNode pre;
        private LinkedNode next;
        public LinkedNode(){

        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public LinkedNode getPre() {
            return pre;
        }

        public void setPre(LinkedNode pre) {
            this.pre = pre;
        }

        public LinkedNode getNext() {
            return next;
        }

        public void setNext(LinkedNode next) {
            this.next = next;
        }
    }
    public MyLinkedList(){
        size = 0;
        first = root;
        last = root;
        root = null;
    }
    //插入
    public boolean add(Object obj){
        if(root==null){
            root = new LinkedNode();
            root.value = obj;
            first = root;
            last = root;
            size++;
            return  true;
        }
              LinkedNode tempNode = new LinkedNode();
              tempNode.value = obj;
              last.next = tempNode;
              tempNode.pre = last;
              last = tempNode;
              size++;
              return true;
    }
    public Object set(int index,Object obj){

        if(index>size){
            return null;
        }
        LinkedNode linkedNode = new LinkedNode();
        linkedNode.value = obj;
        LinkedNode temp = root;
        for(int i = 0;i<index-1;i++){
            temp = temp.next;
        }
          if(temp==last){
             last.next = linkedNode;
             linkedNode.pre = last;
             last = linkedNode;
             size++;
             return obj;

          }
          temp.next.pre = linkedNode;
          linkedNode.next = temp.next;
          linkedNode.pre = temp;
          temp.next = linkedNode;
          size++;
          return obj;
    }
    public Object get(int index){
        if(index>=size)
            return null;
        LinkedNode linkedNode = root;
        for(int i = 0;i<index;i++){
           linkedNode = linkedNode.next;
        }
          if(linkedNode!=null){
            return linkedNode.value;
          }
          return  null;
    }
    public boolean remove(Object obj){
       LinkedNode linkedNode = root;
       while (linkedNode!=null){
           if(linkedNode.value.equals(obj)){
               if(linkedNode==last){
                   last = linkedNode.pre;
                   last.next = null;
               }else {
                   linkedNode.pre.next = linkedNode.next;
                   linkedNode.next.pre = linkedNode.pre;
               }
               size--;
               return true;
           }else {
               linkedNode = linkedNode.next;
           }
       }
       return  false;
    }
    public void print(){
        LinkedNode tempNode = root;
        String str = "";
        while (tempNode!=null){
            str+=tempNode.value+" ";
            tempNode = tempNode.next;
        }
        System.out.println(str);
    }
    public static void main(String[] args){
        MyLinkedList myLinkedList = new MyLinkedList();
        for(int i=0;i<16;i++){
            myLinkedList.add(i+"");
        }
        myLinkedList.set(16,"dai");
        myLinkedList.remove("dai");
        myLinkedList.print();
    }
}
