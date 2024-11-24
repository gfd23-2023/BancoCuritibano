package modelo.casas;
import controlador.*;
import modelo.*;

public class Propriedade extends Casa {
    private Jogador proprietario;
    private double valor;
    private double aluguel;

    /* GETTERS E SETTERS */
    public Jogador getProprietario() {
        return proprietario;
    }

    public void setProprietario(Jogador proprietario) {
        this.proprietario = proprietario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getAluguel() {
        return aluguel;
    }

    public void setAluguel(double aluguel) {
        this.aluguel = aluguel;
    }

    // Realiza a compra da propriedade pelo comprador
    public void comprar(Jogador comprador) {
        if (proprietario != null) {
            System.out.println("Propriedade já tem um proprietário.");
            return;
        }

        Banco banco = Banco.getInstancia();

        if (comprador.getDinheiro() - valor >= 0) {
            //comentei essa parte porque nao achei o metodo dinheiro de um jogador no Banco.java
            //banco.dinheiroDeJogador(comprador, valor);
            proprietario = comprador;
        } else {
            System.out.println("Comprador não tem dinheiro para realizar compra.");
        }
    }

    // origem paga o aluguel para proprietario
    public void pagarAluguel(Jogador origem) {
        if ((proprietario == null) || (origem == proprietario)) {
            System.out.println("Pagante é o dono.");
            return;
        }

        Banco banco = Banco.getInstancia();

        if (origem.getDinheiro() - aluguel >= 0) {
            banco.transferencia(origem, proprietario, aluguel);
        } else {
            System.out.println("Jogador não tem dinheiro para pagar o aluguel. Declarando falencia.");
            origem.setFalido();
        }

    }

    void acaoChegada(Jogador origem) {
        if (proprietario == null) {
            comprar(origem);
        } else {
            pagarAluguel(origem);
        }
    }
}
