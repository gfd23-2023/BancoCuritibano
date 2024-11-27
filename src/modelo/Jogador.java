// JOGADOR
// guarda todos os dados do estado atual do jogador
// maioria dos metodos sao getters e setters dos atributos

package modelo;
import javax.swing.ImageIcon;

public class Jogador {
	// Características do jogador
	private int id;
	private String nome;
	private ImageIcon foto;
	
	// Informações sobre o estado do jogador
	private double dinheiro;
	private int casaAtual;
	private boolean falido;

	// Relação do jogador com Cadeia
	private boolean naCadeia;
	private int rodadasNaCadeia;
	private boolean habeasCorpus;

	// Relação do jogador com Espera
	private boolean esperando;
	private int rodadasEsperando;
	private int rodadasEsperar; // se o jogador está inativo, usada para saber quantas rodadas deve ficar. Se não está, é -1.

	// Construtor 
	public Jogador(int id, String nome, ImageIcon foto) {
	    this.id = id;
	    this.nome = nome;
	    this.foto = foto;
	    this.dinheiro = (double) 2000;
	    this.casaAtual = 0;
	    this.falido = false;
	    this.naCadeia = false;
	    this.rodadasNaCadeia = 0;
		this.habeasCorpus = false;
	    this.esperando = false;
	    this.rodadasEsperando = 0;
		this.rodadasEsperar = -1;
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

	public ImageIcon getFoto() {
	    return foto;
	}

	public void setFoto(ImageIcon foto) {
	    this.foto = foto;
	}

	public double getDinheiro() {
	    return dinheiro;
	}

	public void setDinheiro(double dinheiro) {
	    this.dinheiro = dinheiro;
	}

	public int getCasa() {
	    return casaAtual;
	}

	// Adiciona a quantidade armazenada em casasAndar na casa atual do jogador. Usa quantCasas para "dar a volta"
	public void setCasa(int casasAndar, int quantCasas) {
		if (casaAtual + casasAndar >= quantCasas) {
			casaAtual = (casaAtual + casasAndar) % quantCasas;
		} else if (casaAtual + casasAndar < 0) {
			casaAtual = quantCasas + (casaAtual + casasAndar);
		} else {
			casaAtual += casasAndar;
		}
	}

	public boolean estaFalido() {
	    return falido;
	}

	public void setFalido() {
	    falido = true;
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