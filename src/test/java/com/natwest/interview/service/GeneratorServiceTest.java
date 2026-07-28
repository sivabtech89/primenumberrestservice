package com.natwest.interview.service;

import com.natwest.interview.model.ResponseObj;
import com.natwest.interview.repository.CacheRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class GeneratorServiceTest {

  List<Integer> primeNumbers = new LinkedList<Integer>(Arrays.asList(2, 3, 5, 7));
  @Mock
  CacheRepo cacheRepo;
  @InjectMocks
  private GeneratorService service;

  @BeforeEach
  void setUp() {
    // can be used to set anything before every test case runs.
  }

  // This test method positive case where it returns the prime numbers list for the limit supplied
  @Test
  void testPrimesRangeForObject() {
    ResponseObj responseObj = new ResponseObj();
    responseObj.setInitial("10");
    responseObj.setPrimes(primeNumbers);
    Mockito.when(cacheRepo.findInCache(anyInt())).thenReturn(responseObj);
    Assertions.assertEquals(service.primesRange(10).getPrimes(), primeNumbers);
  }

  // This test method will test if the persisted prime numbers array is empty for the number 0
  @Test
  void testPrimesRangeMethodForNull() {
    ResponseObj responseObj = new ResponseObj();
    responseObj.setInitial("0");
    responseObj.setPrimes(new LinkedList<>());
    Mockito.when(cacheRepo.findInCache(anyInt())).thenReturn(responseObj);
    Assertions.assertTrue(service.primesRange(0).getPrimes().size() == 0);
  }

  // This test method will call the actual business logic method getPrimeRange() in the PrimeNumberGenerator service class
  @Test
  void testPrimesRangeMethodForNullResponseObj() {
    Mockito.when(cacheRepo.findInCache(anyInt())).thenReturn(null);
    Assertions.assertEquals(service.primesRange(10).getPrimes(), primeNumbers);
  }

}
