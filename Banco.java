public class Banco {
  private static Singleton instanciaUnica;
  
  /* Construtores */
  private Banco() {};

  public static synchronized Singleton getInstancia() {
    if (instanciaUnica == null)
    instanciaUnica = new Singleton();
    return instanciaUnica;
  }

  /* Transferências */
  void transferencia(Jogador origem, Jogador destino, double valor) {
    if (origem.getDinheiro() - valor >= 0) {
      origem.setDinheiro(origem.getDinheiro() - valor);
      destino.setDinheiro(destino.getDinheiro() + valor);
    } else {
      System.out.println("Jogador origem não tem dinheiro suficiente para transação. Declarando falencia.");
      origem.setFalido();
    }
  }

  void dinheiroParaJogador(Jogador destino, double valor) {
    destino.setDinheiro(destino.getDinheiro() + valor);
  }

  void dinheiroDeJogador(Jogador destino, double valor) {
    destino.setDinheiro(destino.getDinheiro() - valor);
  }

  void dinheiroParaBanco(Jogador origem, double valor) {
    origem.setDinheiro(origem.getDinheiro() - valor);
  }
}