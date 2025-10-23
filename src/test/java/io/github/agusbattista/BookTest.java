package io.github.agusbattista;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.agusbattista.exceptions.NegativePriceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {
  private Author author;
  private List<Author> authors;
  private Book book;

  @BeforeEach
  void setUp() {
    author =
        new Author(
            "Jorge Luis Borges",
            "Jorge Luis Borges (1899-1986) fue un escritor argentino, poeta y ensayista, cuya obra, marcada por laberintos, sueños y la metafísica, lo convirtió en una de las figuras cumbre de la literatura universal.");
    authors = new ArrayList<>(Arrays.asList(author));
    String publisher = "Debolsillo";
    String description =
        "«Vi el Aleph, desde todos los puntos, vi en el Aleph la tierra, y en la tierra otra vez el Aleph y en el Aleph la tierra». La mayoría de los cuentos reunidos en este libro pertenecen al género fantástico. Algunos surgieron, según sostiene el autor, a partir de crónicas policiales, de pinturas o simplemente de la visión de algún conventillo; otro explora el efecto que la inmortalidad causaría en los hombres; hay una glosa al Martín Fierro, sueños sobre la identidad personal y fantasías del tiempo. El cuento 'El Aleph', publicado por primera vez en la revista Sur en 1945 y que en 1949 dio nombre al libro, aborda uno de los temas recurrentes en la literatura de Borges: el infinito. Porque en esa esfera resplandeciente confluyen de un modo asombroso todos los tiempos y todos los espacios.";
    book = new Book("9789875666481", "El Aleph", authors, 29999.90, description, publisher);
  }

  @Test
  void setEmptyListOfAuthorsTest() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          book.setAuthors(new ArrayList<>());
        });
  }

  @Test
  void setNullListOfAuthorsTest() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          book.setAuthors(null);
        });
  }

  @Test
  void setNegativePriceTest() {
    assertThrows(
        NegativePriceException.class,
        () -> {
          book.setPrice(-1.0);
        });
  }

  @Test
  void setFreePriceTest() {
    book.setPrice(0);
    assertEquals(0, book.getPrice());
  }
}
