package modelo.cartas;

// Tira dinheiro do jogador 
public class CartaPerderDinheiro extends Carta {
    private int valor;

    // Construtor 
    public CartaPerderDinheiro(String nome, String descricao, int valor) {
        super(nome, descricao);
        this.valor = valor;
    }

    // Getter (não faz sentido ter um setter, ja que nunca muda) 
    public int getValor() {
    	return valor;
    }   
}
