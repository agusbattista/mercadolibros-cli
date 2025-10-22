package io.github.agusbattista;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PruebaTest {

  private Prueba unaClaseDePrueba;

  @BeforeEach
  void setUp() {
    unaClaseDePrueba = new Prueba("OK");
  }

  @Test
  void testMostrarMensaje() {
    assertEquals("OK", unaClaseDePrueba.mostrarMensaje());
  }
}
