package com.bashburn.demo.adjectiveservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bashburn.demo.util.WordSource;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;

@RestController
public class AdjectiveController {
  private static final String API_CALL = "api.call";
  private static final String API_LATENCY = "api.latency";
  private static final Tag SERVICE_TAG = Tag.of("service", "adjective-service");
  private final WordSource firstSource = new WordSource(System.currentTimeMillis(), "adjective_one.txt");
  private final WordSource secondSource = new WordSource(System.currentTimeMillis(), "adjective_two.txt");
  private final Counter firstCounter;
  private final Counter secondCounter;
  private final Timer firstTimer;
  private final Timer secondTimer;

  public AdjectiveController(final MeterRegistry registry) {
    firstCounter = registry.counter(API_CALL, Arrays.asList(SERVICE_TAG, Tag.of("context", "/api/adjective/one")));
    secondCounter = registry.counter(API_CALL, Arrays.asList(SERVICE_TAG, Tag.of("context", "/api/adjective/two")));
    firstTimer = registry.timer(API_LATENCY, Arrays.asList(SERVICE_TAG, Tag.of("context", "/api/adjective/one")));
    secondTimer = registry.timer(API_LATENCY, Arrays.asList(SERVICE_TAG, Tag.of("context", "/api/adjective/two")));
  }

  @RequestMapping("/api/adjective/one")
  public ResponseEntity<String> adjectiveOne() {
    firstCounter.increment();
    return firstTimer.record(() -> new ResponseEntity<>(firstSource.randomWord(), HttpStatus.OK));
  }

  @RequestMapping("/api/adjective/two")
  public ResponseEntity<String> adjectiveTwo() {
    secondCounter.increment();
    return secondTimer.record(() -> new ResponseEntity<>(secondSource.randomWord(), HttpStatus.OK));
  }
}
