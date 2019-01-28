package com.bashburn.demo.nounservice;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bashburn.demo.util.WordSource;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;

@RestController
public class NounController {
  private static final String API_CALL = "api.call";
  private static final String API_LATENCY = "api.latency";
  private static final Tag SERVICE_TAG = Tag.of("service", "noun-service");
  private static final Tag CONTEXT_TAG = Tag.of("context", "/api/noun");
  private final WordSource wordSource = new WordSource(System.currentTimeMillis(), "nouns.txt");
  private final Counter counter;
  private final Timer timer;

  public NounController(final MeterRegistry registry) {
    counter = registry.counter(API_CALL, Arrays.asList(SERVICE_TAG, CONTEXT_TAG));
    timer = registry.timer(API_LATENCY, Arrays.asList(SERVICE_TAG, CONTEXT_TAG));
  }

  @RequestMapping("/api/noun")
  public ResponseEntity<String> noun() {
    counter.increment();
    return timer.record(() -> new ResponseEntity<>(wordSource.randomWord(), HttpStatus.OK));
  }
}
