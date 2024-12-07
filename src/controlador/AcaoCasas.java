package controlador;
import modelo.casas.*;
import modelo.cartas.*;
import modelo.*;
import java.util.ArrayList;
import java.util.LinkedList;

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
				banco.alteraDinheiro(origem.getId(), -valorPorcentagem);
				return true;	
			} else { // Se essa opção não funcionou, retorna false
				return false;
			} 
		} else if (opcao == 1) {
			// Se o jogador preferiu pagar o valor fixo e tem dinheiro suficiente, faz o pagamento e retorna true
			if (origem.getDinheiro() - casa.getValor() >= 0) {
				banco.alteraDinheiro(origem.getId(), -casa.getValor());
				return true;
			} else { // Se essa opção não funcionou, retorna false
				return false; 
			}
		} else { // Se o código de opção é invalido, retorna false
			return false;
		}
	}

	// Ao passar ou chegar em ponto de partida, o jogador recebe seu salario
	public static void acaoPontoDePartida(CasaPontoDePartida casa, Jogador origem) {
		Banco banco = Banco.getInstancia();

		banco.alteraDinheiro(origem.getId(), casa.getSalario());
	}

	// Retorna a primeira carta da lista
	public static Carta acaoCasaCarta(CasaSorteOuReves casa, Jogador origem, LinkedList<Carta> cartas) {
		Carta cartaSorteada;

		// cartaSorteada := primeira carta
		cartaSorteada = cartas.getFirst();

		// Remove ela do começo e adiciona no final
		cartas.removeFirst();
		// Se a carta é habeas corpus, só é recolocada no monte depois de ser usada
		if (!(cartaSorteada instanceof CartaHabeasCorpus))
			cartas.addLast(cartaSorteada);

		return cartaSorteada;
	}

	// Ação da casa Vá Para a Cadeia
	public static void acaoCasaVaParaCadeia(CasaVaParaCadeia casa, Jogador origem, int posicaoCadeia, int quantCasas) {
		origem.setNaCadeia(true);
		origem.setCasa(posicaoCadeia, quantCasas);
	}

	// Ação da casa Voltar Para Ponto de Partida
	public static void acaoCasaVoltarPontoPartida(CasaVoltarPontoPartida casa, Jogador origem) {
		origem.setCasa(0, -1); // quantCasas = -1 porque nunca vai ser usado nesse caso
	}
	
	/* Tenta realizar a compra de uma propriedade por um jogador.
	 * Se obteve sucesso retorna true, caso contrário retorna false. 
	 */
	public static boolean compraPropriedade(Propriedade propriedade, Jogador origem) {
		Banco banco = Banco.getInstancia();
		
		// Se a propriedade já tem um proprietário, não pode ser comprada
		if (propriedade.getIdProprietario() != -1)
			return false;

		// Se tem como, realiza a compra
		if (origem.getDinheiro() - propriedade.getValor() >= 0) {
			banco.alteraDinheiro(origem.getId(), -propriedade.getValor());
			propriedade.setIdProprietario(origem.getId());
			origem.adicionaPropriedade(propriedade);
			return true;
		}
		// Se não tinha dinheiro suficiente, não pode ser comprada
		return false;
	}

	// Se possível, efetua o pagamento do aluguel da propriedade. Se não, declara falência.
	public static void pagaAluguelPropriedade(Propriedade propriedade, Jogador origem) {
		Banco banco = Banco.getInstancia();

		// Se tem dinheiro suficiente para pagar, faz a transferência. Se não, declara falência.
		if (origem.getDinheiro() - propriedade.getAluguel() >= 0) {
			banco.transferencia(origem.getId(), propriedade.getIdProprietario(), propriedade.getValor());
		} else {
			origem.setFalido();
		}
	}

	/* Verifica se o jogador idVerificar tem monopólio sobre as casas do grupo da cor passada como parâmetro
	 * Se tem retorna true, caso contrário retorna false.
	 */
	public static boolean verificaMonopolio(ArrayList<Propriedade> propriedades, String cor, int idVerificar) {
		for (Propriedade propAtual : propriedades) {
			if (propAtual.getCor().equals(cor)) {
				// Se propriedadeAtual é da cor solicitada mas o proprietario não for o solicitado, retorna false 
				if (propAtual.getIdProprietario() != idVerificar) { 
					return false;
				}
			} 
		}
		
		// Se chegou até aqui, idVerificar tem o monopólio
		return true;
	}

	/* Retorna true se o jogador idConstrutor pode construir na propriedadeConstrucao, e false caso contrário 
	 * REQUISITOS PARA CONSTRUÇÃO:
	 * 1. A propriedade tem que fazer parte de um monopólio (obviamente, idConstrutor deve ser o dono desse monopolio)
	 * 2. O número máximo de casas por propriedade é 5 (em que 5 casas são o mesmo que um hotel)
	 * 3. Para construir x unidades de casa, todas as outras propriedades do monopólio devem ter pelo menos x-1 casas
	 *   Por exemplo, para construir uma 4a casa, as outras propriedades dessa cor devem ter pelo menos 3 casas.
	 * 
	 * EXPLICAÇÃO DO RETORNO:
	 * - 1 se a construção pode ser feita
	 * - 0 se a construção não pode ser feita porque jogador não tem monopólio (requisito 1)
	 * - -1 se a construção não pode ser feita porque já tem a quantidade máxima de casas (requisito 2)
	 * - -2 se a construção não pode ser feita porque as outras propriedades do monopólio não tem a quantidade mínima de casas (requisito 3) 	 
	 */
	public static int verificaConstrucao(ArrayList<Propriedade> propriedades, Propriedade propriedadeConstrucao, int idConstrutor) {
		String cor = propriedadeConstrucao.getCor(); // Armazena a cor da propriedade a ser construída
		int quantCasasPropriedade = propriedadeConstrucao.getQuantConstrucoes();

		// Verifica o requisito 1: jogador ter monopólio
		if (!verificaMonopolio(propriedades, cor, idConstrutor))
			return 0;

		// Verifica o requisito 2: cada propriedade pode ter no máximo 5 casas
		if (quantCasasPropriedade >= 5)
			return -1;
		
		// Verifica o requisito 3: todas as propriedades do monopólio terem pelo menos x-1 casas
		for (Propriedade propAtual : propriedades) {
			if (propAtual.getCor().equals(cor)) {
				if (propAtual.getQuantConstrucoes() < quantCasasPropriedade)
					return -2;
			}
		}

		// Se chegou até aqui, pode construir
		return 1;
	}

	/* Se for possível, faz uma construção na propriedade propriedadeConstrucao
	 * EXPLICAÇÃO DO RETORNO:
	 * - -2, -1, 0 e 1: mesmo uso de verificaConstrucao
	 * - -3 se a construção não pode ser feita por falta de dinheiro do jogador
	 */
	public static int constroiPropriedade(ArrayList<Propriedade> propriedades, Propriedade propriedadeConstrucao, Jogador construtor) {
		int retornoVerificaConstrucao = verificaConstrucao(propriedades, propriedadeConstrucao, construtor.getId());
		Banco banco = Banco.getInstancia();

		// Se a construção não pode ser feita por um motivo detectável em verificaConstrução, apenas repassa o retorno
		if (retornoVerificaConstrucao != 1)
			return retornoVerificaConstrucao;

		// Se o jogador tem dinheiro, efetua o pagamento, faz a construção, aumenta o valor do aluguel e retorna 1
		if (construtor.getDinheiro() - propriedadeConstrucao.getValorConstrucao() >= 0) {
			banco.alteraDinheiro(construtor.getId(), -propriedadeConstrucao.getValorConstrucao());
			propriedadeConstrucao.aumentaQuantConstrucoes();
			propriedadeConstrucao.setAluguel((int) (propriedadeConstrucao.getAluguel() + propriedadeConstrucao.getAluguel() * propriedadeConstrucao.getmultiplicadorAluguel()));
			return 1;
		}

		// Se o jogador não tem dinheiro, retorna -3
		return -3;
	}

	/* Efetua uma tentativa de sair da cadeia pelo jogador origem.
	 * Retorna true para sucesso e false para erro.
	 * 
	 * O parâmetro opção indica de qual forma o jogador quer tentar sair, seguindo o uso:
	 * - 0: usar habeas corpus
	 * - 1: pagar 50 reais
	 */
	public static boolean tentativaSairCadeia(Cadeia cadeia, Jogador origem, int opcao, LinkedList<Carta> cartas) {
		Banco banco = Banco.getInstancia();

		if (opcao == 0) {
			if (origem.getHabeasCorpus()) {
				origem.setNaCadeia(false);
				origem.setHabeasCorpus(false);
				ListaCartas.adicionaHabeasCorpus(cartas);
				return true;
			} else {
				return false;
			}
		} else if (opcao == 1) {
			if (origem.getDinheiro() - 50 >= 0) {
				origem.setNaCadeia(false);
				banco.alteraDinheiro(origem.getId(), -50);		
			}
		}
		
		return true;
	}
}