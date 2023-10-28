import java.io.File;
import java.util.ArrayList;

import utils.CsvReader;

public class Estoque {
  private static final String DiretorioCargaProdutos = App.DiretorioResources + File.separator + "Produtos.csv";
  private static final String SeparadorColunasPadrao = ",";

  private static final ArrayList<Produto> produtos = new ArrayList<Produto>();

  public static void inicializar() {
    ArrayList<String[]> cargaProdutos = CsvReader.getArrayList(DiretorioCargaProdutos, SeparadorColunasPadrao);

    for (String[] dadosProduto : cargaProdutos.subList(1, cargaProdutos.size())) {
      cadastrarProduto(
          Integer.parseInt(dadosProduto[0]),
          dadosProduto[1],
          Double.parseDouble(dadosProduto[2]),
          Integer.parseInt(dadosProduto[3]));
    }
  }

  public static ArrayList<Produto> getListaProdutos() {
    return produtos;
  }

  public static boolean cadastrarProduto(int id, String nome, double preco, int quantidadeEstoque) {
    if (encontrarProduto(nome) != null) {
      return false;
    }

    produtos.add(new Produto(id, nome, preco, quantidadeEstoque));

    return true;
  }

  public static Produto encontrarProduto(int id) {
    for (Produto produto : getListaProdutos()) {
      if (produto.getId() == id) {
        return produto;
      }
    }

    return null;
  }

  public static Produto encontrarProduto(String nome) {
    for (Produto produto : getListaProdutos()) {
      if (produto.getNome().equalsIgnoreCase(nome)) {
        return produto;
      }
    }

    return null;
  }

  public static void baixarEstoque(int id, int quantidade) {
    Produto produto = encontrarProduto(id);

    if (produto == null) {
      System.out.println("\nProduto nao encontrado!");

      return;
    }

    produto.baixarEstoque(quantidade);
  }

  public static void imprimirListaProdutos() {
    System.out.println("\n------------------------------------------------------");
    System.out.println("|                      CATALOGO                      |");
    System.out.println("|----------------------------------------------------|");
    System.out.println("| ID   | NOME             | PRECO      | QUANTIDADE  |");
    System.out.println("|----------------------------------------------------|");

    for (Produto produto : getListaProdutos()) {
      System.out.printf(
          "| %-4d | %-16s | R$%-8.2f | %-12d| \n",
          produto.getId(),
          produto.getNome(),
          produto.getPreco(),
          produto.getQuantidadeEstoque());
    }

    System.out.println("------------------------------------------------------");
  }
}
