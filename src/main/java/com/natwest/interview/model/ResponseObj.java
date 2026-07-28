package com.natwest.interview.model;


import lombok.Data;

import java.util.List;

@Data
public class ResponseObj {

  private String initial;

  private List<Integer> primes;

}
