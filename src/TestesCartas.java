import java.util.ArrayList;
import modelo.cartas.*;

/* CLASSE USADA APENAS PARA TESTES. APAGAR NA VERSÃO FINAL. */
public class TestesCartas {
  public static void main(String[] args) {
    /* TESTES COM A CLASSE ListaCartas */
    ArrayList<Carta> cartas = ListaCartas.geraLista("modelo/cartas/cartas.csv", ";");
  
	System.out.println("Testes feitos para a classe ListaCartas:");
	for (int i = 0; i < cartas.size(); i++) {
      // Imprime os atributos que qualquer carta tem: id, nome, descricao
      System.out.printf("Carta %d:\n", i);
      System.out.printf("- id: %d\n", cartas.get(i).getIndex());
      System.out.printf("- nome: %s\n", cartas.get(i).getNome());
      System.out.printf("- descricao: %s\n", cartas.get(i).getDescricao());

      // Imprime os atributos específicos de cada tipo de carta
      if (cartas.get(i) instanceof CartaAvancar) {
        CartaAvancar cartaAvancar = (CartaAvancar) cartas.get(i);
        System.out.printf("- casas para avançar: %d\n", cartaAvancar.getCasasAvancadas());
      } else if (cartas.get(i) instanceof CartaVoltar) {
        CartaVoltar cartaVoltar = (CartaVoltar) cartas.get(i);
        System.out.printf("- casas para voltar: %d\n", cartaVoltar.getCasasPerdidas());
      } else if (cartas.get(i) instanceof CartaGanharDinheiro) {
        CartaGanharDinheiro cartaGanhar = (CartaGanharDinheiro) cartas.get(i);
        System.out.printf("- dinheiro recebido: %d\n", cartaGanhar.getValor());
      } else if (cartas.get(i) instanceof CartaPerderDinheiro) {
        CartaPerderDinheiro cartaPerder = (CartaPerderDinheiro) cartas.get(i);
        System.out.printf("- dinheiro perdido: %d\n", cartaPerder.getValor());
      } else if (cartas.get(i) instanceof CartaPagarOuCadeia) {
        CartaPagarOuCadeia cartaCadeia = (CartaPagarOuCadeia) cartas.get(i);
        System.out.printf("- pagar: %d\n", cartaCadeia.getValor());
      } else if (cartas.get(i) instanceof CartaEspera) {
    	CartaEspera cartaEspera = (CartaEspera) cartas.get(i);
		System.out.printf("- rodadas a esperar: %d\n", cartaEspera.getTurnosEspera());
      } else if (cartas.get(i) instanceof CartaHabeasCorpus) {
		CartaHabeasCorpus cartaHabeasCorpus = (CartaHabeasCorpus) cartas.get(i);
		System.out.println("- habeas corpus");
	  }
    }
  }
}