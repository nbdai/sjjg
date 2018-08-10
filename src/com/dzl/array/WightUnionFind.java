package com.dzl.array;

/**
 * @desc并查集 快合并慢查。并将重量小的加到重量大的上。
 */
public class WightUnionFind {
    //用于记录父项的值
    private int[] ids;
    private int size;
    //用于记录权值。
    private int[] weight;
    public WightUnionFind(int size){
        this.size = size;
        ids = new int[size];
        weight = new int[size];
        for(int i = 0;i<size;i++){
            ids[i] = i;
            weight[i] = 1;
        }
    }
    public int find(int element){
        //查找root.
        while (ids[element]!=element){
            //一直找到值等于它自己,那么它一定是root.
            element = ids[element];
        }
        return  element;
    }
    public void union(int firstElement,int lastElement){
        int first = find(firstElement);
        int last = find(lastElement);
        if(first == last){
            //说明在一个集合中。
            return;
        }
        if(weight[first]>weight[last]){
          //root合并
            ids[last]=first;
            weight[first]+=weight[last];
        }else{
            ids[first] = last;
            weight[last]+=weight[first];
        }
    }
    public boolean isConnected(int firstElement,int lastElement){
        return find(firstElement)==find(lastElement);
    }
}
