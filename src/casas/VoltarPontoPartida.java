/* Faz o jogador voltar para o ponto de partida */
public class VoltarPontoPartida extends CasaExtra {
  void acaoChegada(Jogador origem, int opcao) {
    origem.setCasa(0);
    System.out.println(origem.getNome() + " voltou para o ponto de partida!");
  }
}