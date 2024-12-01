package controlador;
import modelo.*;

// Classe banco (Singleton) 
public class Banco {
	private static Banco instanciaUnica;
	
	// Retorna a unica instancia do banco (cria uma se nao existir)
	public static synchronized Banco getInstancia() {
		if (instanciaUnica == null)
		instanciaUnica = new Banco();
		return instanciaUnica;
	}

	
	// Transferências 
	// Dinheiro de um jogador para outro
	public void transferencia(Jogador origem, Jogador destino, int valor) {
		if (origem.getDinheiro() - valor >= 0) {
			origem.setDinheiro(origem.getDinheiro() - valor);
			destino.setDinheiro(destino.getDinheiro() + valor);
		} else {
			origem.setFalido();
		}
	}

	// Adiciona o valor no dinheiro do jogador (se for um valor negativo, o jogador perde dinheiro)
	public void alteraDinheiro(Jogador jogador, int valor) {
		jogador.setDinheiro(jogador.getDinheiro() + valor);

		if (jogador.getDinheiro() < 0)
			jogador.setFalido();
	}

	// Cada jogador recebe 200 reais no dia do pagamento
	public void diaDoPagamento(Jogador origem) {
		alteraDinheiro(origem, 200);
	}
}