package modelo.casas;

//  Casa de imposto de renda: o jogador escolhe entre pagar uma porcentagem de seu dinheiro ou um valor fixo
public class CasaImpostoDeRenda extends Casa {
	private int porcentagem;
	private int valor;

	// Construtor
	public CasaImpostoDeRenda(String nome, int index, int coordenadaX, int coordenadaY, int porcentagem, int valor) {
		super(nome, index);		
		this.porcentagem = porcentagem;
		this.valor = valor;
	}

	// Getters (não faz sentido existirem setters, ja que os valores nunca mudam)
	public int getPorcentagem() {
		return porcentagem;
	}

	public int getValor() {
		return valor;
	}
}
