package modelo.cartas;

// Dá dinheiro para o jogador 
public class CartaGanharDinheiro extends Carta {
	private int valor;

    // Construtor
    public CartaGanharDinheiro(int index, String nome, String descricao, int valor) {
        super(index, nome, descricao);
        this.valor = valor;
    }

    // Getter (não faz sentido ter um setter, ja que nunca muda)
    public int getValor() {
        return valor;
    }
}
