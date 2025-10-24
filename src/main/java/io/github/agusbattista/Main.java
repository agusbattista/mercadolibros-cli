package io.github.agusbattista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static void printMenu() {
    System.out.println("==Menú principal==");
    System.out.println("Ingrese una de las siguientes opciones:");
    System.out.println("1) Para CRUD de autores");
    System.out.println("2) Para CRUD de libros");
    System.out.println("0) Para finalizar el programa");
  }

  private static int readInput(Scanner scanner) {
    String input = scanner.nextLine();
    int option;
    try {
      option = Integer.parseInt(input);
    } catch (NumberFormatException error) {
      option = -1;
    }
    return option;
  }

  private static void authorsCrudMenu(AuthorCRUD authorsCrud, BookCRUD booksCrud, Scanner scanner) {
    int option;
    do {
      authorsCrud.showOptions();
      option = readInput(scanner);
      switch (option) {
        case 1 -> authorsCrud.create();
        case 2 -> authorsCrud.list();
        case 3 -> authorsCrud.update();
        case 4 -> {
          System.out.println("A continuación se listarán los autores que podría intentar eliminar");
          authorsCrud.list();
          Author author = authorsCrud.searchByOption();
          System.out.println("Corroborando que el autor no esté asociado con ningún libro...");
          List<Book> booksForAuthor = booksCrud.getBooksByAutor(author);
          if (!booksForAuthor.isEmpty()) {
            System.out.println(
                "No se puede eliminar el autor. Elimine sus libros y vuelva a intenarlo");
            System.out.println("Libros en los que aparece...");
            System.out.println(booksForAuthor.toString());
          } else {
            System.out.println("El autor puede eliminarse ya que no está asociado a ningún libro");
            authorsCrud.delete(author);
          }
        }
        case 0 -> System.out.println("Volviendo al menú anterior...");
        default -> System.out.println("Opción inválida");
      }
    } while (option != 0);
  }

  private static void addAuthorToList(
      AuthorCRUD authorsCrud, BookCRUD booksCrud, List<Author> bookAuthors) {
    String autorFullName = booksCrud.readString("Ingrese el nombre completo del autor:");
    Author author = authorsCrud.getAuthor(autorFullName);
    if (author == null) {
      String authorBiography = booksCrud.readString("Ingrese la biografía del autor:");
      authorsCrud.createAuthor(autorFullName, authorBiography);
      author = authorsCrud.getAuthor(autorFullName);
    }
    bookAuthors.add(author);
  }

  private static List<Author> addAuthorsToList(
      AuthorCRUD authorsCrud, BookCRUD booksCrud, Scanner scanner) {
    List<Author> bookAuthors = new ArrayList<>();
    System.out.println("Carga de autores del libro...");
    int option;
    do {
      System.out.println("Ingrese una de las siguientes opciones:");
      System.out.println("1) Para crear/agregar un autor");
      System.out.println("2) Para finalizar carga de autores");
      option = readInput(scanner);
      switch (option) {
        case 1 -> addAuthorToList(authorsCrud, booksCrud, bookAuthors);
        case 2 -> {
          if (!bookAuthors.isEmpty()) {
            System.out.println("Carga de autor/es finalizada");
          }
        }
        default -> System.out.println("Opción inválida");
      }
    } while (option != 2);
    return bookAuthors;
  }

  private static void booksCrudMenu(AuthorCRUD authorsCrud, BookCRUD booksCrud, Scanner scanner) {
    int option;
    do {
      booksCrud.showOptions();
      option = readInput(scanner);
      switch (option) {
        case 1 -> {
          List<Author> bookAuthors = addAuthorsToList(authorsCrud, booksCrud, scanner);
          if (!bookAuthors.isEmpty()) {
            booksCrud.create(bookAuthors);
          } else {
            System.out.println("Fallo en la carga de autores. Vuelva a intentarlo");
          }
        }
        case 2 -> booksCrud.list();
        case 3 -> {
          int subOption;
          do {
            System.out.println("Actualización de un libro...");
            System.out.println("Ingrese una de las siguientes opciones:");
            System.out.println("1) Actualizar autor/es de un libro");
            System.out.println("2) Actualizar datos del libro");
            System.out.println("3) Para finalizar la modificación del libro");
            subOption = readInput(scanner);
            switch (subOption) {
              case 1 -> {
                List<Author> bookAuthors = addAuthorsToList(authorsCrud, booksCrud, scanner);
                if (!bookAuthors.isEmpty()) {
                  booksCrud.list();
                  System.out.println(
                      "Elija a que libro listado quiere asignar el autor/los autores");
                  Book book = booksCrud.searchByOption();
                  if (book != null) {
                    book.setAuthors(bookAuthors);
                    System.out.println(
                        "Autor/autores asignado/s correctamente al libro seleccionado");
                  } else {
                    System.out.println(
                        "Libro no encontrado, falló la carga de autores. Vuelva a intentarlo");
                  }
                } else {
                  System.out.println(
                      "Fallo en la carga de autores. Debe cargar al menos uno. Vuelva a intentarlo");
                }
              }
              case 2 -> booksCrud.update();
              case 3 -> System.out.println("Modificación de libro finalizada");
              default -> System.out.println("Opción inválida");
            }
          } while (subOption != 3);
        }
        case 4 -> booksCrud.delete();
        case 0 -> System.out.println("Volviendo al menú anterior...");
        default -> System.out.println("Opción inválida");
      }
    } while (option != 0);
  }

  public static void main(String[] args) {
    final List<Author> authors = new ArrayList<>();
    final List<Book> books = new ArrayList<>();
    final AuthorCRUD authorsCrud = new AuthorCRUD(authors);
    final BookCRUD booksCrud = new BookCRUD(books);
    int option;
    Scanner scanner = new Scanner(System.in);
    do {
      printMenu();
      option = readInput(scanner);
      switch (option) {
        case 1 -> authorsCrudMenu(authorsCrud, booksCrud, scanner);
        case 2 -> booksCrudMenu(authorsCrud, booksCrud, scanner);
        case 0 -> System.out.println("Programa finalizado");
        default -> System.out.println("Opción inválida");
      }
    } while (option != 0);
    scanner.close();
  }
}
