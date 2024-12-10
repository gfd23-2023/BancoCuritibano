package controlador;

import modelo.casas.Casa;
import modelo.casas.Propriedade;
import java.io.Serializable;

// Classe banco (Singleton) 
public class Banco implements Serializable{
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
		alteraDinheiro(idJogadorOrigem, -valor);

		if (instanciaJogo.jogadores.get(idJogadorDestino).estaFalido() == false)
			alteraDinheiro(idJogadorDestino, valor);
	}

	// Adiciona o valor no dinheiro do jogador (se for um valor negativo, o jogador perde dinheiro)
	public void alteraDinheiro(int idJogador, int valor) {
		getInstanciaJogo();
		
		if (instanciaJogo.jogadores.get(idJogador).getDinheiro() + valor >= 0) {
			instanciaJogo.jogadores.get(idJogador).setDinheiro(instanciaJogo.jogadores.get(idJogador).getDinheiro() + valor);
		} else {
			instanciaJogo.jogadores.get(idJogador).setFalido();
			devolvePropriedades(idJogador);
		} 
	}

	// Devolve todas as propriedades de idJogador (usada quando ele é declarado como falido)
	public void devolvePropriedades(int idJogador) {
		getInstanciaJogo();

		for (Casa casaAtual : instanciaJogo.casas) {
			if (casaAtual instanceof Propriedade) {
				Propriedade propAtual = (Propriedade) casaAtual;

				if (propAtual.getIdProprietario() == idJogador) {
					propAtual.setIdProprietario(-1);
					propAtual.setAluguel(propAtual.getAluguelInicial());
					propAtual.zeraQuantConstrucoes();
				}
			}
		}
	}
}
