package io.github.agusbattista;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthorCRUDTest {
  private AuthorCRUD crud;
  private List<Author> authors;
  private String name;
  private String biography;

  @BeforeEach
  void setUp() {
    authors = new ArrayList<>();
    crud = new AuthorCRUD(authors);
    name = "  jorge luis borges";
    biography = "Jorge Luis Borges (1899-1986) fue un escritor, ensayista y poeta argentino...";
    crud.createAuthor(name, biography);
  }

  @Test
  void getAutorByExistingNameTest() {
    Author author = crud.getAuthor("Jorge Luis Borges");
    assertNotNull(author);
    assertEquals("Jorge Luis Borges", author.getFullName());
  }

  @Test
  void getAuthorByNonExistingNameTest() {
    assertNull(crud.getAuthor("Julio Cortazar"));
  }

  @Test
  void getAuthorByExistingIdTest() {
    int authorId = authors.get(0).getId();
    Author author = crud.getAuthor(authorId);
    assertNotNull(author);
    assertEquals("Jorge Luis Borges", author.getFullName());
  }

  @Test
  void getAuthorByNonExistingIdTest() {
    assertNull(crud.getAuthor(0));
  }
}
