package controlador;
import modelo.cartas.*;
import modelo.*;

// Classe que relaciona Carta com Jogador
public class AcaoCartas {

	// Ação da Carta Avançar
	public static void acaoCartaAvancar(CartaAvancar carta, Jogador origem, int quantCasas) {
		origem.setCasa(origem.getCasa() + carta.getCasasAvancadas(), quantCasas);
	}

	// Ação da Carta Voltar
	public static void acaoCartaVoltar(CartaVoltar carta, Jogador origem, int quantCasas) {
		origem.setCasa(origem.getCasa() - carta.getCasasPerdidas(), quantCasas);
	}

	// Ação da Carta Ganhar Dinheiro
	public static void acaoCartaGanharDinheiro(CartaGanharDinheiro carta, int idJogadorOrigem) {
		Banco banco = Banco.getInstancia();
		banco.alteraDinheiro(idJogadorOrigem, carta.getValor());
	}

	// Ação da Carta Perder Dinheiro
	public static void acaoCartaPerderDinheiro(CartaPerderDinheiro carta, int idJogadorOrigem) {
		Banco banco = Banco.getInstancia();
		banco.alteraDinheiro(idJogadorOrigem, -carta.getValor());
	}

	/* Ação da Carta Pagar Ou Cadeia
	 * Uso de opcao:
	 * - 0: se jogador optar por pagar
	 * - 1: se jogador optar por ir direto para a cadeia
	 * 
	 * Retorna true se a opcao escolhida deu certo e 0 em caso de erro
	 */
	public static boolean acaoCartaPagarOuCadeia(CartaPagarOuCadeia carta, Jogador origem, int opcao, int posicaoCadeia, int quantCasas) {
		Banco banco = Banco.getInstancia();
			
		// Se opcao é 0, tenta efetuar o pagamento
		if (opcao == 0) {
			// Verifica se tem o dinheiro disponível e, se tiver, faz o pagamento
			if (origem.getDinheiro() - carta.getValor() >= 0) {
				banco.alteraDinheiro(origem.getId(), -carta.getValor());
				return true;
			} else {
				return false;
			}
		} else if (opcao == 1) {
			origem.setNaCadeia(true);
			origem.setCasa(posicaoCadeia, quantCasas);
			return true;
		} else {
			System.out.println("Erro: opção inválida na ação da carta PagarOuCadeia");
			return false;
		}
	}

	// Ação da Carta Espera
	public static void acaoCartaEspera(CartaEspera carta, Jogador origem) {
		origem.setEsperando(true);
		origem.setRodadasEsperando(0);
		origem.setRodadasEsperar(carta.getTurnosEspera());
	}

	// Verifica se Jogador já esperou a quantidade de rodadas que tinha que esperar. Se sim, arruma variaveis e retorna true. Se não, retorna false.
	public static boolean verificaEspera(Jogador origem) {
		// Caso 1: jogador ja terminou de esperar
		if (origem.getRodadasEsperando() > origem.getRodadasEsperar()) {
			origem.setEsperando(false);
			origem.setRodadasEsperando(0);
			origem.setRodadasEsperar(-1);
			return true;
		}

		// Caso 2: jogador ainda tem que esperar
		return false;
	}

	// Ação da Carta Habeas Corpus
	public static void acaoCartaHabeasCorpus(CartaHabeasCorpus carta, Jogador origem) {
		origem.setHabeasCorpus(true);
	}
}