/* Main para testes com as classes de cartas. APAGAR NA VERSÃO FINAL. */

import java.util.ArrayList;

public class TestesCartas {
  public static void main(String[] args) {
    /* TESTES PARA TIPOS ESPECÍFICOS DE CARTAS (no final do arquivo tem os testes que ja foram feitos e os que faltam) */
    CartaEspera carta = new CartaEspera(0, null, null, 50);
    Jogador jogador = new Jogador("ana", null);
    Banco banco = Banco.getInstancia();

    System.out.println("Testes feitos para um tipo especifico de carta:");
    System.out.printf("Estado do jogador é espera = %b\n", jogador.estaEsperando());
    carta.acao(jogador, 1);
    System.out.printf("Estado do jogador é espera = %b\n", jogador.estaEsperando());
  
    /* TESTES COM A CLASSE ListaCartas */
    ArrayList<Carta> cartas = ListaCartas.geraLista("cartas.csv", ",");
  
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
      }
    }
  }
}


/* Cartas já testadas e organizadas:
 * - avançar
 * - voltar
 * - ganhar dinheiro
 * - perder dinheiro
 * - pagar ou cadeia
 * 
 * Falta verificar:
 * - Espera
 * 
 * Falta fazer:
 * - Habeas Corpus
 * - Lista de cartas
 * - Lista de cartas do jogador 
 * - Espera: monitorar rodadas esperando
 * - adicionar um exemplo de PagarOuCadeia em cartas.csv
 */