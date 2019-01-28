package com.bashburn.demo.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WordSourceTest {
  @Test
  public void testWordSource() {
    WordSource ws = new WordSource(1l, "test_words.txt");
    String word = ws.randomWord();
    assertThat(word, notNullValue());
    assertThat(word, is("moldwarp"));
  }
}
