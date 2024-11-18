public class CartaPerderDinheiro extends Carta {
  private int valor;

  CartaPerderDinheiro(int index, String nome, String descricao, int valor) {
    super(index, nome, descricao);
    this.valor = valor;
  }

  public int getValor() {
    return valor;
  }   

  public void setValor(int valor) {
    this.valor = valor;
  }

  void acao(Jogador origem) {
    Banco banco = Banco.getInstancia();

    banco.dinheiroDeJogador(origem, valor);
  }
}