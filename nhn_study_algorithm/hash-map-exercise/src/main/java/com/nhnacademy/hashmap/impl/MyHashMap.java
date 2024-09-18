package com.nhnacademy.hashmap.impl;

import com.nhnacademy.hashmap.IMap;
import com.nhnacademy.hashmap.node.HashNode;

import java.util.*;

public class MyHashMap<K, V> implements IMap<K, V> {

    //Todo 1. HashNode를 이용하여 구현합니다.
    private static final int DEFAULT_CAPACITY = 31;
    private int size;
    private HashNode<K, V>[] table;

    private int hash(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
//        System.out.println(Objects.hashCode(key) % 31);
        return Objects.hashCode(key) % 31;
    }

    public MyHashMap() {
        table =  new HashNode[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void clear() {
        table = new HashNode[DEFAULT_CAPACITY];
        size = 0;
//        if (table != null) {
//            System.out.println("null table");
//        }
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        HashNode<K, V> node = table[index];
        while (node != null) {
            if (Objects.equals(node.getKey(), key)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (HashNode<K, V> node : table) {
            while (node != null) {
                if (Objects.equals(node.getValue(), value)) {
                    return true;
                }
                node = node.getNext();
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if(!containsKey(key)) {
            throw new IllegalArgumentException();
        }

        int index = hash(key);
        HashNode<K, V> node = table[index];
        while (node != null) {
            if (Objects.equals(node.getKey(), key)) {
                return node.getValue();
            }
            node = node.getNext();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> node = table[index];
        while (node != null) {
            if (Objects.equals(node.getKey(), key)) {
                V oldValue = node.getValue();
                node.setValue(value);
                return oldValue;
            }
            node = node.getNext();
        }

        HashNode<K, V> newNode = new HashNode<>(index, key, value);
        newNode.setNext(table[index]);
        table[index] = newNode;
        size++;
        return newNode.getValue();
    }

    @Override
    public void remove(K key) {
        int index = hash(key);
        HashNode<K, V> node = table[index];
        HashNode<K, V> prev = null;

        while (node != null) {
            if (Objects.equals(node.getKey(), key)) {
                if (prev == null) {
                    table[index] = node.getNext();
                } else {
                    prev.setNext(node.getNext());
                }
                size--;
                return;
            }
            prev = node;
            node = node.getNext();
        }
    }

    @Override
    public int size() {
        return size;
    }

}
