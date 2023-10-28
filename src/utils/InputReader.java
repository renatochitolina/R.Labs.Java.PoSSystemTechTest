package utils;

import java.util.Scanner;

public class InputReader {
  private static final Scanner scan = new Scanner(System.in);

  public static String getString(String mensagem) {
    if (mensagem != null)
      System.out.print(mensagem);

    return scan.nextLine();
  }

  public static int getInt(String mensagem) {
    if (mensagem != null)
      System.out.print(mensagem);

    int resultado = scan.nextInt();

    limparSobrasBuffer();

    return resultado;
  }

  public static void finalizar() {
    scan.close();
  }

  private static void limparSobrasBuffer() {
    scan.nextLine();
  }
}
