package com.ll.shop.service;

import com.ll.shop.domain.*;
import com.ll.shop.domain.item.Item;
import com.ll.shop.repository.ItemRepository;
import com.ll.shop.repository.MemberRepository;
import com.ll.shop.repository.OrderRepository;
import com.ll.shop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /*
    * 주문
    * */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);


        // OrderItem orderItem1 = new OrderItem(); -> protected로 이런 방식의 생성 막기

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장 -> cascade에 대해 공부하기
        orderRepository.save(order);

        return order.getId();
    }



    /*
    * 주문 취소
    * */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        // 주문 취소
        order.cancel();
    }

    /*
    * 검색
    * */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
