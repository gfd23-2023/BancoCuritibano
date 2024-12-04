import java.lang.*;
import java.util.*;
import javax.swing.JFrame;
import modelo.*;
import modelo.casas.*;
import visao.*;


public class Main {
  
    public static void main(String[] args) {

        ArrayList<Casa> casas = new ArrayList<>();
        ArrayList<Jogador> jogadores = new ArrayList<>();

        InicializaCasas(casas);
        InicializaJogadores(jogadores);
        InicializaJogadoresNaCasaInicio(jogadores);
        
        ExibePainelJogo exibePainelJogo = new ExibePainelJogo(casas, jogadores);

    }

    public static void InicializaCasas(ArrayList<Casa> casas) {
        
        int tam = 96;
        for (int i = 0; i < 28; i++)
            casas.add(new Casa());
            
        // Adiciona as casas com seus respectivos nomes, posições e valores
        casas.get(0).setCasa("Início", 0,                 tam * 7, tam * 7);
        casas.get(1).setCasa("Passeio Público", 1,        tam * 6, tam * 7);
        casas.get(2).setCasa("Sorte ou    Azar?", 2,      tam * 5, tam * 7);
        casas.get(3).setCasa("Calçadão R.XV", 3,          tam * 4, tam * 7);
        casas.get(4).setCasa("Gibiteca", 4,               tam * 3, tam * 7);
        casas.get(5).setCasa("UFPR Prédio Histórico", 5,  tam * 2, tam * 7);
        casas.get(6).setCasa("Paço da Liberdade", 6,      tam * 1, tam * 7);
        casas.get(7).setCasa("Cadeia", 7,                 tam * 0, tam * 7);
        casas.get(8).setCasa("Sorte ou    Azar?", 8,      tam * 0, tam * 6);
        casas.get(9).setCasa("Museu Oscar Niemeyer", 9,   tam * 0, tam * 5);
        casas.get(10).setCasa("Santa Felicidade", 10,      tam * 0, tam * 4);
        casas.get(11).setCasa("UFPR Politécnico", 11,      tam * 0, tam * 3);
        casas.get(12).setCasa("UFPR Jardim Botânico", 12,  tam * 0, tam * 2);
        casas.get(13).setCasa("Jardim Botânico", 13,       tam * 0, tam * 1);
        casas.get(14).setCasa("Linha de Turismo (Volte para o Início)", 14, tam * 0, tam * 0);
        casas.get(15).setCasa("Teatro Guaíra", 15,         tam * 1, tam * 0);
        casas.get(16).setCasa("Shopping Barigui", 16,      tam * 2, tam * 0);
        casas.get(17).setCasa("Parque Barigui", 17,        tam * 3, tam * 0);
        casas.get(18).setCasa("Mercado Municipal", 18,     tam * 4, tam * 0);
        casas.get(19).setCasa("Parque Tingui", 19,         tam * 5, tam * 0);
        casas.get(20).setCasa("Bosque Alemão", 20,         tam * 6, tam * 0);
        casas.get(21).setCasa("BLITZ (Pague R$100 ou vá preso)", 21, tam * 7, tam * 0);
        casas.get(22).setCasa("Praça do Japão", 22,        tam * 7, tam * 1);
        casas.get(23).setCasa("Sorte ou Azar?", 23,        tam * 7, tam * 2);
        casas.get(24).setCasa("Torre Panorâmica", 24,      tam * 7, tam * 3);
        casas.get(25).setCasa("Catedral Curitiba", 25,     tam * 7, tam * 4);
        casas.get(26).setCasa("Shopping Pátio Batel", 26,  tam * 7, tam * 5);
        casas.get(27).setCasa("Prefeitura de Curitiba", 27,tam * 7, tam * 6);
    }

    public static void InicializaJogadores(ArrayList<Jogador> jogadores){
        jogadores.add(new Jogador(0, "Vermelho", null));
        jogadores.add(new Jogador(1, "Verde", null));
        jogadores.add(new Jogador(2,"Azul", null));
        jogadores.add(new Jogador(3, "Roxo", null));
        jogadores.add(new Jogador(4, "Amarelo", null));
        jogadores.add(new Jogador(5, "Cinza", null));
      
    }

    public static void InicializaJogadoresNaCasaInicio( ArrayList<Jogador> jogadores){
        System.out.println("Inicializando Jogadores nas Casas");
        
        for (int i = 0; i < jogadores.size(); i++){
            jogadores.get(i).setCasa(0, 27);
            System.out.printf("Jogador %d na Casa %d\n", jogadores.get(i).getId(), jogadores.get(i).getCasa());
        }  
        
    }


}

class ExibePainelJogo{

    ExibePainelJogo (ArrayList<Casa> casas, ArrayList<Jogador> jogadores){
        PainelDoJogo painelJogo = InicializaPainelJogo(casas, jogadores);
        WhileDoJogo(painelJogo, jogadores);
    }

    private static void WhileDoJogo(PainelDoJogo painelJogo, ArrayList<Jogador> jogadores){
        Jogador jogRodada; // Declaração de variável para o jogador da rodada

        while (true) { // Inicia um loop infinito
            if (painelJogo.getbotaoProxRodada()) { // Verifica se o botão "Próxima Rodada" foi clicado
                painelJogo.setbotaoProxRodada(false); // Reseta o estado do botão para o próximo ciclo

                jogRodada = jogadores.get((painelJogo.getRodada()-1)%6); // Determina o jogador da rodada, baseado no número da rodada (mod 6, para garantir que os jogadores são ciclicos)
                painelJogo.setRodada(painelJogo.getRodada(), jogRodada); // Atualiza o número da rodada e o jogador correspondente na tela

                if (painelJogo.getDadosRolados()){ // Se os dados foram rolados, pega o valor dos dados e move o jogador
                    painelJogo.puxaValorDados(); //coloca os valores dos dados rolados no PainelDoJogo
                    
                    try { 
                        Thread.sleep(100); // Aguarda por 100 milissegundos para evitar uso excessivo da CPU no loop
                    } catch (InterruptedException ex) { 
                        ex.printStackTrace(); // Exibe o erro se ocorrer uma interrupção no sleep
                    }
                    MoveJogador(jogRodada, painelJogo.getValorDados()); // Move o jogador baseado no valor dos dados
                }

                painelJogo.atualizarTela(); // Atualiza a tela do painel do jogo para refletir as mudanças

                painelJogo.setFalseDadosRolados(); // Reseta o estado dos dados para "não rolados"
                //painelJogo.setValorDados(0); // Reseta o valor dos dados para 0

            }
            try { 
                Thread.sleep(100); // Pausa o loop principal para não sobrecarregar a CPU
            } 
            catch (InterruptedException ex) { 
                ex.printStackTrace(); // Exibe o erro caso a pausa seja interrompida
            }
        }
    }
    
    private  static PainelDoJogo InicializaPainelJogo(ArrayList<Casa> casas, ArrayList<Jogador> jogadores){
        JFrame window = new JFrame();   
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha no X
        window.setResizable(false);
        window.setTitle("Monopoly");
    
        PainelDoJogo painelJogo = new PainelDoJogo(casas, jogadores);
        window.add(painelJogo);
        window.pack(); // Ajusta o JFrame ao tamanho do JPanel
    
        window.setLocationRelativeTo(null); // Centraliza a janela na tela
        window.setVisible(true);

        return painelJogo;
    }

    private static void MoveJogador(Jogador jogador, int valorDados){
        System.out.printf("casa nova = %d\n", jogador.getCasa() + valorDados );
        jogador.setCasa( jogador.getCasa()+ valorDados, 27);
    }

}


