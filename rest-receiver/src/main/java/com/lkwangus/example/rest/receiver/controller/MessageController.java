package com.lkwangus.example.rest.receiver.controller;

import com.lkwangus.example.rest.receiver.domain.Message;
import com.lkwangus.example.rest.receiver.service.MessageService;
import java.util.Date;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController {

  @NonNull MessageService messageService;

  @PostMapping("/message")
  public ResponseEntity<Message> saveMessage(@RequestBody Message message){
    log.info("[saveMessage] message: {}", message);
    Date now = new Date();
    message.setCreatedOn(now);
    Message dbMessage = messageService.saveMessage(message);
    return ResponseEntity.ok(dbMessage);
  }
}
