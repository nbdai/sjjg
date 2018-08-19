package com.dzl.tree;

public class RBTree<T extends  Comparable<T>, V>{
    /**
     * @desc
     *  红黑 等级与 23 树。
     *  严格意义上它“黑平衡”
     *  不是平衡二叉树
     *  最大高度为2longn
     */
    private static  final boolean RED = true;
    private static  final boolean BLACK = false;
    private Integer size;
    private Node root;

    private class Node{
        private T key;
        private V value;
        private Node left;
        private Node right;
        private boolean color;
        public Node(){
            key = null;
            value = null;
            left = null;
            right = null;
            color = RED;
        }
    }
}
