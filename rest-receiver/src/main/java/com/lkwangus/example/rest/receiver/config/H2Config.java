package com.lkwangus.example.rest.receiver.config;

import java.sql.SQLException;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Config {
  @Bean(initMethod = "start", destroyMethod = "stop")
  public Server h2TcpServer() throws SQLException {
    return Server.createTcpServer(
        "-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
  }

  @Bean(initMethod = "start", destroyMethod = "stop")
  public Server h2WebServer() throws SQLException {
    return Server.createWebServer(
        "-web", "-webAllowOthers", "-webPort", "8092");
  }
}

