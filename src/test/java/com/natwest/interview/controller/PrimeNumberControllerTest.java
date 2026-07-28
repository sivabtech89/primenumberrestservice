package com.natwest.interview.controller;

import com.natwest.interview.service.GeneratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;


@ExtendWith(MockitoExtension.class)
public class PrimeNumberControllerTest {

  @InjectMocks
  PrimeNumberController primeNumberController;

  @Mock
  GeneratorService generatorServiceService;

  @Test
  void testGetPrimesMethod() {
    Assertions.assertEquals(HttpStatus.OK, primeNumberController.getPrimes(10).getStatusCode());
  }
}
