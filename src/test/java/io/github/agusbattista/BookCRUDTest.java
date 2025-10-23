package io.github.agusbattista;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookCRUDTest {
  private BookCRUD bookCrud;
  private Author author;
  private List<Author> authors;
  private String publisher;
  private String description;
  private String title;

  @BeforeEach
  void setUp() {
    bookCrud = new BookCRUD(new ArrayList<>());
    author =
        new Author(
            "Jorge Luis Borges",
            "Jorge Luis Borges (1899-1986) fue un escritor argentino, poeta y ensayista, cuya obra, marcada por laberintos, sueños y la metafísica, lo convirtió en una de las figuras cumbre de la literatura universal.");
    authors = new ArrayList<>(Arrays.asList(author));
    title = "El Aleph";
    publisher = "Debolsillo";
    description =
        "«Vi el Aleph, desde todos los puntos, vi en el Aleph la tierra, y en la tierra otra vez el Aleph y en el Aleph la tierra». La mayoría de los cuentos reunidos en este libro pertenecen al género fantástico. Algunos surgieron, según sostiene el autor, a partir de crónicas policiales, de pinturas o simplemente de la visión de algún conventillo; otro explora el efecto que la inmortalidad causaría en los hombres; hay una glosa al Martín Fierro, sueños sobre la identidad personal y fantasías del tiempo. El cuento 'El Aleph', publicado por primera vez en la revista Sur en 1945 y que en 1949 dio nombre al libro, aborda uno de los temas recurrentes en la literatura de Borges: el infinito. Porque en esa esfera resplandeciente confluyen de un modo asombroso todos los tiempos y todos los espacios.";
    bookCrud.createBook(
        "0303456",
        "Ficciones",
        authors,
        0,
        "Una colección de relatos breves donde Borges explora el infinito, los laberintos y los espejos con brillante precisión filosófica.",
        publisher);
  }

  @Test
  void createBookSuccessfullyTest() {
    bookCrud.createBook("9789875666481", title, authors, 29999.90, description, publisher);
    Book book = bookCrud.getBook(title);
    assertEquals(book.getTitle(), title);
  }

  @Test
  void createBookExceptionTest() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          bookCrud.create();
        });
  }

  @Test
  void getBookByStringTest() {
    Book book = bookCrud.getBook("Ficciones");
    assertEquals(book.getTitle(), "Ficciones");
  }

  @Test
  void authorUpdateByStringTest() {
    List<Author> oldAuthors = bookCrud.getBook("Ficciones").getAuthors();
    List<Author> newAuthors = new ArrayList<>();
    newAuthors.add(new Author("Julio Cortázar", "Bio de Cortázar"));
    assertNotEquals(oldAuthors, newAuthors);
    bookCrud.update("Ficciones", newAuthors);
    assertEquals(newAuthors, bookCrud.getBook("Ficciones").getAuthors());
  }
}
