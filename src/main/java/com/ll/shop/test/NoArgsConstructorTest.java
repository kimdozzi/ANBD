package com.ll.shop.test;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class NoArgsConstructorTest {
    private String username;
    private int age;

    public NoArgsConstructorTest(int age) {
        this.age = age;
        this.username = "닉네임을 설정하세요";
    }

    public static void main(String[] args) {
        NoArgsConstructorTest user = new NoArgsConstructorTest(19);
        System.out.println(user.username + user.age);

    }
}
