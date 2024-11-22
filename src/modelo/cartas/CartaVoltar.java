package modelo.cartas;
import modelo.Jogador;

/* Volta casas no tabuleiro */
public class CartaVoltar extends Carta {
    private int casasPerdidas;

    /* Construtor */
    CartaVoltar(int index, String nome, String descricao, int casasPerdidas) {
        super(index, nome, descricao);
        this.casasPerdidas = casasPerdidas;
    }

    /* Getter (não faz sentido ter um setter, ja que nunca muda) */
    public int getCasasPerdidas() {
        return casasPerdidas;
    }

    /* Função de ação (ultimaCasa é usado para "dar a volta" no tabuleiro) */
    public void acao(Jogador origem, int ultimaCasa) {
        origem.setCasa(origem.getCasa() - casasPerdidas, ultimaCasa);
    }
}
