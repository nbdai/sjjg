package com.dzl.array;

/**
 * 并查集 快查慢合并。
 */
public class UnionFind {
    private int[] ids;
    private int size;

    public UnionFind(int size){
        this.size = size;
        ids = new int[size];
        for(int i = 0;i<size;i++){
            ids[i] = i;
        }
    }
    public int find(int element){
        return ids[element];
    }
    public void unionElement(int firstElement,int secondElement){
        int firstUnion = find(firstElement);
        int secondUion = find(secondElement);
        if(firstElement!=secondElement){
            for(int i = 0;i<size;i++){
                if(ids[i]==firstElement){
                   ids[i] = secondElement;
                }
            }
        }
    }
    public boolean isConnected(int firstElement,int secondElement){

        return find(firstElement)==find(secondElement);
    }


}
