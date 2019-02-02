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
  private final WordSource wordSource = new WordSource(System.currentTimeMillis(), "nouns.txt");

  @RequestMapping("/api/noun")
  public ResponseEntity<String> noun() {
    return new ResponseEntity<>(wordSource.randomWord(), HttpStatus.OK);
  }
}
