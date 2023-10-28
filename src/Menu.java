import utils.InputReader;

public class Menu {
  public static boolean selecionarOpcao() {
    descreverOpcoes();

    switch (InputReader.getInt("ESCOLHA UMA OPCAO: ")) {
      case 0:
        return true;
      case 1:
        Estoque.imprimirListaProdutos();
        break;
      case 2:
        Pedido.adicionarItem();
        break;
      case 3:
        Pedido.imprimirPedido();
        break;
      default:
        System.out.println("OPCAO INVALIDA!!!");
    }

    return false;
  }

  private static void descreverOpcoes() {
    System.out.println("\n----:::::::::::::::::----");
    System.out.println("----::: SUPER POS :::----");
    System.out.println("----:::::::::::::::::----");
    System.out.println("--- 1 MOSTRAR CATALOGO --");
    System.out.println("--- 2 LANCAR ITEM -------");
    System.out.println("--- 3 VER PEDIDO --------");
    System.out.println("--- 0 ENCERRAR ----------");
    System.out.println("-------------------------\n");
  }
}
