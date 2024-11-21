public class CartaEspera extends Carta {
  private int turnosEspera;

  /* Construtor */
  CartaEspera(int index, String nome, String descricao, int turnosEspera) {
    super(index, nome, descricao);
    this.turnosEspera = turnosEspera;
  }

  /* Getter e Setter */
  public int getTurnosEspera() {
    return turnosEspera;
  }

  public void setTurnosEspera(int turnosEspera) {
    this.turnosEspera = turnosEspera;
  }

  void acao(Jogador origem) {
    origem.setEsperando(true);
  }

}
