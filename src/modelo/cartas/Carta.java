package modelo.cartas;

// Classe de cartas 
public class Carta {
	private String nome;
    private String descricao;

    // Construtor 
    Carta(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e Setters 
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

