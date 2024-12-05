package modelo.casas;

// Classe da casa que faz o jogador voltar para o ponto de partida
public class CasaVoltarPontoPartida extends Casa {

	public CasaVoltarPontoPartida(String nome, int index, int coordenadaX, int coordenadaY) {
		super(nome, index, coordenadaX, coordenadaY);
	}

    /*public void acaoChegada(Jogador origem) {
        origem.setCasa(0, 28);
        System.out.println(origem.getNome() + " voltou para o ponto de partida!");
    }*/
}
