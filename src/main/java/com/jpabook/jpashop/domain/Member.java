package com.jpabook.jpashop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @NotEmpty
    private String name;
    @Embedded
    private Address address;

//    @JsonIgnore // json에 보낼 때 무시해주는 어노테이션 -> 엔티티에 프리젠테이션 계층을 위한 로직이 추가되기 시작.......
    // api spec이나 화면.... 엔티티에 들어오게 된다. 애플리케이션을 수정할 때 어려움이 발생할 가능성이 높음.
    // 다른 방법을 사용하자.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
