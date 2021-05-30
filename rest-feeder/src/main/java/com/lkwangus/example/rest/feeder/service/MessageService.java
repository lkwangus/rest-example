package com.lkwangus.example.rest.feeder.service;

import com.lkwangus.example.rest.feeder.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageService {

  RestTemplate restTemplate = new RestTemplate();

  final String API_URL_RECEIVE_MESSAGE = "http://localhost:8080/message";

  public Message sendMessage(String message) {
    log.info("[sendMessage] message: {}", message);

    ResponseEntity<Message> response =
        restTemplate.postForEntity(
            API_URL_RECEIVE_MESSAGE,
            Message.builder().messageContent(message).build(),
            Message.class);
    log.info("[saveMessage] response status: {}", response.getStatusCode());
    return response.getBody();
  }
}
