package modelo.casas;
import java.io.*;
import modelo.Jogador;

/* Coloca o jogador na cadeia */
public class VaParaCadeia extends Casa {
    private int posicaoPrisao;

    public int getPosicaoPrisao() {
        return posicaoPrisao;
    }

    public void setPosicaoPrisao(int posicaoPrisao) {
        this.posicaoPrisao = posicaoPrisao;
    }

    VaParaCadeia(int posicaoPrisao) {
        this.posicaoPrisao = posicaoPrisao;
    }

    void acaoChegada(Jogador origem, int opcao) {
        origem.setCasaAtual(posicaoPrisao);
        System.out.println(origem.getNome() + " vai para a prisão!");
    }

}
