package com.lkwangus.example.rest.receiver.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.lkwangus.example.rest.*"})
public class AppConfig {
  @Bean
  public ModelMapper modelMapper(){
    return new ModelMapper();
  }
}

