package com.ll.shop.test;

import lombok.Builder;

@Builder
public class BuilderTest {
    private String username;
    private int age;

    public BuilderTest(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
