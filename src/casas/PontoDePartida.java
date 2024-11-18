import java.io.*;

public class PontoDePartida extends CasaExtra {
  void acaoChegada(Jogador origem) {
    origem.setDinheiro(origem.getDinheiro() + 200);
    System.out.println("Parabens! Jogador(a) " + origem.getNome() + " ganhou 200 reais!");
  }
}