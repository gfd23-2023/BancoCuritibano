package modelo.casas;

import java.io.*;
import java.util.*;

// Classe que gera a lista de casas usadas no jogo a partir de um arquivo csv 
public class ListaCasas {

	/* Retorna a lista de casas do jogo passadas no arquivo caminhoCSV 
	 * DEFINIÇÃO DOS TIPOS:
	 * - 0: Cadeia
	 * - 1: Imposto de renda (pagar porcentagem do seu dinheiro ou pagar valor fixo)
	 * - 2: Ponto de partida (inicia o jogo e, cada vez que um jogador passa por ela, recebe dinheiro)
	 * - 3: Casa Sorte ou Reves (retira uma carta aleatoria do monte)
	 * - 4: Casa Va Para Cadeia (manda o jogador que caiu nela para a prisão)
	 * - 5: Casa Voltar Para Ponto de Partida (jogador que caiu nela volta para a casa 0)
	 * - 6: Propriedade
	*/
	public static ArrayList<Casa> geraLista(String caminhoCSV, String separadorCSV) {
		// Armazena as casas - retorno do metodo
		ArrayList<Casa> casas = new ArrayList<>();

		// Usadas para leitura do CSV
        BufferedReader conteudoCSV = null;
        String linhaCSV = "";

		// Bloco principal: leitura e atribuição de cartas
        try {
            // Processa o arquivo
            conteudoCSV = new BufferedReader(new FileReader(caminhoCSV));

            // Consome a primeira linha (que contem os nomes das colunas)
            linhaCSV = conteudoCSV.readLine();

            // Consome e atribui os dados da tabela csv 
            while ((linhaCSV = conteudoCSV.readLine()) != null) {
                Casa casaAtual;

                String[] casa = linhaCSV.split(separadorCSV);

                if (casa[0].equals("0")) { // Casa cadeia
                    casaAtual = new Cadeia(casa[1], Integer.parseInt(casa[2]));
                } else if (casa[0].equals("1")) { // Casa imposto de renda
                    casaAtual = new CasaImpostoDeRenda(casa[1], Integer.parseInt(casa[2]), Integer.parseInt(casa[3]));
                } else if (casa[0].equals("2")) { // Casa ponto de partida
                    casaAtual = new CasaPontoDePartida(casa[1], Integer.parseInt(casa[2]), Integer.parseInt(casa[3]));
                } else if (casa[0].equals("3")) { // Casa sorte ou reves
                    casaAtual = new CasaSorteOuReves(casa[1], Integer.parseInt(casa[2]));
                } else if (casa[0].equals("4")) { // Casa va para a cadeia
                    casaAtual = new CasaVaParaCadeia(casa[1], Integer.parseInt(casa[2]));
                } else if (casa[0].equals("5")) { // Casa voltar ponto de partida
					casaAtual = new CasaVoltarPontoPartida(casa[1], Integer.parseInt(casa[2]));
                } else if (casa[0].equals("6")) { // Propriedade
					casaAtual = new Propriedade(casa[1], Integer.parseInt(casa[2]), Integer.parseInt(casa[4]), Integer.parseInt(casa[6]), casa[7], Integer.parseInt(casa[9]), Float.parseFloat(casa[10]));
				} else {
                	System.out.printf("Tipo nao definido: %s\n", casa[0]);
                	return null;
                }

                // adiciona casa
                casas.add(casaAtual);
            } 
        } // Tratamento de exceções
        catch (FileNotFoundException e) {
            System.out.println("Arquivo CSV nao encontrado.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro: ArrayIndexOutOfBounds");
        } catch (IOException e) {
            System.out.println("Erro IO");
        } finally {
            if (conteudoCSV != null) {
                try {
                    conteudoCSV.close();
                } catch (IOException e) {
                    System.out.println("Erro IO");
                }
            }
        }

		return casas;
	}
}
