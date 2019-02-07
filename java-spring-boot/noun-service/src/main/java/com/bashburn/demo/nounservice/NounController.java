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
  private static final Tag SERVICE_TAG = Tag.of("service", "adjective-service");
  private final WordSource wordSource = new WordSource(System.currentTimeMillis(), "nouns.txt");
  private final Counter firstCounter;

  public NounController(final MeterRegistry registry) {
    firstCounter = registry.counter(API_CALL, Arrays.asList(SERVICE_TAG, Tag.of("context", "/api/noun")));
  }

  @RequestMapping("/api/noun")
  public ResponseEntity<String> noun() {
    firstCounter.increment();
    return new ResponseEntity<>(wordSource.randomWord(), HttpStatus.OK);
  }
}
