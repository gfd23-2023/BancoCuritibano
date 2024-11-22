package modelo.cartas;
import modelo.*;

/* Avança casas no tabuleiro */
public class CartaAvancar extends Carta {
  private int casasAvancadas;

  /* Construtor */
  CartaAvancar(int index, String nome, String descricao, int casasAvancadas) {
    super(index, nome, descricao);
    this.casasAvancadas = casasAvancadas;
  }

  /* Getter (não faz sentido ter um setter, ja que nunca muda) */
  public int getCasasAvancadas() {
    return casasAvancadas;
  }

  /* Função de ação (ultimaCasa é usado para "dar a volta" no tabuleiro) */
  public void acao(Jogador origem, int ultimaCasa) {
    origem.setCasa(origem.getCasa() + casasAvancadas, ultimaCasa);
  }
}
