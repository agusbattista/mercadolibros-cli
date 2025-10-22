package io.github.agusbattista;

public class Prueba {

  private String mensaje;

  public Prueba(String mensaje) {
    this.mensaje = mensaje;
  }

  public String mostrarMensaje() {
    return this.mensaje;
  }

  public static void main(String[] args) {
    System.out.println("OK");
  }
}
