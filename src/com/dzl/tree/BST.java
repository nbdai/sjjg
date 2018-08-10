package com.dzl.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @param <E>
 * @author DZL
 * @DESC 二叉搜索树。
 */
public class BST<E extends Comparable<E>> {
    private Integer size;
    private Node root;

    private class Node {
        private E e;
        private Node left;
        private Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    public BST() {
        root = null;
        size = 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        //为空说明找到结尾了，直接添加节点即可。
        if (node == null) {
            node = new Node(e);
            size++;
            return node;
        }
        //负责和当前节点的值比较。如果比其小说明应该插入左子树。
        else if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        //如果为空，说明找到最后都没有找到，那么就应该为false，
        if (node == null) {
            return false;
        } else if (e.compareTo(node.e) < 0) {
            //负责比较node的值，如果比它小，就应该从左子树中寻找。
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        }
        //相等就说明找到了
        return true;
    }

    public void preOrder() {
        //递归前序遍历。
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrderNR() {
        //非递归遍历.需要手动模拟入栈 出栈过程，
        if (size == 0) {
            try {
                throw new Exception("BST IS EMPTY!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.e);
            //栈先进后出，所以我们应该先将root的右节点入栈。
            //如果为空我们就不入栈。
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }

    public void levelOrder() {
        //层序遍历，不应该用递归，因为我们要模拟一个队列，而递归原理是栈。
        if (size == 0) {
            try {
                throw new Exception("BST IS EMPTY!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.e);
            //先进先出 ，所以应该先push left，
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }
    public E findMin(){
        if(size == 0){
            try {
                throw new Exception("BST IS EMPTY!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Node node = findMin(root);
        return node.e;
    }
    private Node findMin(Node node){
        if(node.left==null){
            return node;
        }
        return  findMin(node.left);
    }
    public E findMax(){
        if(size == 0){
            try {
                throw new Exception("BST IS EMPTY!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Node node = findMax(root);
        return node.e;
    }
    private Node findMax(Node node){
        if(node.right ==null){
            return node;
        }
        return  findMax(node.right);
    }
    public E  removeMin(){
        if(size==0){
            try {
                throw new Exception("BST IS EMPTY!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       E e = findMin();
       root = removeMin(root);
       return  e;
    }
    private Node  removeMin(Node node){
        if(node.left==null){
            //说明此刻就是最小值。
            Node tempRight = node .right;
            node.right = null;
            size --;
            return tempRight;
        }
          //否则就应该继续递归下去，并重新赋一下改变过的左子树。
         node.left = removeMin(node.left);
         return node;
    }

    public E  removeMax(){
        if(size==0){
            try {
                throw new Exception("BST IS EMPTY!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        E e = findMax();
        root = removeMax(root);
        return  e;
    }
    private Node  removeMax(Node node){
        if(node.right==null){
            //说明此刻就是最大值。
            Node tempLeft= node.left;
            node.left = null;
            size --;
            return tempLeft;
        }
        //否则就应该继续递归下去，并重新赋一下改变过的右子树。
        node.right = removeMax(node.right);
        return node;
    }
    public void remove(E e){
      root = remove(root,e);
    }
    private Node remove(Node node,E e){
        if(node == null)
            return null;
       if(e.compareTo(node.e)==0){
           if(node.left==null){
               Node temp = node.right;
               node.right = null;
               size--;
               return temp;
           }
           if(node.right==null){
               Node temp = node.left;
               node.left = null;
               size--;
               return temp;
           }
           //左右子树都不为空，那么找出右子树最小的。
           Node sessor =  findMin(node.right);
           sessor.left = node.left;
           sessor.right = removeMin(node.right);
           node.left = node.right = null;
           return sessor;

       }else if(e.compareTo(node.e)<0){
           node.left = remove(node.left,e);
           return node;
       }else {
           node.right = remove(node.right,e);
           return node;
       }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        for(int i = 0;i<10;i++){
            bst.add(i);
        }
         bst.remove(5);
         bst.preOrder();



    }
}
