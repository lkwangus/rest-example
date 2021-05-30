package com.lkwangus.example.rest.feeder;

import com.lkwangus.example.rest.feeder.model.Message;
import com.lkwangus.example.rest.feeder.service.MessageService;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableAsync
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageFeederScheduler {

  @NonNull MessageService messageService;

  @Async
  @Scheduled(cron = "*/5 * * * * *")
  public void messageFeed() {
    log.info("[messageFeed]");
    String dateString = SimpleDateFormat.getDateTimeInstance().format(new Date());
    String content =
        MessageFormatter.format("{}: {}", dateString, DigestUtils.md5Hex(dateString)).getMessage();
    Message message = messageService.sendMessage(content);
    log.info("[messageFeed] message: {}", message);
  }
}
