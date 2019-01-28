package com.bashburn.demo.insultservice;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class AdjectiveService {
  private static final Logger logger = Logger.getLogger(AdjectiveService.class.getName());
  private static final HystrixCommandKey KEY = HystrixCommandKey.Factory.asKey(AdjectiveService.class.getSimpleName());
  @Value("${adjective.host}")
  private String adjectiveHost;
  private final RestTemplate restTemplate = new RestTemplate();

  @HystrixCommand(commandKey = "AdjectiveService",
      fallbackMethod = "getFallbackAdjectiveOne",
      commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
      })
  public String lookupFirst() {
    logger.log(Level.INFO, "Adjective Host: {0}", new Object[] { adjectiveHost });
    return restTemplate.getForObject(adjectiveHost + "/api/adjective/one", String.class);
  }

  @HystrixCommand(commandKey = "AdjectiveService",
      fallbackMethod = "getFallbackAdjectiveTwo",
      commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
      })
  public String lookupSecond() {
    logger.log(Level.INFO, "Adjective Host: {0}", new Object[] { adjectiveHost });
    return restTemplate.getForObject(adjectiveHost + "/api/adjective/two", String.class);
  }

  private String getFallbackAdjectiveOne() {
    return "unconnected";
  }

  private String getFallbackAdjectiveTwo() {
    return "non-distributed";
  }
}
