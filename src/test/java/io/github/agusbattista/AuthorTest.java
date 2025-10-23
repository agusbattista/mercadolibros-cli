package io.github.agusbattista;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthorTest {
  private Author author;

  @BeforeEach
  void setUp() {
    String name = "  jorge luis borges";
    String biography =
        "Jorge Luis Borges (1899-1986) fue un escritor, ensayista y poeta argentino, considerado una de las figuras más importantes e influyentes de la literatura mundial del siglo XX.";
    author = new Author(name, biography);
  }

  @Test
  void getFullNameTest() {
    assertEquals("Jorge Luis Borges", author.getFullName());
  }
}
