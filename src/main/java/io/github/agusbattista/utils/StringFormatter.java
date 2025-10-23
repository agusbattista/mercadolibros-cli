package io.github.agusbattista.utils;

public class StringFormatter {
  private StringFormatter() {}

  private static String convertToLowerCase(String text) {
    return text.trim().toLowerCase();
  }

  private static String[] splitString(String text) {
    return text.split("\\s+");
  }

  private static String buildResult(String[] words) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      if (!word.isBlank()) {
        String upperCaseFirstCharacterWord = word.substring(0, 1).toUpperCase() + word.substring(1);
        result.append(upperCaseFirstCharacterWord);
      }
      if (i < words.length - 1) {
        result.append(" ");
      }
    }
    return result.toString();
  }

  public static String format(String text) {
    String lowercase = convertToLowerCase(text);
    String[] words = splitString(lowercase);
    return buildResult(words);
  }
}
