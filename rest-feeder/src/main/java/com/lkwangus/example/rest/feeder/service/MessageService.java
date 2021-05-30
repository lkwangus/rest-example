package com.lkwangus.example.rest.feeder.service;

import com.lkwangus.example.rest.feeder.model.Message;
import com.lkwangus.example.rest.feeder.model.req.MessageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageService {

  final RestTemplate restTemplate = new RestTemplate();
  @Value("${app.message.token}")
  String systemToken;

  final String API_URL_RECEIVE_MESSAGE = "http://localhost:8080/message";

  public Message sendMessage(String message) {
    log.info("[sendMessage] message: {}", message);
    MessageReq messageReq = genMessageReq(message);
    ResponseEntity<Message> response =
        restTemplate.postForEntity(
            API_URL_RECEIVE_MESSAGE, messageReq,
            Message.class);
    log.info("[saveMessage] response status: {}", response.getStatusCode());
    return response.getBody();
  }

  private MessageReq genMessageReq(String message){
    return MessageReq.builder()
        .messageContent(message)
        .token(genToken(message))
        .build();
  }
  private String genToken(String message){
    // concat systemToken and message as input, use input generate the token with MD5
    return DigestUtils.md5Hex(systemToken + message);
  }
}
