package com.bashburn.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordSource {
  private static final Logger log = Logger.getLogger(WordSource.class.getName());
  private final Random rangen;
  private final List<String> words;

  public WordSource(final long seed, final String filename) {
    rangen = new Random(seed);
    words = generateWordList(filename);
  }

  public String randomWord() {
    return words.get(rangen.nextInt(words.size()));
  }

  private List<String> generateWordList(final String filename) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)))) {
      ArrayList<String> wordfile = new ArrayList<>();
      while(reader.ready()) {
        wordfile.add(reader.readLine().trim());
      }
      return wordfile;
    } catch(IOException e) {
      log.log(Level.SEVERE, "Error loading word source", e);
    }
    return Collections.emptyList();
  }
}
