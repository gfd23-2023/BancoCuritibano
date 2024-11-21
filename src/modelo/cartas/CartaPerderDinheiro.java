package modelo.cartas.CartaPerderDinheiro;
import modelo.Jogador;

/* Tira dinheiro do jogador */
public class CartaPerderDinheiro extends Carta {
    private int valor;

    /* Construtor */
    CartaPerderDinheiro(int index, String nome, String descricao, int valor) {
        super(index, nome, descricao);
        this.valor = valor;
    }

    /* Getter (não faz sentido ter um setter, ja que nunca muda) */
    public int getValor() {
        return valor;
    }   

    /* Implementação da função de ação */
    public void acao(Jogador origem, int opcao) {
        Banco banco = Banco.getInstancia();

        banco.alteraDinheiro(origem, -valor);
    }
}
