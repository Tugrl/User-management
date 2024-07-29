package com.example.user_management.mapper;

import com.example.user_management.dto.MessageDTO;
import com.example.user_management.entity.Message;
import com.example.user_management.entity.User;
import com.example.user_management.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    private final UserRepository userRepository;

    public MessageMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MessageDTO messageToMessageDTO(Message message) {
        if (message == null) {
            return null;
        }
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setHeader(message.getHeader());
        messageDTO.setBody(message.getBody());
        messageDTO.setSendDate(message.getSendDate());
        messageDTO.setSender(message.getSender().getId());
        messageDTO.setReceiverId(message.getReceiverId());
        return messageDTO;
    }

    public Message messageDTOToMessage(MessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }
        Message message = new Message();
        message.setId(messageDTO.getId());
        message.setHeader(messageDTO.getHeader());
        message.setBody(messageDTO.getBody());
        message.setSendDate(messageDTO.getSendDate());
        User sender = userRepository.findById(messageDTO.getSenderId()).
                orElseThrow(() -> new RuntimeException("Sender not found"));
        message.setSender(sender);
        message.setReceiverId(messageDTO.getReceiverId());
        return message;
    }
}
