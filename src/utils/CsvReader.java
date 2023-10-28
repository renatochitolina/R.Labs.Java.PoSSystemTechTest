package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {
  public static ArrayList<String[]> getArrayList(String enderecoArquivo, String separadorColunas) {
    ArrayList<String[]> resultado = new ArrayList<>();

    try (BufferedReader leitor = new BufferedReader(new FileReader(enderecoArquivo))) {
      String linha = "";

      while ((linha = leitor.readLine()) != null) {
        resultado.add(linha.split(separadorColunas));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return resultado;
  }
}
