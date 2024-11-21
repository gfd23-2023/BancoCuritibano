package modelo.casas.Casa;
import java.io.*;
import modelo.Jogador;


public abstract class Casa {
    private String nome;
    private int posicao;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getPosicao() {
        return posicao;
    }
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    abstract void acaoChegada(Jogador origem);
}
