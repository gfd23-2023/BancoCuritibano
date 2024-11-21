package modelo.casas.VoltarPontoPartida;
import java.io.*;
import modelo.Jogador;

/* Faz o jogador voltar para o ponto de partida */
public class VoltarPontoPartida extends Casa {
    void acaoChegada(Jogador origem, int opcao) {
        origem.setCasa(0);
        System.out.println(origem.getNome() + " voltou para o ponto de partida!");
    }
}
