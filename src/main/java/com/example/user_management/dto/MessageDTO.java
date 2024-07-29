package com.example.user_management.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public class MessageDTO {
    private UUID id;
    private String header;
    private String body;
    private LocalDateTime sendDate;
    private UUID senderId;
    private UUID receiverId;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public LocalDateTime getSendDate() {
        return sendDate;
    }
    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSender(UUID senderId) {
        this.senderId = senderId;
    }
    public UUID getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(UUID receiverId) {
        this.receiverId = receiverId;
    }


}

