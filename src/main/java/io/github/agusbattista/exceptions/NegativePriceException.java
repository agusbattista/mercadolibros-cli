package io.github.agusbattista.exceptions;

public class NegativePriceException extends RuntimeException {
  public NegativePriceException(String message) {
    super(message);
  }
}
