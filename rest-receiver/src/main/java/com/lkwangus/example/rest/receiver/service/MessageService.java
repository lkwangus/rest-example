package com.lkwangus.example.rest.receiver.service;

import com.lkwangus.example.rest.receiver.domain.Message;
import com.lkwangus.example.rest.receiver.repository.MessageRepository;
import java.util.Date;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageService{

  @NonNull MessageRepository messageRepository;

  public Message saveMessage(Message message){
    log.info("[saveMessage] message: {}", message.getMessageContent());
    Date now = new Date();
    message.setCreatedOn(now);
    Message dbMessage = messageRepository.save(message);
    log.info("[saveMessage] Message Saved successfully, id: {}", dbMessage.getId());
    return dbMessage;
  }
}
