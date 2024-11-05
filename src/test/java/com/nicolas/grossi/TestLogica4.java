package com.nicolas.grossi;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestLogica4 {
  @Test
  @Order(1)
  public void TEXT() {
    String input = "0003";
    String expected = "0004";
    String res = Main.autoCompletarZeros(Main.stringToInt(Main.aumentar_devuelve_si_collapsa(input).getRta()) ,input.length());
    assertEquals(expected, res);
  }
  @Test
  @Order(1)
  public void firstCharPosition() {
    String actual = "123A123";
    int expected = 3;
    assertEquals(expected, Main.firstCharacterPosition("123A123"));
  }
  @Test
  @Order(2)
  public void maxNumber4Digits() {
    int digits = 4;
    int expected = 9999;
    assertEquals(expected, Main.generateMaxNumber(digits));
  }
  @Test
  @Order(3)
  public void maxNumber3Digits() {
    int digits = 3;
    int expected = 999;
    assertEquals(expected, Main.generateMaxNumber(digits));
  }
  @Test
  @Order(6)
  public void aumentar() {
    String input = "0001";
    String expected = "0002";
    assertEquals(expected, Main.generatePlate(input));
  }
  @Test
  @Order(6)
  public void aumentar2() {
    String input = "0003";
    String expected = "0004";
    assertEquals(expected, Main.generatePlate(input));
  }
  @Test
  @Order(6)
  public void aumentar3() {
    String input = "9029";
    String expected = "9030";
    assertEquals(expected, Main.generatePlate(input));
  }
  @Test
  @Order(6)
  public void aumentar4() {
    String input = "9A09";
    String expected = "9A10";
    String result = Main.generatePlate(input);
    assertEquals(expected,result );
  }
  @Test
  @Order(6)
  public void esTodos9() {
    String input = "9";
    boolean expected = true;
    assertEquals(expected, Main.esTodos9s(input));
  }
  @Test
  @Order(6)
  public void NosonTodos9() {
    String input = "09";
    boolean expected = false;
    assertEquals(expected, Main.esTodos9s(input));
  }
  @Test
  @Order(6)
  public void esTodos999() {
    String input = "99999999";
    boolean expected = true;
    assertEquals(expected, Main.esTodos9s(input));
  }
  @Test
  @Order(6)
  public void esTodosZ() {
    String input = "Z";
    boolean expected = true;
    assertEquals(expected, Main.esTodosZs(input));
  }
  @Test
  @Order(6)
  public void esTodosZZZ() {
    String input = "ZZZZZZZZZZZZ";
    boolean expected = true;
    assertEquals(expected, Main.esTodosZs(input));
  }
  @Test
  @Order(7)
  public void nueveCambiaDiez() {
    String input = "0009";
    String expected = "0010";
    assertEquals(expected, Main.generatePlate(input));
  }
  @Test
  @Order(8)
  public void nnacien() {
    String input = "0099";
    String expected = "0100";
    assertEquals(expected, Main.generatePlate(input));
  }
  @Test
  @Order(10)
  public void testNumericToAlphabeticTransition() {
    String input = "9999";
    String expected = "000A";
    assertEquals(expected, Main.generatePlate(input));
  }
  @Test
  @Order(11)
  public void testSingleLetterIncrement() {
    String input = "999A";
    String expected = "000B";
    assertEquals(expected, Main.generatePlate(input));
  }

  @Test
  @Order(12)
  public void testDoubleLetterIncrement() {
    String input = "999AA";
    String expected = "000AB";
    assertEquals(expected, Main.generatePlate(input));
  }

  @Test
  @Order(13)
  public void testToTripleLetterIncrement() {
    String input = "99ZZ";
    String expected = "00AA";
    assertEquals(expected, Main.generatePlate(input));
  }
}
