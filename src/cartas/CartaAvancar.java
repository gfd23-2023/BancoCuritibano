public class CartaAvancar extends Carta {
  int casasAvancadas;

  CartaAvancar(int index, String nome, String descricao, int casasAvancadas) {
    super(index, nome, descricao);
    this.casasAvancadas = casasAvancadas;
  }

  void acao(Jogador origem) {
    origem.setCasa(origem.getCasa() + casasAvancadas);
  }
}