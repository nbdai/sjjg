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
        public Node(T t,V v){
           this.key = t;
           this.value = v;
            left = null;
            right = null;
            color = RED;
        }
    }
    public RBTree(){
        size = 0;
        root = null;
    }
    public void preOrder() {
        //递归前序遍历。
        preOrder(root);
    }
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        if(node.left==null){
            System.out.println(node.key+":"+node.value+node.color+BLACK);
        }else
        System.out.println(node.key+":"+node.value+node.color+node.left.color);
        preOrder(node.left);
        preOrder(node.right);
    }
    private Node leftRolate(Node node){
        //  往一个黑节点的右边插入一个红节点。
        //  y
        //    \
        //     x
           Node x = node.right;
           Node z = x.left;
           Node n = node.left;
           node.right = z;
           x.left = node;
           x.color = node.color;
           node.color = RED;
           return x;
    }
    private Node rightRolate(Node node){
        //往一个红节点左边插入一个节点，让它变成一个四节点。
        //   y
        //  /
        //x
        // /
        // z
         Node x = node.left;
         Node z = x.left;
         Node m = x.right;
         Node n = node.right;
         node.left=m;
         x.right = node;
         x.color = node.color;
         z.color = RED;
         node.color = RED;
         return x;
    }
    private void changeColor(Node node){
        //向上融合。
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }
    public void add(T t,V v) {
        root = add(root,t,v);
        //根节点为黑.
        root.color = BLACK;
    }
    public  boolean  isRed(Node node){
        //空节点为黑。
        if(node==null)
            return false;
        return node.color;
    }

    private Node add(Node node,T t,V v) {
        //为空说明找到结尾了，直接添加节点即可。
        if (node == null) {
            node = new Node(t,v);
            size++;
            return node;
        }
        //负责和当前节点的值比较。如果比其小说明应该插入左子树。
        else if (t.compareTo(node.key) < 0) {
            node.left = add(node.left,t,v);
        } else if (t.compareTo(node.key) > 0) {
            node.right = add(node.right,t,v);
        }
        //维护黑平衡，即二三树的性质   三节点左边设为红，右边设为黑。   二节点为黑色的。因为23树增加节点永远不能再空的上面增加
        //只能融合当前节点，所以新增的节点我们设为红色。（若为黑还破坏了黑平衡。）
        /**
         *
         */
        if(isRed(node.right)&&!isRed(node.left)){
            //包含两种情况基于23树。
            // 2,4  三节点插入一个3 左旋后交给上层处理。
            //4二节点 插入一个5
            node = leftRolate(node);
        }
       /* if(isRed(node.left)&&isRed(node.left.right)){
           node.left =  leftRolate(node.left);
        }*/
        if(isRed(node.left)&&isRed(node.left.left)){
            //往3 ，4 一个三节点插入一个2.
            node = rightRolate(node);
        }
        //向上融合。
        if (isRed(node.left) && isRed(node.right)){
            changeColor(node);
        }

        return node;
    }

    public static void main(String[] args) {
        RBTree rbTree = new RBTree();
       for(int i = 0;i<20;i++){
           rbTree.add(i,"dai"+i);
       }
       rbTree.preOrder();
    }
}
