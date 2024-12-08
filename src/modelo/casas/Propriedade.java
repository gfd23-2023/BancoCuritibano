package modelo.casas;

// Classe de Propriedades
public class Propriedade extends Casa {
    private int idProprietario; // -1 quando não foi comprada ainda
    private int valor;
	private int aluguelInicial;
    private int aluguel;
	private String cor;
	private int quantConstrucoes;
	private int valorConstrucao;
	private float multiplicadorAluguel;

	// Construtores
	// Inicializador (para novo jogo)
	public Propriedade(String nome, int index, int valor, int aluguel, String cor, int valorConstrucao, float multiplicadorAluguel) {
		super(nome, index);
		this.idProprietario = -1; 
		this.valor = valor;
		this.aluguelInicial = aluguel;
		this.aluguel = aluguel;
		this.cor = cor;
		this.quantConstrucoes = 0;
		this.valorConstrucao = valorConstrucao;
		this.multiplicadorAluguel = multiplicadorAluguel;
	}

	// Recebe todos os atributos (para carregar jogo)
	public Propriedade(String nome, int index, int idProprietario, int valor, int aluguelInicial, int aluguel, String cor, int quantConstrucoes, int valorConstrucao, float multiplicadorAluguel) {
		super(nome, index);
		this.idProprietario = idProprietario;
		this.valor = valor;
		this.aluguelInicial = aluguelInicial;
		this.aluguel = aluguel;
		this.cor = cor;
		this.quantConstrucoes = quantConstrucoes;
		this.valorConstrucao = valorConstrucao;
		this.multiplicadorAluguel = multiplicadorAluguel;
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

	public int getAluguelInicial() {
		return aluguelInicial;
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

	// Retorna qual será o valor do aluguel quando tiver quantCasas casas construídas
	public int consultaAluguel(int quantCasas) {
		float multiplicador = (float) Math.pow((1 + multiplicadorAluguel), quantCasas);
		float valorConsulta = aluguelInicial * multiplicador;  
	
		return (int)valorConsulta;
	}
}