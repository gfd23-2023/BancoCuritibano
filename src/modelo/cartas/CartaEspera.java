package modelo.cartas;
public class CartaEspera extends Carta {
    private int turnosEspera;

    // Construtor
    public CartaEspera(String nome, String descricao, int turnosEspera) {
		super(nome, descricao);
        this.turnosEspera = turnosEspera;
    }

    // Getter (não faz sentido ter um setter, ja que nunca muda) 
    public int getTurnosEspera() {
    	return turnosEspera;
    }
}
