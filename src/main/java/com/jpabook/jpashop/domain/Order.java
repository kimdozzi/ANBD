package com.jpabook.jpashop.domain;

import lombok.Getter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id") // 외래키
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private Delivery delivery;

    // 날짜관련 어노테이션 매핑해야함 -> java8 LocalDateTime 쓰면 hibernate가 자동 지원
    private LocalDateTime orderDate;

    private OrderStatus status; // 주문상태 [ORDER, CANCEL]
}
