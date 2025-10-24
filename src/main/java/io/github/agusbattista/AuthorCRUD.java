package io.github.agusbattista;

import java.util.List;

public class AuthorCRUD extends ConsoleCRUD<Author> {
  private final List<Author> authors;

  public AuthorCRUD(List<Author> authors) {
    this.authors = authors;
  }

  @Override
  public void create() {
    String fullName = this.readString("Ingrese el nombre completo del autor:");
    String biography = this.readString("Ingrese una breve biografía del autor:");
    this.createAuthor(fullName, biography);
    System.out.println("Autor creado exitosamente");
  }

  void createAuthor(String fullName, String biography) {
    this.authors.add(new Author(fullName, biography));
  }

  @Override
  public void list() {
    if (this.authors == null || this.authors.isEmpty()) {
      System.out.println("La lista de autores está vacía");
    } else {
      System.out.println("Listando autores...");
      System.out.println(authors.toString());
    }
  }

  Author getAuthor(String fullName) {
    return this.authors.stream()
        .filter(a -> a.getFullName().equals(fullName))
        .findFirst()
        .orElse(null);
  }

  Author getAuthor(int id) {
    return this.authors.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
  }

  Author searchByOption() {
    System.out.println("¿Desea buscar el autor por índice o por nombre completo?");
    System.out.println("1) Índice");
    System.out.println("2) Nombre completo");
    Author author;
    int option = this.optionSelection();
    if (option == 1) {
      int id = this.readInteger("Ingrese el id:");
      author = getAuthor(id);
    } else {
      String fullName =
          this.readString("Ingrese el nombre completo del autor que desea modificar o eliminar:");
      author = getAuthor(fullName);
    }
    return author;
  }

  @Override
  public void update() {
    System.out.println("A continuación se listaran los autores que puede modificar");
    this.list();
    boolean modified = false;
    Author author = this.searchByOption();
    if (author == null) {
      System.out.println("No se ha encontrado el autor");
    } else {
      System.out.println("¿Desea cambiar el nombre?");
      printTwoOptions();
      int option = this.optionSelection();
      if (option == 1) {
        modified = true;
        String newFullName = this.readString("Ingrese el nuevo nombre:");
        author.setFullName(newFullName);
        System.out.println("Nombre del autor actualizado con éxito");
      }
      System.out.println("¿Desea cambiar la biografía?");
      printTwoOptions();
      option = this.optionSelection();
      if (option == 1) {
        modified = true;
        String biography = this.readString("Ingrese la biografía:");
        author.setBiography(biography);
        System.out.println("Biografía actualizada con éxito");
      }
    }
    if (modified) {
      System.out.println("Autor modificado exitosamente");
    }
  }

  @Override
  public void delete() {
    System.out.println("A continuación se listaran los autores que puede eliminar");
    this.list();
    Author author = this.searchByOption();
    this.delete(author);
  }

  public void delete(Author author) {
    if (author == null) {
      System.out.println("No se ha encontrado el autor");
    } else {
      this.authors.remove(author);
      System.out.println("Autor eliminado exitosamente");
    }
  }

  @Override
  public void showOptions() {
    System.out.println("==CRUD de Autores==");
    System.out.println("Elija una de las siguientes opciones:");
    System.out.println("1) Crear autor");
    System.out.println("2) Listar autores");
    System.out.println("3) Actualizar autor");
    System.out.println("4) Eliminar autor");
    System.out.println("0) Volver al menú anterior / Salir");
  }
}
