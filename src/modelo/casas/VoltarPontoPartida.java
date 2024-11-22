package modelo.casas;
import modelo.*;

/* Faz o jogador voltar para o ponto de partida */
public class VoltarPontoPartida extends Casa {
    @Override
    public void acaoChegada(Jogador origem) {
        origem.setCasa(0, 28);
        System.out.println(origem.getNome() + " voltou para o ponto de partida!");
    }
}
