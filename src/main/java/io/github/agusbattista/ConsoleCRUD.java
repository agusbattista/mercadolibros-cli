package io.github.agusbattista;

import java.util.Scanner;

public abstract class ConsoleCRUD<T> implements InputConsoleInterface {
  protected final Scanner scanner = new Scanner(System.in);

  public abstract void create();

  public abstract void list();

  public abstract void update();

  public abstract void delete();

  public abstract void showOptions();

  public int readInteger(String message) {
    while (true) {
      try {
        System.out.println(message);
        String line = scanner.nextLine();
        return Integer.parseInt(line.trim());
      } catch (NumberFormatException e) {
        System.out.println("Debe ingresar un número entero");
      }
    }
  }

  public double readDouble(String message) {
    while (true) {
      try {
        System.out.println(message);
        String line = scanner.nextLine();
        return Double.parseDouble(line.trim());
      } catch (NumberFormatException e) {
        System.out.println("Debe ingresar un número decimal (utilice punto)");
      }
    }
  }

  public String readString(String message) {
    System.out.println(message);
    return scanner.nextLine();
  }

  int optionSelection() {
    int option;
    do {
      option = this.readInteger("Ingrese una opción válida (1 o 2):");
    } while (option != 1 && option != 2);
    return option;
  }

  protected void printTwoOptions() {
    System.out.println("1) Sí");
    System.out.println("2) No");
  }
}
