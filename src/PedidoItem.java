public class PedidoItem {
  private Produto produto;
  private double preco;
  private int quantidade;

  public PedidoItem(Produto produto, int quantidade) {
    this.produto = produto;
    this.quantidade = quantidade;

    this.preco = produto.getPreco();
  }

  public Produto getProduto() {
    return produto;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public double getPreco() {
    return preco;
  }

  public double getValorTotal() {
    return getQuantidade() * getPreco();
  }
}
