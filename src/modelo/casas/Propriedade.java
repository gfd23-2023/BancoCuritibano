package modelo.casas;

// Classe de Propriedades
public class Propriedade extends Casa {
    private int idProprietario; // -1 quando não foi comprada ainda
    private int valor;
    private int aluguel;
	private String cor;
	private int quantConstrucoes;
	private int valorConstrucao;
	private float multiplicadorAluguel;

	// Construtores
	public Propriedade(String nome, int index, int coordenadaX, int coordenadaY, int valor, int aluguel) {
		super(nome, index, coordenadaX, coordenadaY);
		this.idProprietario = -1; 
		this.valor = valor;
		this.aluguel = aluguel;
	}

	public Propriedade(String nome, int index, int coordenadaX, int coordenadaY, int idProprietario, int valor, int aluguel) {
		super(nome, index, coordenadaX, coordenadaY);
		this.idProprietario = idProprietario;
		this.valor = valor;
		this.aluguel = aluguel;
	}


    // Getters e setters (alguns atributos não possuem setters porque nunca mudam )
    public int getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(int idProprietario) {
        this.idProprietario = idProprietario;
    }

    public int getValor() {
        return valor;
    }

    public int getAluguel() {
        return aluguel;
    }

    public void setAluguel(int aluguel) {
        this.aluguel = aluguel;
    }

	public String getCor() {
		return cor;
	}

	public int getQuantConstrucoes() {
		return quantConstrucoes;
	}

	public void aumentaQuantConstrucoes() {
		++quantConstrucoes;
	}
	
	public int getValorConstrucao() {
		return valorConstrucao;
	}

	public float getmultiplicadorAluguel() {
		return multiplicadorAluguel;
	}
}