package controlador;

import modelo.*;
import modelo.casas.*;
import modelo.cartas.*;
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
	
	ArrayList<Jogador> jogadores;
	ArrayList<Casa> casas;
	LinkedList<Carta> cartas; // lista ligada (FIFO)
	Dado dado1, dado2;
	Banco banco;
	
	// construtor
	public Jogo() {
		this.estado = Estados.MENU_INICIAL;
		this.rodada = 0;
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

	// métodos de controle -----------------------------------------------------

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

    //public void iniciaJogo() {
        








/*
	public void proximaJogada() {
		++jogada; // incrementa jogada
	
		int quant = jogadores.size(); // quantidade de jogadores
		// se todos os jogadores ja jogaram, vai para proxima rodada
		if (jogada > quant) {
			++rodada;
			jogada = 0;
		}

		// verifica se jogador pode jogar
		// colocar se esta esperando
		if (jogadores.get(jogada).estaFalido() || jogadores.get(jogada).estaNaCadeia()) {
			this.proximaJogada();
		}
	}
*/
}
