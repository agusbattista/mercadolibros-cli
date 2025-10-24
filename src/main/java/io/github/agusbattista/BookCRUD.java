package io.github.agusbattista;

import java.util.ArrayList;
import java.util.List;

public class BookCRUD extends ConsoleCRUD<Book> {

  private final List<Book> books;

  public BookCRUD(List<Book> books) {
    this.books = books;
  }

  @Override
  public void create() {
    throw new IllegalArgumentException("Debe proveer una lista con uno o más autores");
  }

  public void create(List<Author> authors) {
    String isbn = this.readString("Ingrese el ISBN del libro:");
    String title = this.readString("Ingrese el título del libro:");
    double price = this.readDouble("Ingrese el precio del libro:");
    String description = this.readString("Ingrese una descripción del libro:");
    String publisher = this.readString("Ingrese la editorial del libro:");
    this.createBook(isbn, title, authors, price, description, publisher);
    System.out.println("Libro creado exitosamente");
  }

  void createBook(
      String isbn,
      String title,
      List<Author> authors,
      double price,
      String description,
      String publisher) {
    Book book = new Book(isbn, title, authors, price, description, publisher);
    this.books.add(book);
  }

  @Override
  public void list() {
    if (this.books == null || this.books.isEmpty()) {
      System.out.println("La lista de libros está vacía");
    } else {
      System.out.println("Listando libros...");
      System.out.println(books.toString());
    }
  }

  Book getBook(String title) {
    return this.books.stream().filter(b -> b.getTitle().equals(title)).findFirst().orElse(null);
  }

  Book getBook(int id) {
    return this.books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
  }

  Book searchByOption() {
    System.out.println("¿Desea buscar el libro por índice o por título completo?");
    System.out.println("1) Índice");
    System.out.println("2) Título completo");
    Book book;
    int option = this.optionSelection();
    if (option == 1) {
      int id = this.readInteger("Ingrese el id:");
      book = getBook(id);
    } else {
      String title = this.readString("Ingrese el título completo del libro que desea modificar:");
      book = getBook(title);
    }
    return book;
  }

  @Override
  public void update() {
    System.out.println("Listando libros que puede actualizar...");
    this.list();
    Book book = searchByOption();
    if (book == null) {
      System.out.println("No se ha encontrado el libro");
    } else {
      System.out.println("¿Desea cambiar el ISBN?");
      printTwoOptions();
      int option = this.optionSelection();
      if (option == 1) {
        String isbn = readString("Ingrese el ISBN:");
        book.setIsbn(isbn);
        System.out.println("ISBN actualizado con éxito");
      }
      System.out.println("¿Desea cambiar el título");
      printTwoOptions();
      option = this.optionSelection();
      if (option == 1) {
        String title = readString("Ingrese el título:");
        book.setTitle(title);
        System.out.println("Título actualizado con éxito");
      }
      System.out.println("¿Desea cambiar el precio?");
      printTwoOptions();
      option = this.optionSelection();
      if (option == 1) {
        double price = readDouble("Ingrese el precio:");
        book.setPrice(price);
        System.out.println("Precio actualizado");
      }
      System.out.println("¿Desea cambiar la descripción?");
      printTwoOptions();
      option = this.optionSelection();
      if (option == 1) {
        String description = readString("Ingrese la descripción:");
        book.setDescription(description);
        System.out.println("Descripción actualizada con éxito");
      }
      System.out.println("¿Desea cambiar la editorial?");
      printTwoOptions();
      option = this.optionSelection();
      if (option == 1) {
        String publisher = readString("Ingrese la editorial:");
        book.setPublisher(publisher);
        System.out.println("Editorial actualizada con éxito");
      }
    }
  }

  public void update(int id, List<Author> authors) {
    this.getBook(id).setAuthors(authors);
  }

  public void update(String title, List<Author> authors) {
    this.getBook(title).setAuthors(authors);
  }

  @Override
  public void delete() {
    System.out.println("Listando libros que puede eliminar...");
    this.list();
    Book book = this.searchByOption();
    if (book == null) {
      System.out.println("No se ha encontrado el libro");
    } else {
      this.books.remove(book);
      System.out.println("Libro eliminado exitosamente");
    }
  }
  
  public List<Book> getBooksByAutor(Author author) {
    return this.books.stream().filter(book -> book.getAuthors().contains(author)).toList();  
  }

  @Override
  public void showOptions() {
    System.out.println("==CRUD de Libros==");
    System.out.println("Elija una de las siguientes opciones:");
    System.out.println("1) Crear libro");
    System.out.println("2) Listar libros");
    System.out.println("3) Actualizar libro");
    System.out.println("4) Eliminar libro");
    System.out.println("0) Volver al menú anterior / Salir");
  }
}
