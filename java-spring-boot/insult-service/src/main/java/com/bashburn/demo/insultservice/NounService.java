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
public class NounService {
  private static final Logger logger = Logger.getLogger(NounService.class.getName());
  private static final HystrixCommandKey KEY = HystrixCommandKey.Factory.asKey(NounService.class.getSimpleName());
  @Value("${noun.host}")
  private String nounHost;
  private final RestTemplate restTemplate = new RestTemplate();

  @HystrixCommand(commandKey = "NounService",
      fallbackMethod = "getFallbackNoun",
      commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
      })
  public String lookup() {
    logger.log(Level.INFO, "Noun host: {0}", new Object[] { nounHost });
    return restTemplate.getForObject(nounHost + "/api/noun", String.class);
  }

  private String getFallbackNoun() {
    return "dimwit";
  }
}
