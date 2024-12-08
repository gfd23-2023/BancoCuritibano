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
	
	public ArrayList<Jogador> jogadores;
	public ArrayList<Casa> casas;
	public LinkedList<Carta> cartas; // lista ligada (FIFO)
	public Dado dado1, dado2;
	public Banco banco;
	
	// construtor
	public Jogo() {
		this.estado = Estados.MENU_INICIAL;
		this.rodada = 1;
		this.jogada = 0;
		this.jogadores = new ArrayList<>();
		this.casas = new ArrayList<>();
		this.cartas = ListaCartas.geraLista("src/modelo/cartas/cartas.csv", ";");
		this.dado1 = new Dado();
		this.dado2 = new Dado();
		this.banco = Banco.getInstancia();
		this.iniciaCasas();
	}

	// função provisória
	public void iniciaCasas() {
		for (int i = 0; i < 28; i++)
            casas.add(new Casa());

        // Adiciona as casas com seus respectivos nomes, posições e valores
        casas.get(0).setCasa("Início", 0);
        casas.get(1).setCasa("Passeio Público", 1);
        casas.get(2).setCasa("Sorte ou Azar?", 2);
        casas.get(3).setCasa("Calçadão R.XV", 3);
        casas.get(4).setCasa("Gibiteca", 4);
        casas.get(5).setCasa("UFPR Prédio Histórico", 5);
        casas.get(6).setCasa("Paço da Liberdade", 6);
        casas.get(7).setCasa("Cadeia", 7);
        casas.get(8).setCasa("Sorte ou Azar?", 8);
        casas.get(9).setCasa("Museu Oscar Niemeyer", 9);
        casas.get(10).setCasa("Santa Felicidade", 10);
        casas.get(11).setCasa("UFPR Politécnico", 11);
        casas.get(12).setCasa("UFPR Jardim Botânico", 12);
        casas.get(13).setCasa("Jardim Botânico", 13);
        casas.get(14).setCasa("Linha de Turismo", 14);
        casas.get(15).setCasa("Teatro Guaíra", 15);
        casas.get(16).setCasa("Shopping Barigui", 16);
        casas.get(17).setCasa("Parque Barigui", 17);
        casas.get(18).setCasa("Mercado Municipal", 18);
        casas.get(19).setCasa("Parque Tingui", 19);
        casas.get(20).setCasa("Bosque Alemão", 20);
        casas.get(21).setCasa("BLITZ", 21);
        casas.get(22).setCasa("Praça do Japão", 22);
        casas.get(23).setCasa("Sorte ou Azar?", 23);
        casas.get(24).setCasa("Torre Panorâmica", 24);
        casas.get(25).setCasa("Catedral Curitiba", 25);
        casas.get(26).setCasa("Shopping Pátio Batel", 26);
        casas.get(27).setCasa("Prefeitura de Curitiba", 27);
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
