import java.io.*;

import main.Banco;

public class PontoDePartida extends CasaExtra {
  void acaoChegada(Jogador origem) {
    Banco banco = Banco.getInstancia();
    
    banco.diaDoPagamento(origem);
    System.out.println("Parabens! Jogador(a) " + origem.getNome() + " ganhou 200 reais!");
  }
}