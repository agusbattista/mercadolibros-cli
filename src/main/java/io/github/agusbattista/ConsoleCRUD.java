package io.github.agusbattista;

import java.util.Scanner;

public abstract class ConsoleCRUD<T> {
  protected final Scanner scanner = new Scanner(System.in);

  public abstract void create();

  public abstract void list();

  public abstract void update();

  public abstract void delete();

  public abstract void showOptions();

  protected int readInteger(String message) {
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

  protected double readDouble(String message) {
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

  protected String readString(String message) {
    System.out.println(message);
    return scanner.nextLine();
  }
}
