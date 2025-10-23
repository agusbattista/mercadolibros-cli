package io.github.agusbattista.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StringFormatterTest {

  @Test
  void formatEmptyStringTest() {
    assertEquals("", StringFormatter.format(""));
  }

  @Test
  void formatStringWithOnlySpacesTest() {
    assertEquals("", StringFormatter.format("   "));
  }

  @Test
  void formatSingleWordTest() {
    assertEquals("Café", StringFormatter.format("  café  "));
  }

  @Test
  void formatAlreadyFormattedStringTest() {
    assertEquals("Café Cabrales", StringFormatter.format("Café Cabrales"));
  }

  @Test
  void formatStringWithTabsTest() {
    assertEquals("Té De Tilo", StringFormatter.format("\t té \t de \t tilo \t"));
  }

  @Test
  void formatMultipleWordsTest() {
    assertEquals("Café Con Leche", StringFormatter.format("  caFé    con   LECHE  "));
  }
}
