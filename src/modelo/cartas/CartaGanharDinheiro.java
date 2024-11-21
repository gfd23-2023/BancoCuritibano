package modelo.cartas.CartaGanharDinheiro;
import modelo.Jogador;

/* Dá dinheiro para o jogador */
public class CartaGanharDinheiro extends Carta {
    private int valor;

    /* Construtor */
    CartaGanharDinheiro(int index, String nome, String descricao, int valor) {
        super(index, nome, descricao);
        this.valor = valor;
    }

    /* Getter (não faz sentido ter um setter, ja que nunca muda) */
    public int getValor() {
        return valor;
    }

    /* Função de ação no recebimento da carta */
    public void acao(Jogador origem, int opcao) {
        Banco banco = Banco.getInstancia();

        banco.alteraDinheiro(origem, valor);
    }
}
