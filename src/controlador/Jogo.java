package controlador;

import modelo.*;
import modelo.casas.*;
import modelo.cartas.*;
import visao.*;
import java.util.*;

// CLASSE JOGO
// responsável por instanciar todos os componentes do jogo BC
// possui atributos para controlar o estado, rodadas e jogador da vez
// ela cria a conexao entre visao e modelo
// só pode existir UM jogo no programa, logo eh uma classe Singleton

public class Jogo {

	// classe jogo eh singleton
	private static Jogo instanciaUnica;

	// retorna a unica instancia de jogo (cria uma se nao existir)
	public static synchronized Jogo getInstancia() {
		if (instanciaUnica == null) 
			instanciaUnica = new Jogo();
		return instanciaUnica;
	}
		
	private Estados estado; // guarda estado atual do jogo
	private int rodada;		// numero da rodada
	private int jogada;		// id do jogador que deve jogar
	
	public ArrayList<Jogador> jogadores;
	public ArrayList<Casa> casas;
	public LinkedList<Carta> cartas; // lista ligada (FIFO)
	public Dado dado1, dado2;
	public Banco banco;
	
	// construtor (private para só getInstancia utilizar)
	private Jogo() {
		this.estado = Estados.MENU_INICIAL;
		this.rodada = 1;
		this.jogada = 0;
		this.jogadores = new ArrayList<>();
		this.casas = new ArrayList<>();
		this.cartas = new LinkedList<>();
		this.dado1 = new Dado();
		this.dado2 = new Dado();
		this.banco = banco.getInstancia();
	}

	// getters e setters ---------------------------

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public int getRodada() {
		return rodada;
	}

	public int getJogada() {
		return jogada;
	}

	// ------------------------------------------------------------------------------
	// MÈTODOS GERAIS JOGO ----------------------------------------------------------
	// ------------------------------------------------------------------------------

	// recebe um nome de um jogador e adiciona ele na lista
	public void registroJogadores(String nome, int id) {

		// vamos ver quantos jogadores já existem
		int num = jogadores.size(); 

		if (id == num) {
			// jogador ainda não foi adicionado
			this.jogadores.add(new Jogador(id, nome));
		}
		else {
			// jogador já existe, vamos atualizar
			this.jogadores.get(id).setNome(nome);
		}

		System.out.printf("%d: %s\n", id, jogadores.get(id).getNome());
	}
		
	// verifica se foram registrados jogadores suficientes
	public boolean registroValido() {
		// numero total de jogadores
		int num = jogadores.size();
		if (num < 2) 
			return false;
		else
			return true;
	}

	public void iniciaJogo() {
		this.cartas = ListaCartas.geraLista("src/modelo/cartas/cartas.csv", ";");
		this.casas = ListaCasas.geraLista("src/modelo/casas/casas.csv", ",");
		this.banco.getInstanciaJogo();
	}


	public void jogaDados() {
		dado1.jogaDados();
		dado2.jogaDados();
	}

	public int valorDados() {
		int valor = dado1.getValor() + dado2.getValor();
		return valor;
	}

	// movimenta jogador da vez 1 casa
	public void movimentaJogador() {	
		int casaAtual = jogadores.get(jogada).getCasa();
		jogadores.get(jogada).setCasa(casaAtual+1, casas.size());
	}

	public void proximaJogada() {
		++jogada; // incrementa jogada
	
		int quant = jogadores.size(); // quantidade de jogadores

		// se todos os jogadores ja jogaram, vai para proxima rodada
		if (jogada >= quant) {
			++rodada;
			jogada = 0;
			// percorre todos os jogadores atualizando rodadas esperando
			for (int i = 0; i < quant; i++) {
				// se ainda precisa esperar
				if (!AcaoCartas.verificaEspera(jogadores.get(i))) {
					// atualizamos a espera porque passou uma rodada
					int esperando = jogadores.get(i).getRodadasEsperando();
					jogadores.get(i).setRodadasEsperando(esperando+1);
				}
				if (jogadores.get(i).estaNaCadeia()) {
					// verifica se ja completou 3 rodadas na cadeia
					if (jogadores.get(i).getRodadasNaCadeia() == 3)
						jogadores.get(i).setRodadasNaCadeia(0); // liberta
					else
						jogadores.get(i).setRodadasNaCadeia(1); // incrementa rodada
				}
			}
		}
		// verifica se jogador pode jogar
		if (jogadores.get(jogada).estaFalido() 
		|| jogadores.get(jogada).estaNaCadeia()
		|| jogadores.get(jogada).estaEsperando()) {

			this.proximaJogada();
		}
	}

	public String corCasa(int index) {
		String cor = "Rosa";
		if (casas.get(index) instanceof Propriedade) {
			Casa casa = casas.get(index);
			cor = ((Propriedade) casa).getCor();
		}
		return cor;
	}

	// recebe o indice da casa, se for propriedade, retorna ela
	public Propriedade casaPropriedade(int index) {
		if (casas.get(index) instanceof Propriedade) {
			Propriedade casa = (Propriedade) casas.get(index);
			return casa;
		}
		return null;
	}


	public Casa casaJogada() {
		int index = jogadores.get(jogada).getCasa();
		return casas.get(index);
	}



