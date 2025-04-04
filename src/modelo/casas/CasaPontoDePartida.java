package modelo.casas;

// Primeira casa do tabuleiro. Ao passar por ela, os jogadores recebem 200 reais.
public class CasaPontoDePartida extends Casa {
	private int salario;

	// Construtor
    public CasaPontoDePartida(String nome, int index, int salario) {
		super(nome, index);
		this.salario = salario;
	}

	// Getter (não faz sentido ter um setter ja que nunca muda) 
	public int getSalario() {
		return salario;
	}
}
