package com.nhnacademy.hashmap.node;

public class HashNode<K,V> {
    private final int hash;
    private final K key;
    private V value;

    private HashNode<K,V> next;

    public HashNode(int hash, K key, V value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }

    public int getHash() {
        return hash;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public HashNode getNext() {
        return next;
    }

    public void setNext(HashNode next) {
        this.next = next;
    }

}
