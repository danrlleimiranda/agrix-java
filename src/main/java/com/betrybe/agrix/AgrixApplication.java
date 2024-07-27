package com.betrybe.agrix;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application main class.
 */
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default url")})
@SpringBootApplication
public class AgrixApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(AgrixApplication.class, args);
  }

}
