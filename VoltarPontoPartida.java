public class VoltarPontoPartida extends CasaExtra {
  void acaoChegada(Jogador origem) {
    origem.setCasa(0);
    System.out.println(origem.getNome() + " voltou para o ponto de partida!");
  }
}