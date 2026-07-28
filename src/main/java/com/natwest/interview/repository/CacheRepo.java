package com.natwest.interview.repository;

import com.natwest.interview.model.ResponseObj;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Repository
@Slf4j
// This class can be replaced by Redis - an in-memory cache is a high-speed data storage layer but for the use-case of this interview, have used apache commons PassiveExpiringMap
public class CacheRepo {

  PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<Integer, ResponseObj> expirationPolicy = new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(
      30, TimeUnit.SECONDS);
  PassiveExpiringMap<Integer, ResponseObj> expiringMap = new PassiveExpiringMap<>(expirationPolicy,
      new HashMap<>());

  // the responseObj for the key limit is retrieved from in-memory if exists within 30 seconds.
  public ResponseObj findInCache(int limit) {
    return expiringMap.get(limit) != null ? expiringMap.get(limit) : null;
  }

  // the responseObj for the key limit is saved in-memory for 30 mins.
  public void saveInCache(int limit, ResponseObj responseObj) {
    log.info("Saving the response in-memory for limit{} ", limit);
    expiringMap.put(limit, responseObj);
  }
}
