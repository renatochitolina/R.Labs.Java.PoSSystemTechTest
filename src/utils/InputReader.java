package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReader {
  private static final Scanner scan = new Scanner(System.in);

  public static String getString(String mensagem) {
    if (mensagem != null)
      System.out.print(mensagem);

    try {
      return scan.nextLine();
    } catch (InputMismatchException e) {
      limparSobrasBuffer();
    }

    return null;
  }

  public static int getInt(String mensagem) {
    if (mensagem != null)
      System.out.print(mensagem);

    try {
      int resultado = scan.nextInt();

      return resultado;
    } catch (InputMismatchException e) {
    } finally {
      limparSobrasBuffer();
    }

    return -1;
  }

  public static double getDouble(String mensagem) {
    if (mensagem != null)
      System.out.print(mensagem);

    try {
      double resultado = scan.nextDouble();

      return resultado;
    } catch (InputMismatchException e) {
    } finally {
      limparSobrasBuffer();
    }

    return -1;
  }

  public static void finalizar() {
    scan.close();
  }

  private static void limparSobrasBuffer() {
    scan.nextLine();
  }
}
