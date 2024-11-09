public class Banco {
  private double capital;

  /* Construtores */
  Banco() {};

  Banco (double capital) {
    this.capital = capital;
  }
  /* Getter e Setter */
  double getCapital() {
    return capital;
  }

   void setCapital(double capital) {
    this.capital = capital;
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
    capital -= valor;
  }

  void dinheiroParaBanco(Jogador origem, double valor) {
    capital += valor;
    origem.setDinheiro(origem.getDinheiro() - valor);
  }
}