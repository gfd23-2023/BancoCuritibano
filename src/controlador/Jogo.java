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
			instanciaUnica = new Jogo;
		return instanciaUnica;
	}
		
	// (ainda nao sei se deixo tudo publico ou privado...)
	public Estados estado; // guarda estado atual do jogo
	public int rodada; // numero da rodada
	public int jogada; // id do jogador que deve jogar
	
	ArrayList<Jogador> jogadores;
	ArrayList<Casa> casas;
	LinkedList<Carta> cartas; // lista ligada (FIFO)
	Dado dado1, dado2;
	
	// construtor
	public Jogo() {
		this.estado = MENU_INICIAL;
		this.rodada = 0;
		this.jogada = 0;
		this.jogadores = new ArrayList<>();
		this.casas = new ArrayList<>();
		this.cartas = new LinkedList<>();
		this.dado1 = new Dado();
		this.dado2 = new Dado();
	}

	

}

// enumeracao dos estados do jogo
// funcionam como macros/defines
// facil de adicionar/remover estados
enum Estados {
	MENU_INICIAL, 
	MENU_JOGADORES, // escolha dos jogadores
	JOGAR_DADOS, // momento para jogar os dados
	JOGAR_MOVIMENTO, // momento para jogador vai se movimentar
	JOGAR_CASA // momento para exibir em qual casa jogador caiu
	// ...
}


