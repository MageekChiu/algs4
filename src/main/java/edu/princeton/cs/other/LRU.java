//package edu.princeton.cs.other;
//
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
//    基于链表和hashmapx的LRU
// * @author Mageek Chiu
// */
//public class LRU<K,V> {
//
//    /**
//     * list
//     */
//    public Node<K,V> head = new Node("s","s");
//    public Node<K,V> tail = new Node("nn","-1");
//
//    /**
//     * hash map
//     */
//    public  Map<String,Node<K,V>> map = new ConcurrentHashMap<>();
//
//    public void put(Node node){
//
//    }
//
//    public Node get(String key){
//
//    }
//
//
//    public static void main (String ...args){
//
//    }
//
//    class Node<K,V>{
//        K key;
//        V value;
//        Node next;// 下一节点
//        Node pre;//上一节点
//        Node( K k,V v){
//            key = k;
//            value = v;
//        }
//    }
//}
//
//
//
