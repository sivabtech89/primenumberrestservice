package com.natwest.interview.service;

import com.natwest.interview.model.ResponseObj;
import com.natwest.interview.repository.CacheRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
// Actual business logic for generating the prime numbers up to and including a number provided.
public class GeneratorService {

  @Autowired
  CacheRepo cacheRepo;

  public ResponseObj primesRange(int limit) {
    log.info("Check in-memory cache with the limit key {} before calling the actual logic ", limit);
    ResponseObj responseObj = cacheRepo.findInCache(limit);
    return responseObj != null ? responseObj : getPrimeRange(limit);
  }

  //  Sieve Of Eratosthenes - Efficient method which could help us to generate prime numbers efficiently. Its time efficiency is O(n logn).
  private ResponseObj getPrimeRange(int limit) {
    {
      log.info("No reference found in the In-memory, generating prime numbers efficiently up-to {}",
          limit);
      ResponseObj responseObj = new ResponseObj();
      //  Initializing a Boxed Boolean Array to store the boolean values
      boolean prime[] = new boolean[limit + 1];
      Arrays.fill(prime, true);
      for (int p = 2; p * p <= limit; p++) {
        if (prime[p]) {
          for (int i = p * 2; i <= limit; i += p) {
            prime[i] = false;
          }
        }
      }
      // Create a LinkedList to add all the generated prime numbers
      List<Integer> primeNumbersList = new LinkedList<>();
      for (int i = 2; i <= limit; i++) {
        if (prime[i]) {
          primeNumbersList.add(i);
        }
      }
      responseObj.setInitial(String.valueOf(limit));
      responseObj.setPrimes(primeNumbersList);
      cacheRepo.saveInCache(limit, responseObj);
      return responseObj;
    }
  }
}
