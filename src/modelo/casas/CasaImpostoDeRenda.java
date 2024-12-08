package modelo.casas;

//  Casa de imposto de renda: o jogador escolhe entre pagar uma porcentagem de seu dinheiro ou um valor fixo
public class CasaImpostoDeRenda extends Casa {
	private int valor;

	// Construtor
	public CasaImpostoDeRenda(String nome, int index, int valor) {
		super(nome, index);		
		this.valor = valor;
	}

	// Getter (não faz sentido existir um setter, ja que nunca muda)
	public int getValor() {
		return valor;
	}
}
