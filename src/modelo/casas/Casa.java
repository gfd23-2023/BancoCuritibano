package modelo.casas;

import java.io.Serializable;

// Classe que define a estrutura geral de casas
public class Casa implements Serializable{
    private String nome;
    private int index;

	// Construtor
	public Casa() {}

    public Casa(String nome, int index) {
        this.nome = nome; 
        this.index = index;
    }

	// Getters e setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public void setCasa(String nome, int index) {
        this.nome = nome; 
        this.index = index;
    }
}
