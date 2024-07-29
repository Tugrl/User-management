package com.example.user_management.service;

import com.example.user_management.dto.MessageDTO;
import com.example.user_management.entity.Message;
import com.example.user_management.logic.MessageLogic;
import com.example.user_management.mapper.MessageMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageLogic messageLogic;

    public List<MessageDTO> getAllMessages() {
        return messageLogic.getAllMesages().stream().map(messageMapper ::messageToMessageDTO)
                .collect(Collectors.toList());
    }
    public MessageDTO getMessageById(UUID id) {
        Message message = messageLogic.getMessageById(id);
        return messageMapper.messageToMessageDTO(message);
    }
    public MessageDTO createMessage(MessageDTO messageDTO) {
        Message message = messageMapper.messageDTOToMessage(messageDTO);
        Message sentMessage = messageLogic.createMessage(message,messageDTO.getReceiverId());
        return messageMapper.messageToMessageDTO(sentMessage);
    }
}
