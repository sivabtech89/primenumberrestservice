package com.natwest.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class InterviewApplicationTest {

  private ApplicationContext applicationContext;

  InterviewApplicationTest(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Test
  public void testAllApplicationContextBeanNamesNotNull() {
    final String[] registerBeanMap = applicationContext.getBeanDefinitionNames();
    Assertions.assertNotNull(registerBeanMap);
  }

}
