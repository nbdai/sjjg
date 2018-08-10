package com.dzl.array;

/**
 * 按高度合并。
 */
public class HeightUnionFind {
    private int size;
    private int[] ids;
    private int[] height;
    public HeightUnionFind(int size){
        this.size = size;
        ids = new int[size];
        height = new int[size];
        for(int i=0;i<size;i++){
            ids[i] = i;
            height[i] = 1;
        }
    }
    public int find(int element){
        while(ids[element]!=element){
            element = ids[element];
        }
         return element;
    }
    public void union(int firstElement,int lastElement){
        int first = find(firstElement);
        int last = find(lastElement);
        if(first==last){
            return;
        }
        if(height[first]>height[last]){
            ids[last] = first;
        }else if(height[first]<height[last]){
            ids[first] = last;
        }else {
            //高度相当，任意一个做根
            ids[first] = last;
            height[last] = height[last]+1;
        }
    }
    public boolean isConnected(int firstElement,int lastElement){
        return find(firstElement)==find(lastElement);
    }
}
