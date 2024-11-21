import java.io.*;
import java.util.ArrayList;

/* Classe que gera a lista de cartas usadas no jogo a partir de um arquivo csv */
public class ListaCartas {
  /* Retorna a lista de cartas do jogo descritas no arquivo passado em caminhoCSV 
   * DEFINIÇÃO DOS TIPOS:
   * - 0: Carta de avançar casas
   * - 1: Carta de voltar casas
   * - 2: Carta de ganhar dinheiro
   * - 3: Carta de perder dinheiro
   * - 4: Carta de pagar ou ir para a cadeia
  */
  public static ArrayList<Carta> geraLista(String caminhoCSV, String separadorCSV) {
    // Armazena as cartas - retorno do metodo
    ArrayList<Carta> cartas = new ArrayList<>();
    int i = 0;

    // Usadas para leitura do CSV
    BufferedReader conteudoCSV = null;
    String linhaCSV = "";

    // Bloco principal: leitura e atribuição de cartas
    try {
      // Processa o arquivo
      conteudoCSV = new BufferedReader(new FileReader(caminhoCSV));
      
      // Consome a primeira linha (que contem os nomes das colunas)
      linhaCSV = conteudoCSV.readLine();
      
      // Consome e atribui os dados da tabela csv 
      while ((linhaCSV = conteudoCSV.readLine()) != null) {
        Carta cartaAtual;

        String[] carta = linhaCSV.split(separadorCSV);

        if (carta[0].equals("0")) { // Carta avançar
          cartaAtual = new CartaAvancar(i, carta[1], carta[2], Integer.parseInt(carta[3]));
        } else if (carta[0].equals("1")) { // Carta voltar
          cartaAtual = new CartaVoltar(i, carta[1], carta[2], Integer.parseInt(carta[3]));
        } else if (carta[0].equals("2")) { // Carta ganhar dinheiro
          cartaAtual = new CartaGanharDinheiro(i, carta[1], carta[2], Integer.parseInt(carta[3]));
        } else if (carta[0].equals("3")) { // Carta perder dinheiro
          cartaAtual = new CartaPerderDinheiro(i, carta[1], carta[2], Integer.parseInt(carta[3]));
        } else if (carta[0].equals("4")) { // Carta pagar ou ir para a cadeia
          cartaAtual = new PagarOuCadeia(i, carta[1], carta[2], Integer.parseInt(carta[3]));
        } else {
          System.out.printf("Tipo nao definido: %s\n", carta[0]);
          return null;
        }

        cartas.add(cartaAtual);
        i++;
      } 
    } // Tratamento de exceções
      catch (FileNotFoundException e) {
        System.out.println("Arquivo CSV nao encontrado.");
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Erro: ArrayIndexOutOfBounds");
    } catch (IOException e) {
        System.out.println("Erro IO");
    } finally {
      if (conteudoCSV != null) {
        try {
          conteudoCSV.close();
        } catch (IOException e) {
          System.out.println("Erro IO");
        }
      }
    }

    return cartas;
  } 
}
