import modelo.*;
import modelo.cartas.*;
import controlador.*;

/* CLASSE USADA APENAS PARA TESTES. APAGAR NA VERSÃO FINAL. */
public class TesteAcoes {
	public static void main (String Args[]) {
		CartaPagarOuCadeia carta = new CartaPagarOuCadeia(0, "nome", "descricao", 50);
		Jogador jogador = new Jogador(0, "ana", null);

		System.out.printf("ESTADO DO JOGADOR ANTES DA CARTA:\n- Casa: %d\n- Dinheiro: %.2f\n- Cadeia: %b\n-Habeas Corpus: %b\n- Espera: %b\n- Turnos a esperar: %d\n", jogador.getCasa(), jogador.getDinheiro(), jogador.estaNaCadeia(), jogador.getHabeasCorpus(), jogador.estaEsperando(), jogador.getRodadasEsperar());
		AcaoCartas.acao(carta, jogador, 1);
		System.out.printf("ESTADO DO JOGADOR DEPOIS DA CARTA:\n- Casa: %d\n- Dinheiro: %.2f\n- Cadeia: %b\n-Habeas Corpus: %b\n- Espera: %b\n- Turnos a esperar: %d\n", jogador.getCasa(), jogador.getDinheiro(), jogador.estaNaCadeia(), jogador.getHabeasCorpus(), jogador.estaEsperando(), jogador.getRodadasEsperar());
	}
}