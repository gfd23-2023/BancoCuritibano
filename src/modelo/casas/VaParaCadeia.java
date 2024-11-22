package modelo.casas;
import modelo.*;

/* Coloca o jogador na cadeia */
public class VaParaCadeia extends Casa {
    private int posicaoPrisao;

    public int getPosicaoPrisao() {
        return posicaoPrisao;
    }

    public void setPosicaoPrisao(int posicaoPrisao) {
        this.posicaoPrisao = posicaoPrisao;
    }

    public VaParaCadeia(int posicaoPrisao) {
        this.posicaoPrisao = posicaoPrisao;
    }

    @Override
    public void acaoChegada(Jogador origem) {
        origem.setCasa(posicaoPrisao, 28);
        System.out.println(origem.getNome() + " vai para a prisão!");
    }

}
