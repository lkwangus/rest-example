package com.lkwangus.example.rest.receiver.service;

import com.lkwangus.example.rest.receiver.domain.Message;
import com.lkwangus.example.rest.receiver.model.req.MessageReq;
import com.lkwangus.example.rest.receiver.repository.MessageRepository;
import java.util.Date;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageService {

  @NonNull MessageRepository messageRepository;

  @Value("${app.message.token}")
  String systemToken;

  public Message saveMessage(Message message) {
    log.info("[saveMessage] message: {}", message.getMessageContent());
    Date now = new Date();
    message.setCreatedOn(now);
    Message dbMessage = messageRepository.save(message);
    log.info("[saveMessage] Message Saved successfully, id: {}", dbMessage.getId());
    return dbMessage;
  }

  public Page<Message> getMessage(Pageable pageable) {
    return messageRepository.findAll(pageable);
  }

  public boolean validateToken(MessageReq messageReq){
    // concat systemToken and message as input, use input generate the token with MD5
    String check = DigestUtils.md5Hex(systemToken + messageReq.getMessageContent());
    return StringUtils.equals(check, messageReq.getToken());
  }
}
