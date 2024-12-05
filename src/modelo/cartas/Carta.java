package modelo.cartas;

// Classe de cartas 
public class Carta {
	private int index;
	private String nome;
    private String descricao;

    // Construtor 
    Carta(int index, String nome, String descricao) {
        this.index = index;
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e Setters 
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
    	this.descricao = descricao;
    }
}

