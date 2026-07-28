package com.natwest.interview.repository;

import com.natwest.interview.model.ResponseObj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CacheRepoTest {

  List<Integer> primeNumbers = new LinkedList<Integer>(Arrays.asList(2, 3, 5, 7));

  private CacheRepo cacheRepo;

  @BeforeEach
  void setUp() {
    cacheRepo = new CacheRepo();
  }

  @Test
  void testSaveAndRetrieveFromCacheMethods() {
    ResponseObj responseObj = new ResponseObj();
    responseObj.setInitial("10");
    responseObj.setPrimes(primeNumbers);
    cacheRepo.saveInCache(10, responseObj);
    Assertions.assertEquals(cacheRepo.findInCache(10).getPrimes(), primeNumbers);
  }

  @Test
  void testSaveAndRetrieveFromCacheForEmptyList() {
    ResponseObj responseObj = new ResponseObj();
    responseObj.setInitial("1");
    responseObj.setPrimes(new LinkedList<>());
    cacheRepo.saveInCache(1, responseObj);
    Assertions.assertTrue(cacheRepo.findInCache(1).getPrimes().size() == 0);
  }

  @Test
  void testSaveAndRetrieveFromCacheForNull() {
    cacheRepo.saveInCache(1, null);
    Assertions.assertNull(cacheRepo.findInCache(1));
  }

}
