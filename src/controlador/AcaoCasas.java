package controlador;
import modelo.casas.*;
import modelo.cartas.*;
import modelo.*;
import java.util.ArrayList;

// Classe que relaciona Casa com Jogador
public class AcaoCasas {
	
	/* Chegada em imposto de renda: pagar alguma porcentagem do proprio dinheiro ou um valor fixo
	 * Uso de opcao:
	 *  -  0 se jogador optou por pagar porcentagem
	 *  -  1 se jogador optou por pagar valor fixo
	 * 
	 * Retorno é true se a opção escolhida funcionou e false caso contrário.
	 */
	public static boolean chegadaImpostoDeRenda(CasaImpostoDeRenda casa, Jogador origem, int opcao) {
		Banco banco = Banco.getInstancia();
		int valorPorcentagem = (int) (casa.getPorcentagem()/100*origem.getDinheiro());

		// Se o jogador não tem dinheiro para nenhuma das opções, declara falência e retorna erro
		if ((origem.getDinheiro() - valorPorcentagem < 0) && (origem.getDinheiro() - casa.getValor() < 0)) {
			origem.setFalido();
			return false;
		}

		if (opcao == 0) {
			// Se o jogador preferiu pagar a porcentagem e tem dinheiro suficiente, faz o pagamento e retorna true
			if (origem.getDinheiro() - valorPorcentagem >= 0) {
				banco.alteraDinheiro(origem, -valorPorcentagem);
				return true;	
			} else { // Se essa opção não funcionou, retorna false
				return false;
			} 
		} else if (opcao == 1) {
			// Se o jogador preferiu pagar o valor fixo e tem dinheiro suficiente, faz o pagamento e retorna true
			if (origem.getDinheiro() - casa.getValor() >= 0) {
				banco.alteraDinheiro(origem, -casa.getValor());
				return true;
			} else { // Se essa opção não funcionou, retorna false
				return false; 
			}
		} else { // Se o código de opção é invalido, retorna false
			return false;
		}
	}

	// Ao passar ou chegar em ponto de partida, o jogador recebe seu salario
	public void acaoPontoDePartida(CasaPontoDePartida casa, Jogador origem) {
		Banco banco = Banco.getInstancia();

		banco.alteraDinheiro(origem, casa.getSalario());
	}

	// Retorna a primeira carta da lista
	public Carta acaoCasaCarta(CasaSorteOuReves casa, Jogador origem, ArrayList<Carta> cartas) {
		Carta cartaSorteada;

		cartaSorteada = cartas.get(0);

		// Remove ela do começo e adiciona no final
		cartas.remove(cartaSorteada);
		cartas.add(cartaSorteada);

		return cartaSorteada;
	}

	// Ação da casa Vá Para a Cadeia
	public void acaoCasaVaParaCadeia(CasaVaParaCadeia casa, Jogador origem) {
		origem.setNaCadeia(true);
	}

	// Ação da casa Voltar Para Ponto de Partida
	public void acaoCasaVoltarPontoPartida(CasaVoltarPontoPartida casa, Jogador origem) {
		origem.setCasa(0, -1); // quantCasas = -1 porque nunca vai ser usado nesse caso
	}	

}