public class VaParaCadeia extends CasaExtra {
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
  
  void acaoChegada(Jogador origem) {
    origem.setCasaAtual(posicaoPrisao);
    System.out.println(origem.getNome() + " vai para a prisão!");
  }

}