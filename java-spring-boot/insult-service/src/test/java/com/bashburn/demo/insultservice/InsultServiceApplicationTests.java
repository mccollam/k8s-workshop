package com.bashburn.demo.insultservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "management.metrics.export.signalfx.access-token=invalid_token")
public class InsultServiceApplicationTests {

  @Test
  public void contextLoads() {
  }

}
