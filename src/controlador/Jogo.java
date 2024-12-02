package controlador;

import modelo.*;
import modelo.casas.*;
import modelo.cartas.*;
import java.util.*;


public class Jogo {
	// classe jogo eh singleton
	private static Jogo instanciaUnica;

	// retorna a unica instancia de jogo (cria uma se nao existir)
	public static synchronized Jogo getInstancia() {
		if (instanciaUnica == null) 
			instanciaUnica = new Jogo();
		return instanciaUnica;
	}
		
	// (ainda nao sei se deixo tudo publico ou privado...)
	private Estados estado; // guarda estado atual do jogo
	public int rodada; // numero da rodada
	public int jogada; // id do jogador que deve jogar
	
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

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public Estados getEstado() {
		return estado;
	}

	

}
