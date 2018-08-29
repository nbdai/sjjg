package com.dzl.hash;

import java.util.TreeMap;

/**
 * 以空间换时间。
 */
public class MyHashTable<K,V>{
    private final  static  int[] capacity = {
            53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317, 196613,
            393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843, 50331653, 100663319, 201326611,
            402653189, 805306457, 1610612741
    };
    private TreeMap<K,V>[] hashTable;
    private int size;
    private int M;//记录hash数组的长度。
    private final static int uppperTor = 10;//能够容忍最大数量的因子数。
    private final static int lowerTor = 2;
    private int capacityIndex = 0;

    public MyHashTable(){
       this.M = capacity[capacityIndex];
       hashTable = new TreeMap[M];
       size = 0;
       for(int i = 0;i<M;i++){
           hashTable[i] = new TreeMap();
       }
    }
    public void add(K key,V val){
       TreeMap map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key,val);
        }else {
            map.put(key,val);
            size++;
            if(size>=uppperTor*M&&capacityIndex<capacity.length-1){
                resize(capacity[++capacityIndex]);
            }
        }
    }
    public V remove(K key){
        TreeMap map = hashTable[hash(key)];
        V val = null;
        if(map.containsKey(key)){
            val = (V)map.remove(key);
            size--;
            if(size<=lowerTor*M&&capacityIndex>0){
                resize(capacity[--capacityIndex]);
            }
        }
        return val;
    }
    public V get(K key){
        TreeMap map = hashTable[hash(key)];
        V val = null;
        if(map.containsKey(key)){
            val = (V)map.get(key);
        }
        return val;
    }
    public void resize(int newM){
       TreeMap<K,V>[] newhashTable = new TreeMap[newM];
       int oldM = this.M;
       this.M = newM;
       for(int i = 0 ;i< oldM;i++){
           TreeMap<K,V> map = hashTable[i];
           for(K key:map.keySet()){
               newhashTable[hash(key)].put(key,map.get(key));
           }
       }
    }
    public int hash(K k){
        return (k.hashCode()&0x7fffffff)%M;
    }


}
