package modelo.casas;

// Classe de Propriedades
public class Propriedade extends Casa {
    private int idProprietario; // -1 quando não foi comprada ainda
    private int valor;
    private int aluguel;

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


    // Getters e setters 
    public int getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(int idProprietario) {
        this.idProprietario = idProprietario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public double getAluguel() {
        return aluguel;
    }

    public void setAluguel(int aluguel) {
        this.aluguel = aluguel;
    }

	/*
	// Realiza a compra da propriedade pelo comprador
    public void comprar(Jogador comprador) {
        if (proprietario != null) {
            System.out.println("Propriedade já tem um proprietário.");
            return;
        }

        Banco banco = Banco.getInstancia();

        if (comprador.getDinheiro() - valor >= 0) {
            //comentei essa parte porque nao achei o metodo dinheiro de um jogador no Banco.java
            //banco.dinheiroDeJogador(comprador, valor);
            proprietario = comprador;
        } else {
            System.out.println("Comprador não tem dinheiro para realizar compra.");
        }
    }

    // origem paga o aluguel para proprietario
    public void pagarAluguel(Jogador origem) {
        if ((proprietario == null) || (origem == proprietario)) {
            System.out.println("Pagante é o dono.");
            return;
        }

        Banco banco = Banco.getInstancia();

        if (origem.getDinheiro() - aluguel >= 0) {
            banco.transferencia(origem, proprietario, aluguel);
        } else {
            System.out.println("Jogador não tem dinheiro para pagar o aluguel. Declarando falencia.");
            origem.setFalido();
        }

    }

    void acaoChegada(Jogador origem) {
        if (proprietario == null) {
            comprar(origem);
        } else {
            pagarAluguel(origem);
        }
    }
*/

}