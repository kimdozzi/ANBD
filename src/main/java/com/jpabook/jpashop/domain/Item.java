package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private long id;


}
