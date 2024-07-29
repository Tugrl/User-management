package com.example.user_management.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tbl_message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String header;
    @Column(nullable = false)
    private String body;
    @Column(nullable = false)
    private LocalDateTime sendDate;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @Column(nullable = false)
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
public User getSender() {
    return sender;
}
public void setSender(User sender) {
    this.sender = sender;
}
public UUID getReceiverId() {
    return receiverId;
}
public void setReceiverId(UUID receiverId) {
    this.receiverId = receiverId;
}


    @PrePersist
    public void prePersist() {
        sendDate = LocalDateTime.now();
    }

}
