package modelo.casas;

// Classe que define a estrutura geral de casas
public class Casa {
    private String nome;
    private int index;
	private int coordenadaX;
	private int coordenadaY;


	// Construtor
	public Casa() {}


    public Casa(String nome, int index) {
        this.nome = nome; 
        this.index = index;
	}

    public Casa(String nome, int index, int coordenadaX, int coordenadaY) {
        this.nome = nome; 
        this.index = index;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
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
    public int getCoordenadaX() {
        return this.coordenadaX;
    }
    public int getCoordenadaY() {
        return this.coordenadaY;
    }
    public void setCasa(String nome, int index) {
        this.nome = nome; 
        this.index = index;
	}
}
