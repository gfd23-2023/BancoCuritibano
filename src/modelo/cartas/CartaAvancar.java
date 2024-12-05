package modelo.cartas;

/* Avança casas no tabuleiro */
public class CartaAvancar extends Carta {
  private int casasAvancadas;

  // Construtor 
  public CartaAvancar(int index, String nome, String descricao, int casasAvancadas) {
    super(index, nome, descricao);
    this.casasAvancadas = casasAvancadas;
  }

  // Getter (não faz sentido ter um setter, ja que nunca muda) 
  public int getCasasAvancadas() {
    return casasAvancadas;
  }
}
