package modelo.cartas;
import controlador.Banco;
import modelo.*;

// Obriga o jogador a pagar ou, se não quiser pagar ou não tiver o dinheiro, ir para a cadeia 
public class CartaPagarOuCadeia extends Carta {
	private int valor;

	// Construtor 
	public CartaPagarOuCadeia(int index, String nome, String descricao, int valor) {
		super(index, nome, descricao);
    	this.valor = valor;
  	}

	// Getter (não faz sentido ter um setter, ja que nunca muda) 
	public int getValor() {
		return valor;
  	}
}
