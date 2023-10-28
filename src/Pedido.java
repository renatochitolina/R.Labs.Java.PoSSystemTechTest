import java.util.ArrayList;

import utils.InputReader;

public class Pedido {
  private static final ArrayList<PedidoItem> itens = new ArrayList<PedidoItem>();

  public static ArrayList<PedidoItem> getListaItens() {
    return itens;
  }

  public static void adicionarItem() {
    String nome = InputReader.getString("\nInforme o nome do produto: ");

    Produto produto = Estoque.encontrarProduto(nome);

    if (produto == null) {
      System.out.println("\nProduto nao encontrado!");

      return;
    }

    int quantidade = InputReader.getInt("Informe a quantidade: ");

    boolean sucesso = adicionarItem(produto, quantidade);

    if (sucesso)
      System.out.printf("\nAdicionado %d %s com sucesso!\n", quantidade, produto.getNome());
    else
      System.out.printf("\nNao foi possivel adicionar %d %s!\n", quantidade, produto.getNome());
  }

  public static void imprimirPedido() {
    ArrayList<PedidoItem> listaItens = getListaItens();

    System.out.println("\n------------------------------------------------------------------------");
    System.out.println("|                                PEDIDO                                |");
    System.out.println("|----------------------------------------------------------------------|");
    System.out.println("| ID   | NOME             | PRECO UN     | QUANTIDADE    | TOTAL       |");
    System.out.println("|----------------------------------------------------------------------|");

    if (listaItens.isEmpty()) {
      System.out.println("| Ainda nao existem itens no pedido!                                   |");
    } else {
      for (PedidoItem itemPedido : listaItens) {
        System.out.printf(
            "| %-4d | %-16s | R$%-10.2f | %-13d | R$%-10.2f|\n",
            itemPedido.getProduto().getId(),
            itemPedido.getProduto().getNome(),
            itemPedido.getProduto().getPreco(),
            itemPedido.getQuantidade(),
            itemPedido.getValorTotal());
      }
    }

    System.out.println("|----------------------------------------------------------------------|");
    System.out.printf("| %59s%-9.2f |\n", "R$", getValorTotal());
    System.out.println("------------------------------------------------------------------------");
  }

  public static double getValorTotal() {
    double valorTotal = 0;

    for (PedidoItem itemPedido : getListaItens())
      valorTotal += itemPedido.getValorTotal();

    return valorTotal;
  }

  private static boolean adicionarItem(Produto produto, int quantidade) {
    for (PedidoItem itemPedido : getListaItens()) {
      if (itemPedido.getProduto().getNome().equalsIgnoreCase(produto.getNome())) {
        itemPedido.setQuantidade(itemPedido.getQuantidade() + quantidade);

        Estoque.baixarEstoque(itemPedido.getProduto().getId(), quantidade);

        return true;
      }
    }

    itens.add(new PedidoItem(produto, quantidade));

    Estoque.baixarEstoque(produto.getId(), quantidade);

    return true;
  }
}
