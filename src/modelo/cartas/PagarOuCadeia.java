package modelo.cartas;
import modelo.Jogador;
import controlador.Banco;
/* Obriga o jogador a pagar ou, se não quiser pagar ou não tiver o dinheiro, ir para a cadeia */
public class PagarOuCadeia extends Carta {
  private int valor;

  /* Construtor */
  PagarOuCadeia(int index, String nome, String descricao, int valor) {
    super(index, nome, descricao);
    this.valor = valor;
  }

  /* Getter (não faz sentido ter um setter, ja que nunca muda) */
  public int getValor() {
    return valor;
  }

  /* Função de ação (opcao = 0 para tentar pagar e opcao = 1 para ir para a cadeia direto) */
  public void acao(Jogador origem, int opcao) {
    Banco banco = Banco.getInstancia();

    // Se opcao é 0, tenta efetuar o pagamento
    if (opcao == 0) {
      // Verifica se tem o dinheiro disponível
      if (origem.getDinheiro() - valor < 0) {
        System.out.println("Jogador não tem dinheiro o suficiente para essa opção.");
        origem.setNaCadeia(true);
      } else {
        banco.alteraDinheiro(origem, -valor);
      }
    } else if (opcao == 1) {
      origem.setNaCadeia(true);
    } else {
      System.out.println("Erro: opção inválida na ação da carta PagarOuCadeia");
    }
  }
}