	public int casaCadeia() {
		// descobre qual eh a casa da cadeia
		for (int i = 0; i < casas.size(); i++) {
			if (casas.get(i) instanceof Cadeia) {
				return casas.get(i).getIndex();
			}
		}
		// retorna casa atual
		return casas.get(jogadores.get(jogada).getCasa()).getIndex();
	}

	// analisa a casa em que o jogador da jogada parou 
	// atualiza o estado to jogo
	public void analisaCasa() {
		// indice da casa do jogador atual da rodada
		int index = jogadores.get(jogada).getCasa();
		Casa casa = casas.get(index);

		if (casa instanceof CasaSorteOuReves) {
			this.estado = Estados.JOGAR_CARTA;
		}
		else if (casa instanceof CasaVaParaCadeia) {
			this.estado = Estados.JOGAR_CADEIA;
		}
		else if (casa instanceof CasaImpostoDeRenda) {
			this.estado = Estados.JOGAR_IMPOSTO;
		}
		else if (casa instanceof CasaVoltarPontoPartida) {
			this.estado = Estados.JOGAR_PARTIDA;
		}
		else if (casa instanceof Propriedade) {
			Propriedade prop = (Propriedade) casa;
			String cor = prop.getCor();
			if (AcaoCasas.verificaConstrucao(prop, jogadores.get(jogada).getId()))
				this.estado = Estados.JOGAR_CONSTRUIR;
			else if (prop.getIdProprietario() == -1)
				this.estado = Estados.JOGAR_PROPRIEDADE;
			else 
				this.estado = Estados.JOGAR_ALUGUEL;
		}
		else {
			this.estado = Estados.JOGAR_PROXIMO;
		}
	}

	public void acaoCasa() {
		// indice da casa do jogador atual da rodada
		int index = jogadores.get(jogada).getCasa();
		Casa casa = casas.get(index);
		Jogador jogador = jogadores.get(jogada);

		if (casa instanceof CasaVaParaCadeia) {
			AcaoCasas.acaoCasaVaParaCadeia(jogador, this.casaCadeia(), casas.size());
		}
		else if (casa instanceof CasaImpostoDeRenda) {
			AcaoCasas.chegadaImpostoDeRenda((CasaImpostoDeRenda) casa, jogador);
		}

		else if (casa instanceof CasaVoltarPontoPartida) {
			AcaoCasas.acaoCasaVoltarPontoPartida(jogador);
		}
		else if (casa instanceof Propriedade) {
			Propriedade prop = (Propriedade) casa;
			if (estado == Estados.JOGAR_PROPRIEDADE)
				AcaoCasas.compraPropriedade(prop, jogador);
			else if (estado == Estados.JOGAR_CONSTRUIR) 
				AcaoCasas.constroiPropriedade(prop, jogador);
			else
				AcaoCasas.pagaAluguelPropriedade(prop, jogador);
		}
	}


	// retira uma carta do baralho, executa ação e guarda no final
	// chama os metodos de AçãoCartas e atualiza o estado do jogo
	// retorna carta retirada visao exibir botoes Pagar ou Cadeia caso necessário
	// trata com o jogador atua da rodada
	public Carta retiraCarta() {
		Carta carta = cartas.removeFirst();

		if (carta instanceof CartaAvancar) {
			AcaoCartas.acaoCartaAvancar((CartaAvancar) carta, jogadores.get(jogada), casas.size());
			this.estado = Estados.JOGAR_PROXIMO;
		}
		else if (carta instanceof CartaVoltar) {
			AcaoCartas.acaoCartaVoltar((CartaVoltar) carta, jogadores.get(jogada), casas.size());
			this.estado = Estados.JOGAR_PROXIMO;
		}
		else if (carta instanceof CartaGanharDinheiro) {
			AcaoCartas.acaoCartaGanharDinheiro((CartaGanharDinheiro) carta, jogadores.get(jogada).getId());
			this.estado = Estados.JOGAR_PROXIMO;
		}
		else if (carta instanceof CartaPerderDinheiro) {
			AcaoCartas.acaoCartaPerderDinheiro((CartaPerderDinheiro) carta, jogadores.get(jogada).getId());
			this.estado = Estados.JOGAR_PROXIMO;
		}
		else if (carta instanceof CartaEspera) {
			AcaoCartas.acaoCartaEspera((CartaEspera) carta, jogadores.get(jogada));
			this.estado = Estados.JOGAR_PROXIMO;
		}
		else if (carta instanceof CartaHabeasCorpus) {
			AcaoCartas.acaoCartaHabeasCorpus((CartaHabeasCorpus) carta, jogadores.get(jogada));
			this.estado = Estados.JOGAR_PROXIMO;
		}
		else if (carta instanceof CartaPagarOuCadeia) {
			this.estado = Estados.JOGAR_CARTA_OPCAO;
		}

		cartas.addLast(carta);
		return carta;
	}

	public void cartaPagarOuCadeia(int opcao) {
		// carta ja foi retirada, está no final
		Carta carta = cartas.getLast();
		AcaoCartas.acaoCartaPagarOuCadeia((CartaPagarOuCadeia) carta, jogadores.get(jogada), opcao, 7, casas.size());
		estado = Estados.JOGAR_PROXIMO;
	}


}
