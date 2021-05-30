package com.lkwangus.example.rest.feeder;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RestFeederApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(RestFeederApplication.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }
}
