package edu.princeton.cs.other;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 基于链表和hashmapx的LRU
 LinkedHashMap继承了HashMap底层是通过Hash表+单向链表实现Hash算法，内部自己维护了一套元素访问顺序的列表。
 * @author Mageek Chiu
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V>{
    //定义缓存的容量
    private int capacity;
    private static final long serialVersionUID = 1L;
    //带参数的构造器
    LRUCache(int capacity){
        //调用LinkedHashMap的构造器，传入以下参数
        super(16,0.75f,true);
        //传入指定的缓存最大容量
        this.capacity=capacity;
    }
    //实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest){
//        System.out.println(eldest.getKey() + "=" + eldest.getValue());
        return size()>capacity;
    }

//    @Override
//    public V get(Object key) {
//        return super.get(key)==null ? -1 : super.get(key);
//    }


    public static void  main(String... args){
        LinkedHashMap<Integer,Integer> cache = new LRUCache<>(2);
        out.println(cache.put(1, 1));
        out.println(cache.put(2, 2));
        out.println(cache.get(1));       // 返回  1
        out.println(cache.put(3, 3));    // 该操作会使得密钥 2 作废
        out.println(cache.get(2));       // 返回 -1 (未找到)
        out.println(cache.put(4, 4));    // 该操作会使得密钥 1 作废
        out.println(cache.get(1));       // 返回 -1 (未找到)
        out.println(cache.get(3));       // 返回  3
        out.println(cache.get(4));       // 返回  4
    }

}



