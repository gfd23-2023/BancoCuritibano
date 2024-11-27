package controlador;
import modelo.cartas.*;
import modelo.*;

public class AcaoCartas {

	/* Aplica a ação da carta no jogador origem. infoExtra é usada por cartas específicas, e de uma maneira diferente para cada.
	 * Os usos de infoExtra estão descritos a seguir:
	 * - na ação de CartaAvancar: representa a posição da última casa no tabuleiro
	 * - na ação de CartaVoltar: representa a posição da última casa no tabuleiro
	 * - na ação de CartaPagarOuCadeia: representa a opção escolhida pelo jogador (0 para pagar, 1 para ir para a cadeia)
	 */
	public static void acao(Carta carta, Jogador origem, int infoExtra) {

		// Ação de CartaAvancar
		if (carta instanceof CartaAvancar) {
			CartaAvancar cartaAux = (CartaAvancar) carta;
			origem.setCasa(cartaAux.getCasasAvancadas(), infoExtra);
		}
		
		// Ação de CartaVoltar
		if (carta instanceof CartaVoltar) {
			CartaVoltar cartaAux = (CartaVoltar) carta;
			origem.setCasa(-cartaAux.getCasasPerdidas(), infoExtra);
		} 
		
		// Ação de CartaGanharDinheiro
		if (carta instanceof CartaGanharDinheiro) {
			CartaGanharDinheiro cartaAux = (CartaGanharDinheiro) carta;
			Banco banco = Banco.getInstancia();
		
			banco.alteraDinheiro(origem, cartaAux.getValor());
		}
		
		// Ação de CartaPerderDinheiro
		if (carta instanceof CartaPerderDinheiro) {
			CartaPerderDinheiro cartaAux = (CartaPerderDinheiro) carta;
			Banco banco = Banco.getInstancia();
		
			banco.alteraDinheiro(origem, -cartaAux.getValor());
			if (origem.getDinheiro() < 0) {
				origem.setFalido();
			}
		}
		
		// Ação de CartaPagarOuCadeia
		if (carta instanceof CartaPagarOuCadeia) {
			CartaPagarOuCadeia cartaAux = (CartaPagarOuCadeia) carta;
			Banco banco = Banco.getInstancia();
			
			// Se opcao é 0, tenta efetuar o pagamento
			if (infoExtra == 0) {
				// Verifica se tem o dinheiro disponível
				if (origem.getDinheiro() - cartaAux.getValor() < 0) {
					System.out.println("Jogador não tem dinheiro o suficiente para essa opção.");
					origem.setNaCadeia(true);
				} else {
					banco.alteraDinheiro(origem, -cartaAux.getValor());
				}
			} else if (infoExtra == 1) {
			  origem.setNaCadeia(true);
			} else {
			  System.out.println("Erro: opção inválida na ação da carta PagarOuCadeia");
			}
		}

		// Ação de CartaEspera
		if (carta instanceof CartaEspera) {
			CartaEspera cartaAux = (CartaEspera) carta;
			origem.setEsperando(true);
			origem.setRodadasEsperando(0);
			origem.setRodadasEsperar(cartaAux.getTurnosEspera());
		}
		
		// Ação de CartaHabeasCorpus
		if (carta instanceof CartaHabeasCorpus) {
			origem.setHabeasCorpus(true);
		}
		
	}
}