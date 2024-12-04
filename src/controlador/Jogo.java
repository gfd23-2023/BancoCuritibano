package controlador;

import modelo.*;
import modelo.casas.*;
import modelo.cartas.*;
import java.util.*;

//imports para o registro
//----------------------------------------
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//----------------------------------------

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
	int participantes = 0;	//quantidade de jogadores
	int id = 0;				//identificação do jogador
	
	ArrayList<Jogador> jogadores = new ArrayList<>();
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

	public void registroJogador(JTextField campo)
	{
		campo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//captura o nome escrito no JTextField em uma variável
				String nome = campo.getText();

				//se o nome não for vazio...
				if(!nome.isEmpty())
				{
					//adiciona-o à lista, incrementa o número de participantes e o id
					jogadores.add(new Jogador(id, nome));
					participantes++;
					//System.out.printf("%s\n", jogadores.get(id).getNome());	(funcionou)

					//depois que tirar o printf posso subir esse id++
					id++;
				}
			}
		});
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
