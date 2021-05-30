package com.lkwangus.example.rest.receiver.controller;

import com.lkwangus.example.rest.receiver.domain.Message;
import com.lkwangus.example.rest.receiver.model.dto.MessageDto;
import com.lkwangus.example.rest.receiver.model.req.MessageReq;
import com.lkwangus.example.rest.receiver.service.MessageService;
import java.util.Date;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController {

  @NonNull MessageService messageService;
  @NonNull ModelMapper modelMapper;

  @PostMapping("/message")
  public ResponseEntity<MessageDto> saveMessage(@RequestBody MessageReq req) {
    log.info("[saveMessage] message: {}", req);
    boolean isValid = this.messageService.validateToken(req);
    ResponseEntity<MessageDto> response;
    if (isValid){
      Message message = modelMapper.map(req, Message.class);
      Date now = new Date();
      message.setCreatedOn(now);
      Message dbMessage = messageService.saveMessage(message);
      // Transfer the message entity to DTO
      MessageDto messageDto = modelMapper.map(dbMessage, MessageDto.class);
      // Response the message entity to DTO
      response = ResponseEntity.ok(messageDto);
    }else {
      response = ResponseEntity.badRequest().build();
    }
    return response;
  }

  @GetMapping("/message")
  public ResponseEntity<Page<MessageDto>> getMessage(Pageable pageable) {
    log.info("[getMessage] pageable: {}", pageable);
    Page<Message> dbMessages = messageService.getMessage(pageable);
    // Transfer the message entity to DTO
    Page<MessageDto> messageDtos = dbMessages.map(m -> modelMapper.map(m, MessageDto.class));
    // Response the message entity to DTO
    return ResponseEntity.ok(messageDtos);
  }
}
