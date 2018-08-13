package com.dzl.tree;

import java.util.*;

/**
 * @author DZL
 * @desc
 */
public class AvlTree<E extends Comparable<E>>{
    private Integer size;
    private Node root;

    private class Node {
        private E e;
        private Node left;
        private Node right;
        private int height;
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
            //在整个树中新建节点一定为叶子节点。
            height = 1;
        }
    }

    public AvlTree() {
        root = null;
        size = 0;
    }

    public void add(E e) {
        root = add(root, e);
    }
    public int getHeight(){
        return getHeight(root);
    }
    private int getHeight(Node node){
        //计算当前树的高度.
        if(node==null)
            return 0;
        return node.height;
    }
    public int balanceNum(Node node){
        //计算节点的平衡因子左子树的高度减去右子树的高度。
        if(node==null)
            return 0;
         return getHeight(node.left)-getHeight(node.right);
    }
    public boolean isBalance(){
        return  isBalance(root);
    }
    private boolean isBalance(Node node){
        //判断是否为AVL树。
        if(node == null)
            return true;
        if(Math.abs(balanceNum(node))>1)
            return false;
        return isBalance(node.left)&&isBalance(node.right);
    }
    public boolean isBST(){
        //判断是否为平衡搜索树。
         List<E> list = new ArrayList<>();
         inorder(list,root);
        for (int i = 1 ; i<list.size();i++){
           if(list.get(i-1).compareTo(list.get(i))>0)
               return false;
        }
               return true;
    }
    private void inorder(List<E> list ,Node node){
        if(node==null)
            return;
       inorder(list,node.left);
       list.add(node.e);
       inorder(list,node.right);

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
        }else {
            return node;
        }
        //维护平衡
        //每增加一个节点应该递归更新当前节点的高度。
        node.height = 1+Math.max(node.left.height,node.right.height);
        //计算平衡因子.
        int balanceFactor = balanceNum(node);
        //LL
        if(balanceFactor>1&&balanceNum(node.left)>=0){
            return rightRolate(node);
        }
        //RR
        if(balanceFactor<-1&&balanceNum(node.right)<=0){
            return leftRolate(node);
        }
        //LR
        if(balanceFactor>1&&balanceNum(node.left)<0){
            /**
             *    y
             *   /
             *  x
             *    \
             *     z
             */
            node.left = leftRolate(node.left);
            return rightRolate(node);
        }
        //RL
        if(balanceFactor<-1&&balanceNum(node.right)>0){
            node.right = rightRolate(node.right);
            return leftRolate(node);
        }

        return node;
    }
    private Node rightRolate(Node y){
        /**     y
         *     /\
         *   x   h   右旋
         *  / \
         * n   z
         */

         Node x = y.left;
         Node z = x.right;
         x.right = y;
         y.left = z;
         return x;
    }
    private Node leftRolate(Node y){
        /**     y
         *     /\
         *   h   x   左旋
         *     / \
         *    z  n
         */
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        return x;
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

    public void remove(E e){
        root = remove(root,e);
    }
    private Node remove(Node node, E e){
        if(node == null)
            return null;
        Node returnNode ;
        if(e.compareTo(node.e)==0){
            //值相等就删除。
            if(node.left==null){
                Node temp = node.right;
                node.right = null;
                size--;
                returnNode = temp;
            } else if(node.right==null){
                Node temp = node.left;
                node.left = null;
                size--;
                returnNode = temp;
            }else {
                //左右子树都不为空，那么找出右子树最小的。
                Node sessor =  findMin(node.right);
                sessor.left = node.left;
                //删除最小值，因为sessor就是最小值，所以递归调用即可。
                sessor.right = remove(node.right,sessor.e);
                node.left = node.right = null;
               returnNode = sessor;
            }

        }else if(e.compareTo(node.e)<0){
            node.left = remove(node.left,e);
            returnNode =  node;
        }else {
            node.right = remove(node.right,e);
            returnNode = node;
        }
           if(returnNode == null)
               return null;
        //维护平衡
        //每增加一个节点应该递归更新当前节点的高度。
        returnNode.height = 1+Math.max(returnNode.left.height,returnNode.right.height);
        //计算平衡因子.
        int balanceFactor = balanceNum(returnNode);
        //LL
        if(balanceFactor>1&&balanceNum(returnNode.left)>=0){
            return rightRolate(returnNode);
        }
        //RR
        if(balanceFactor<-1&&balanceNum(returnNode.right)<=0){
            return leftRolate(returnNode);
        }
        //LR
        if(balanceFactor>1&&balanceNum(returnNode.left)<0){
            /**
             *    y
             *   /
             *  x
             *    \
             *     z
             */
            node.left = leftRolate(returnNode.left);
            return rightRolate(returnNode);
        }
        //RL
        if(balanceFactor<-1&&balanceNum(returnNode.right)>0){
            node.right = rightRolate(returnNode.right);
            return leftRolate(returnNode);
        }

            return returnNode;
    }

    public static void main(String[] args) {



    }
}
