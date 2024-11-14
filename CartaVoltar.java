public class CartaVoltar extends Carta {
  private int casasPerdidas;

  CartaVoltar(int index, String nome, String descricao, int casasPerdidas) {
    super(index, nome, descricao);
    this.casasPerdidas = casasPerdidas;
  }

  public int getCasasPerdidas() {
    return casasPerdidas;
  }

  public void setCasasPerdidas(int casasPerdidas) {
    this.casasPerdidas = casasPerdidas;
  }

  void acao(Jogador origem) {
    origem.setCasa(origem.getCasa() - casasPerdidas);
  }
}