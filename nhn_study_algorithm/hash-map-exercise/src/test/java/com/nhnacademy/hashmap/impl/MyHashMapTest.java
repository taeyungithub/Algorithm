package com.nhnacademy.hashmap.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyHashMapTest {
    //Todo 2. 구현한 MyHashMap을 적절히 테스트합니다.
    MyHashMap<Integer, Integer> myHashMap;

    @BeforeEach
    @DisplayName("해쉬맵 생성, 6개 노드 put")
    void setUp() {
        myHashMap = new MyHashMap<>();
                             // 현재 인덱스위치
        myHashMap.put(0, 100);    // 0
        myHashMap.put(1, 101);    // 1
        myHashMap.put(2, 102);    // 2
        myHashMap.put(3, 103);    // 3
        myHashMap.put(31, 1031);   // 0
        myHashMap.put(32, 1032);   // 1
    }


    @Test
    @DisplayName("키가 존재하는지")
    void containsKey() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(myHashMap.containsKey(0)),
                () -> Assertions.assertFalse(myHashMap.containsKey(5))
        );
    }

    @Test
    @DisplayName("값이 존재하는지")
    void containsValue() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(myHashMap.containsValue(1031)),
                () -> Assertions.assertFalse(myHashMap.containsValue(104))
        );
    }

    @Test
    @DisplayName("키가 있으면 그 값 가져오기")
    void get1() {
        Assertions.assertEquals(100, myHashMap.get(0));
    }

    @Test
    @DisplayName("키가 없으면 예외처리")
    void get2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> myHashMap.get(5));
    }

    @Test
    @DisplayName("현재 비어있는가 false")
    void isEmpty() {
        Assertions.assertFalse(myHashMap.isEmpty());
    }

    @Test
    @DisplayName("키와 값이 잘 들어갔는가")
    void put() {
        myHashMap.put(777,777);
        Assertions.assertTrue(myHashMap.containsKey(777));
    }

    @Test
    @DisplayName("키와 값이 잘 제거됬는가")
    void remove() {
        myHashMap.remove(32);
        Assertions.assertFalse(myHashMap.containsKey(32));
    }

    @Test
    void size() {
        Assertions.assertEquals(myHashMap.size(), 6);
    }

    @Test
    @DisplayName("size = 0, myHashMap이 비어있어야 된다")
    void clear() {
        myHashMap.clear();
        Assertions.assertAll(
                () -> Assertions.assertEquals(myHashMap.size(), 0),
                () -> Assertions.assertEquals(true, myHashMap.isEmpty())
        );
    }


}