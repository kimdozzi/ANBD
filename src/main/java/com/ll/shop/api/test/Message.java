package com.ll.shop.api.test;

import lombok.Data;

@Data
public class Message {

    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = status.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}
