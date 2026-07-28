package com.natwest.interview;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(title = "PrimeNumber REST Service", description = "This REST service calculates and returns all the prime numbers up to and including a number provided using spring boot.", contact = @Contact(name = "Sivakumar Subramanian", email = "sivabtech89@gmail.com"), version = "1.0.0")
)
public class InterviewApplication {

  public static void main(String[] args) {
    SpringApplication.run(InterviewApplication.class, args);
  }

}
