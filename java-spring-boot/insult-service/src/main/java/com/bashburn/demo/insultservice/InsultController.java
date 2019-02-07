package com.bashburn.demo.insultservice;

import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;

@RestController
public class InsultController {
  private static final String API_CALL = "api.call";
  private static final Tag SERVICE_TAG = Tag.of("service", "insult-service");
  private static final Tag CONTEXT_TAG = Tag.of("context", "/api/insult");
  private final AdjectiveService adjectiveService;
  private final NounService nounService;
  private final Counter counter;

  public InsultController(final MeterRegistry registry, final AdjectiveService adjectiveService, final NounService nounService) {
    this.adjectiveService = adjectiveService;
    this.nounService = nounService;
    counter = registry.counter(API_CALL, Arrays.asList(SERVICE_TAG, CONTEXT_TAG));
  }

  @RequestMapping("/api/insult")
  public Insult createInsult() {
    counter.increment();
    String first = adjectiveService.lookupFirst();
    String second = adjectiveService.lookupSecond();
    String noun = nounService.lookup();
    return new Insult(first, second, noun);
  }
}
