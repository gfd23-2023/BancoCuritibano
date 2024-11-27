package modelo.cartas;
import modelo.*;

// Faz o jogador perder rodadas
public class CartaEspera extends Carta {
    private int turnosEspera;

    // Construtor
    public CartaEspera(int index, String nome, String descricao, int turnosEspera) {
		super(index, nome, descricao);
        this.turnosEspera = turnosEspera;
    }

    // Getter (não faz sentido ter um setter, ja que nunca muda) 
    public int getTurnosEspera() {
    	return turnosEspera;
    }
}
