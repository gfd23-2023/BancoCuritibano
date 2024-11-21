package modelo.casas.PontoDePartida;
import java.io.*;


public class PontoDePartida extends Casa {
    void acaoChegada(Jogador origem) {
        Banco banco = Banco.getInstancia();

        banco.diaDoPagamento(origem);
        System.out.println("Parabens! Jogador(a) " + origem.getNome() + " ganhou 200 reais!");
    }
}
