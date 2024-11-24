package modelo.casas;
import controlador.Banco;
import modelo.*;


public class PontoDePartida extends Casa {

    public void acaoChegada(Jogador origem) {
        Banco banco = Banco.getInstancia();

        banco.diaDoPagamento(origem);
        System.out.println("Parabens! Jogador(a) " + origem.getNome() + " ganhou 200 reais!");
    }
}
