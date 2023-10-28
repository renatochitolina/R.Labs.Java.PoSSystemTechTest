import java.io.File;

import utils.InputReader;

public class App {
  public static final String DiretorioResources = System.getProperty("user.dir") + File.separator + "resources";

  public static void main(String[] args) throws Exception {
    Estoque.inicializar();

    boolean sair = false;
    do {
      sair = Menu.selecionarOpcao();
    } while (!sair);

    InputReader.finalizar();
  }
}
