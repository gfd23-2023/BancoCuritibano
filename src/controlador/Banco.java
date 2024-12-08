package controlador;

// Classe banco (Singleton) 
public class Banco {
	private static Banco instanciaUnica;
	private static Jogo instanciaJogo;
	
	// Retorna a instância do jogo para o banco
	public void getInstanciaJogo() {
		instanciaJogo = Jogo.getInstancia();
	}

	// Retorna a unica instancia do banco (cria uma se nao existir)
	public static synchronized Banco getInstancia() {
		if (instanciaUnica == null)
			instanciaUnica = new Banco();

		return instanciaUnica;
	}

	// Construtor (private para classes externas usarem apenas getInstancia)
	private Banco() {};

	// Transferências ------------------
	// Dinheiro de um jogador para outro
	public void transferencia(int idJogadorOrigem, int idJogadorDestino, int valor) {
		getInstanciaJogo();
		alteraDinheiro(idJogadorDestino, valor);
		alteraDinheiro(idJogadorOrigem, -valor);
	}

	// Adiciona o valor no dinheiro do jogador (se for um valor negativo, o jogador perde dinheiro)
	public void alteraDinheiro(int idJogador, int valor) {
		getInstanciaJogo();
		instanciaJogo.jogadores.get(idJogador).setDinheiro(instanciaJogo.jogadores.get(idJogador).getDinheiro() + valor);
	}
}
