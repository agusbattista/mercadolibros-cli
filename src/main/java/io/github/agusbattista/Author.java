package io.github.agusbattista;

import io.github.agusbattista.utils.StringFormatter;

public class Author {
  private static int count = 1;
  private int id;
  private String fullName;
  private String biography;

  public Author(String fullName, String biography) {
    this.id = count++;
    setFullName(fullName);
    this.biography = biography;
  }

  public String getFullName() {
    return this.fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = StringFormatter.format(fullName);
  }

  public void setBiography(String biography) {
    this.biography = biography;
  }

  public String getBiography() {
    return this.biography;
  }

  public int getId() {
    return this.id;
  }

  @Override
  public String toString() {
    return "{ id: " + this.getId() + ", Nombre completo: " + this.getFullName() + " }";
  }
}
