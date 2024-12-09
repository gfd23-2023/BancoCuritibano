// JOGADOR
// guarda todos os dados do estado atual do jogador
// maioria dos metodos sao getters e setters dos atributos

package modelo;
import java.io.Serializable;
import java.util.ArrayList;
import modelo.casas.*;

public class Jogador implements Serializable {
	// Características do jogador
	private int id;
	private String nome;
	
	// Informações sobre o estado do jogador
	private int dinheiro;
	private int casaAtual;
	private boolean falido;
	private boolean pontoDePartida;
	private ArrayList<Propriedade> propriedades;

	// Relação do jogador com Cadeia
	private boolean naCadeia;
	private int rodadasNaCadeia;
	private boolean habeasCorpus;

	// Relação do jogador com Espera
	private boolean esperando;
	private int rodadasEsperando;
	private int rodadasEsperar; // se o jogador está inativo, usada para saber quantas rodadas deve ficar. Se não está, é -1.

	// Construtores
	// Inicializador (para jogo novo)
	public Jogador(int id, String nome) {
	    this.id = id;
	    this.nome = nome;
	    this.dinheiro = 20000;
	    this.casaAtual = 0;
	    this.falido = false;
		this.pontoDePartida = false;
		this.propriedades = new ArrayList<>();
	    this.naCadeia = false;
	    this.rodadasNaCadeia = 0;
		this.habeasCorpus = false;
	    this.esperando = false;
	    this.rodadasEsperando = 0;
		this.rodadasEsperar = -1;
	}

	// Recebe todos os parametros (para jogo carregado)
	public Jogador(int id, String nome, int dinheiro, int casaAtual, boolean falido, boolean pontoDePartida, ArrayList<Propriedade> propriedades, boolean naCadeia, int rodadasNaCadeia, boolean habeasCorpus, boolean esperando, int rodadasEsperando, int rodadasEsperar) {
	    this.id = id;
	    this.nome = nome;
	    this.dinheiro = dinheiro;
	    this.casaAtual = casaAtual;
	    this.falido = falido;
		this.pontoDePartida = pontoDePartida;
		this.propriedades = propriedades;
	    this.naCadeia = naCadeia;
	    this.rodadasNaCadeia = rodadasNaCadeia;
		this.habeasCorpus = habeasCorpus;
	    this.esperando = esperando;
	    this.rodadasEsperando = rodadasEsperando;
		this.rodadasEsperar = rodadasEsperar;
	}
    
	// Getters e Setters 
	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getNome() {
	    return nome;
	}

	public void setNome(String nome) {
	    this.nome = nome;
	}

	public int getDinheiro() {
	    return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
	    this.dinheiro = dinheiro;
	}

	public int getCasa() {
	    return casaAtual;
	}

	// Adiciona a quantidade armazenada em casasAndar na casa atual do jogador. Usa quantCasas para "dar a volta"
	public void setCasa(int casaNova, int quantCasas) {
		if (casaNova >= quantCasas) {
			casaAtual = casaNova % quantCasas;
			setPontoDePartida(true);
		} else if (casaNova < 0) {
			casaAtual = quantCasas + casaNova;
		} else {
			casaAtual = casaNova;
			if (casaAtual == 0) {
				setPontoDePartida(true);
			}
		}
	}

	public boolean estaFalido() {
	    return falido;
	}

	public void setFalido() {
	    falido = true;
		// Quando é declarado como falido, deixa de ser proprietário de todas as suas propriedades
		for (Propriedade propAtual : propriedades) {
			propAtual.setIdProprietario(-1);
			propAtual.setAluguel(propAtual.getAluguelInicial());
		}
	}

	public boolean passouPontoDePartida() {
		return pontoDePartida;
	}

	public void setPontoDePartida(boolean pontoDePartida) {
		this.pontoDePartida = pontoDePartida;
	}

	public ArrayList<Propriedade> getListaPropriedades() {
		return propriedades;
	}

	public void adicionaPropriedade(Propriedade propriedade) {
		propriedades.add(propriedade);
	}
	
	public boolean estaNaCadeia() {
	    return naCadeia;
	}

	public void setNaCadeia(boolean naCadeia) {
	    this.naCadeia = naCadeia;
	}

	public int getRodadasNaCadeia() {
	    return rodadasNaCadeia;
	}

	// Se zerarOuAumentar for 0, zera o inteiro rodadasNaCadeia. Se for 1, incrementa 1 rodada.
	public void setRodadasNaCadeia(int zerarOuAumentar) {
	    if (zerarOuAumentar == 0) {
	        rodadasNaCadeia = 0;
	    } else if (zerarOuAumentar == 1) {
	        rodadasNaCadeia++;
	    } else {
	        System.out.println("Erro. Código zerarOuAumentar inválido.");
	    }
	}

	public boolean getHabeasCorpus() {
		return habeasCorpus;
	}

	public void setHabeasCorpus(boolean habeasCorpus) {
		this.habeasCorpus = habeasCorpus;
	}

	public boolean estaEsperando() {
	    return esperando;
	}	

	public void setEsperando(boolean esperando) {
	    this.esperando = esperando;
	}

	public int getRodadasEsperando() {
	    return rodadasEsperando;
	}

	// Se zerarOuAumentar for 0, zera o inteiro rodadasEsperando. Se for 1, incrementa 1 rodada.
	public void setRodadasEsperando(int zerarOuAumentar) {
	    if (zerarOuAumentar == 0) {
	        rodadasEsperando = 0;
	    } else if (zerarOuAumentar == 1) {
	        rodadasEsperando++;
	    } else {
	        System.out.println("Erro. Código zerarOuAumentar inválido.");
	    }
	}

	public int getRodadasEsperar() {
	    return rodadasEsperar;
	}

	public void setRodadasEsperar(int rodadasEsperar) {
	    this.rodadasEsperar = rodadasEsperar;
	}
}
