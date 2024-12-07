package controlador;

// Classe banco (Singleton) 
public class Banco {
	private static Banco instanciaUnica;
	private static Jogo instanciaJogo;
	
	public void getInstanciaJogo() {
		this.instanciaJogo = Jogo.getInstancia();
	}

	// Retorna a unica instancia do banco (cria uma se nao existir)
	public static synchronized Banco getInstancia() {
		if (instanciaUnica == null)
			instanciaUnica = new Banco();

		return instanciaUnica;
	}

	
	// Transferências ------------------
	// Dinheiro de um jogador para outro
	public void transferencia(int idJogadorOrigem, int idJogadorDestino, int valor) {
		alteraDinheiro(idJogadorDestino, valor);
		alteraDinheiro(idJogadorOrigem, -valor);
		//jogadores.get(idJogadorOrigem).setDinheiro(jogadores.get(idJogadorOrigem).getDinheiro() - valor);
		//jogadores.get(idJogadorDestino).setDinheiro(jogadores.get(idJogadorDestino).getDinheiro() + valor);
	}

	// Adiciona o valor no dinheiro do jogador (se for um valor negativo, o jogador perde dinheiro)
	public void alteraDinheiro(int idJogador, int valor) {
		instanciaJogo.jogadores.get(idJogador).setDinheiro(instanciaJogo.jogadores.get(idJogador).getDinheiro() + valor);
	}
}
