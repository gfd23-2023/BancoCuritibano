package controlador;

// Classe banco (Singleton) 
public class Banco {
	private static Banco instanciaUnica;
	private Jogo instanciaJogo;
	
	private Banco() {
		this.instanciaJogo = Jogo.getInstancia();
	}

	// Retorna a unica instancia do banco (cria uma se nao existir)
	public static synchronized Banco getInstancia() {
		if (instanciaUnica == null)
			instanciaUnica = new Banco();

		return instanciaUnica;
	}

	
	// Transferências 
	// Dinheiro de um jogador para outro
	public void transferencia(int idJogadorOrigem, int idJogadorDestino, int valor) {
		if (instanciaJogo.jogadores.get(idJogadorOrigem).getDinheiro() - valor >= 0) {
			instanciaJogo.jogadores.get(idJogadorOrigem).setDinheiro(instanciaJogo.jogadores.get(idJogadorOrigem).getDinheiro() - valor);
			instanciaJogo.jogadores.get(idJogadorDestino).setDinheiro(instanciaJogo.jogadores.get(idJogadorDestino).getDinheiro() + valor);
		} else {
			instanciaJogo.jogadores.get(idJogadorOrigem).setFalido();
		}
	}

	// Adiciona o valor no dinheiro do jogador (se for um valor negativo, o jogador perde dinheiro)
	public void alteraDinheiro(int idJogador, int valor) {
		instanciaJogo.jogadores.get(idJogador).setDinheiro(instanciaJogo.jogadores.get(idJogador).getDinheiro() + valor);

		if (instanciaJogo.jogadores.get(idJogador).getDinheiro() < 0)
			instanciaJogo.jogadores.get(idJogador).setFalido();
	}
}