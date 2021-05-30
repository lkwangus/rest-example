package com.lkwangus.example.rest.feeder.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
  Integer id;
  String messageContent;
  Date createdOn;
}
