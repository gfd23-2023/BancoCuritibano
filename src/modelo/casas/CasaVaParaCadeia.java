package modelo.casas;

// Classe da casa que faz o jogador pagar uma quantia ou ir para a cadeia
public class CasaVaParaCadeia extends Casa { 
	private int posicaoPrisao;

	// Construtor
	public CasaVaParaCadeia(String nome, int index, int coordenadaX, int coordenadaY, int posicaoPrisao) {
        super(nome, index);
		this.posicaoPrisao = posicaoPrisao;
    }

	// Getters (não faz sentido existirem setters, já que nunca muda)
    public int getPosicaoPrisao() {
        return posicaoPrisao;
    }

    /*public void acaoChegada(Jogador origem) {
        origem.setCasa(posicaoPrisao, 28);
        System.out.println(origem.getNome() + " vai para a prisão!");
    }*/

}
