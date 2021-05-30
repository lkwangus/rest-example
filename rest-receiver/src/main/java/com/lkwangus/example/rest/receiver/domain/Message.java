package com.lkwangus.example.rest.receiver.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "message")
public class Message {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  Integer id;

  @Column
  String messageContent;

  @Column
  Date createdOn;

}
