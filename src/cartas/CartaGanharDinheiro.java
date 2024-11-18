public class CartaGanharDinheiro extends Carta {
  private int valor;

  /* Construtor */
  CartaGanharDinheiro(int index, String nome, String descricao, int valor) {
    super(index, nome, descricao);
    this.valor = valor;
  }

  /* GETTER E SETTER */
  public int getValor() {
    return valor;
  }

  public void setValor(int valor) {
    this.valor = valor;
  }
  
  /* Função de ação no recebimento da carta */
  void acao(Jogador origem) {
    Banco banco = Banco.getInstancia();
    
    banco.dinheiroParaJogador(origem, valor);
  }
}