package io.github.agusbattista;

import io.github.agusbattista.exceptions.NegativePriceException;
import java.util.List;

public class Book {
  private static int count = 1;
  private int id;
  private String isbn;
  private String title;
  private List<Author> authors;
  private double price;
  private String description;
  private String publisher;

  public Book(
      String isbn,
      String title,
      List<Author> authors,
      double price,
      String description,
      String publisher) {
    this.setPrice(price);
    this.setAuthors(authors);
    this.id = count++;
    this.isbn = isbn;
    this.title = title;
    this.description = description;
    this.publisher = publisher;
  }

  public void setPrice(double price) {
    if (price < 0) {
      throw new NegativePriceException("El precio no puede ser negativo");
    }
    this.price = price;
  }

  public void setAuthors(List<Author> authors) {
    if (authors == null || authors.isEmpty()) {
      throw new IllegalArgumentException(
          "El libro debe tener al menos un autor. Utiliza una lista de autores válida");
    }
    this.authors = List.copyOf(authors);
  }

  public double getPrice() {
    return this.price;
  }

  public int getId() {
    return this.id;
  }

  public String getIsbn() {
    return this.isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Author> getAuthors() {
    return this.authors;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPublisher() {
    return this.publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  @Override
  public String toString() {
    return "{ "
        + "id: "
        + this.getId()
        + ", ISBN: "
        + this.getIsbn()
        + ", Título: "
        + this.title
        + ", Autor/es: "
        + this.getAuthors().toString()
        + ", Editorial: "
        + this.getPublisher()
        + " }";
  }
}
