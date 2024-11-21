/* Classe banco (Singleton) */
public class Banco {
  private static Banco instanciaUnica;
  
  /* Construtores */
  private Banco() {};

  // Retorna a unica instancia do banco (cria uma se nao existir)
  public static synchronized Banco getInstancia() {
    if (instanciaUnica == null)
      instanciaUnica = new Banco();
    return instanciaUnica;
  }

  
  /*Transferências*/ 
  // Dinheiro de um jogador para outro
  public void transferencia(Jogador origem, Jogador destino, double valor) {
    if (origem.getDinheiro() - valor >= 0) {
      origem.setDinheiro(origem.getDinheiro() - valor);
      destino.setDinheiro(destino.getDinheiro() + valor);
    } else {
      System.out.println("Jogador origem não tem dinheiro suficiente para transação. Declarando falencia.");
      origem.setFalido();
    }
  }

  // Adiciona o valor no dinheiro do jogador (se for um valor negativo, o jogador perde dinheiro)
  public void alteraDinheiro(Jogador jogador, double valor) {
    jogador.setDinheiro(jogador.getDinheiro() + valor);
  }

  // Cada jogador recebe 200 reais no dia do pagamento
  public void diaDoPagamento(Jogador origem) {
    alteraDinheiro(origem, 200);
  }
}