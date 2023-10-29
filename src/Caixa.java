import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import utils.InputReader;
import utils.MonetaryHelper;

public class Caixa {
  private static final List<Double> valoresMonetarios = List.of(
      200d, 100d, 50d, 20d, 10d, 5d, 2d, 1d, 0.5, 0.25, 0.10, 0.05, 0.01);

  public static boolean pagar(double valorTotal) {
    boolean pago = false;

    boolean sair = false;
    do {
      descreverOpcoesPagamento();

      switch (InputReader.getInt("ESCOLHA UMA OPCAO DE PAGAMENTO: ")) {
        case 0:
          sair = true;
          break;
        case 1:
          sair = pago = pagarDinheiro(valorTotal);
          break;
        case 2:
        case 3:
          sair = pago = true;
          break;
        default:
          System.out.println("\nOPCAO DE PAGAMENTO INVALIDA!");
          break;
      }
    } while (!sair);

    return pago;
  }

  private static List<Double> getValoresMonetarios() {
    return valoresMonetarios;
  }

  private static void descreverOpcoesPagamento() {
    System.out.println("\n----:::::::::::::::::----");
    System.out.println("----::: PAGAMENTO :::----");
    System.out.println("----:::::::::::::::::----");
    System.out.println("--- 1 DINHEIRO ----------");
    System.out.println("--- 2 CARTAO ------------");
    System.out.println("--- 3 PIX ---------------");
    System.out.println("--- 0 CANCELAR ----------");
    System.out.println("-------------------------\n");
  }

  private static boolean pagarDinheiro(double valorTotal) {
    double valorPago = InputReader.getDouble("\nInforme o valor pago em dinheiro: ");

    if (valorPago < valorTotal) {
      System.out.println("\nValor pago em dinheiro invalido ou inferior ao esperado.");

      return false;
    }

    if (valorPago > valorTotal) {
      double valorTroco = valorPago - valorTotal;

      LinkedHashMap<Double, Integer> trocoDinheiro = calcularTrocoDinheiro(valorTroco);

      imprimirTrocoDinheiro(valorTroco, trocoDinheiro);
    }

    return true;
  }

  private static LinkedHashMap<Double, Integer> calcularTrocoDinheiro(double valorTroco) {
    BigDecimal valorTrocoRestanteAltaPrecisao = MonetaryHelper.getValorAltaPrecisao(valorTroco);

    LinkedHashMap<Double, Integer> trocoDinheiro = new LinkedHashMap<>();

    for (double valorMonetario : getValoresMonetarios()) {
      BigDecimal valorMonetarioAltaPrecisao = MonetaryHelper.getValorAltaPrecisao(valorMonetario);

      if (valorMonetarioAltaPrecisao.compareTo(valorTrocoRestanteAltaPrecisao) > 0)
        continue;

      int frequencia = valorTrocoRestanteAltaPrecisao.divide(valorMonetarioAltaPrecisao, RoundingMode.FLOOR).intValue();

      valorTrocoRestanteAltaPrecisao = valorTrocoRestanteAltaPrecisao.remainder(valorMonetarioAltaPrecisao);

      trocoDinheiro.put(valorMonetario, frequencia);
    }

    return trocoDinheiro;
  }

  private static void imprimirTrocoDinheiro(double valorTroco, LinkedHashMap<Double, Integer> trocoDinheiro) {
    System.out.println("\n-----------------------------------------");
    System.out.println("|                 TROCO                 |");
    System.out.println("|---------------------------------------|");

    for (Map.Entry<Double, Integer> valorMonetario : trocoDinheiro.entrySet()) {
      String tipoMonetario = valorMonetario.getKey() > 1
          ? "cedula(s)"
          : "moeda(s) ";

      System.out.printf(
          "| %-7d %-12s R$%-14.2f |\n",
          valorMonetario.getValue(),
          tipoMonetario,
          valorMonetario.getKey());
    }

    System.out.println("|---------------------------------------|");
    System.out.printf("| %23s%-14.2f |\n", "R$", valorTroco);
    System.out.println("-----------------------------------------");
  }
}
