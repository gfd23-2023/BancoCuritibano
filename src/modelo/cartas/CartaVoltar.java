package modelo.cartas;
import modelo.*;

// Volta casas no tabuleiro 
public class CartaVoltar extends Carta {
    private int casasPerdidas;

    // Construtor 
    public CartaVoltar(int index, String nome, String descricao, int casasPerdidas) {
        super(index, nome, descricao);
        this.casasPerdidas = casasPerdidas;
    }

    // Getter (não faz sentido ter um setter, ja que nunca muda) 
    public int getCasasPerdidas() {
        return casasPerdidas;
    }
}