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

        //JFrame window = new JFrame();   
        GamePanel gamePanel = InicializaTabuleiro(casas, jogadores);

        WhileDoJogo(gamePanel, jogadores);

    }

    public static void WhileDoJogo(GamePanel gamePanel, ArrayList<Jogador> jogadores){

        Jogador jogRodada;
        int valorDados = -1;

            while (true) {
                if (gamePanel.getContinuar()) {              // Espera até que o botão seja clicado
                    gamePanel.SetContinuar(false); // Reseta a variável para o próximo ciclo
                    
                    System.out.println("-\n");
                    jogRodada = jogadores.get((gamePanel.getRodada()-1)%6);

                    gamePanel.setRodada(gamePanel.getRodada(), jogRodada);
                    //System.out.printf("Rodada=%d\nJogador=%d\nCasa Jogador=%d\n", gamePanel.getRodada(), jogRodada.getId(), jogRodada.getCasa());
            
                    if (gamePanel.getDadosRolados()){
                        valorDados = gamePanel.RetornaValorDados();
                        try {Thread.sleep(100);} // Evita ocupar 100% da CPU no loop}
                        catch (InterruptedException ex) {ex.printStackTrace();}
                    }
                    
                    gamePanel.setValorDados(valorDados);
                    MoveJogador(jogRodada, valorDados);
                    gamePanel.atualizarTela();

                    //System.out.printf("Casa Jogador=%d\n" ,jogRodada.getCasa());
                    gamePanel.setFalseDadosRolados();
                    valorDados = 0;
                
                }
                try { Thread.sleep(100); } 
                catch (InterruptedException ex) {ex.printStackTrace();}
            }
    }
    
    public static GamePanel InicializaTabuleiro(ArrayList<Casa> casas, ArrayList<Jogador> jogadores){
        JFrame window = new JFrame();   
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha no X
        window.setResizable(false);
        window.setTitle("Monopoly");
    
        GamePanel gamePanel = new GamePanel(casas, jogadores);
        window.add(gamePanel);
        window.pack(); // Ajusta o JFrame ao tamanho do JPanel
    
        window.setLocationRelativeTo(null); // Centraliza a janela na tela
        window.setVisible(true);

        return gamePanel;
    }

    public static void MoveJogador(Jogador jogador, int valorDados){
        System.out.printf("casa nova = %d\n", jogador.getCasa() + valorDados );
        jogador.setCasa( jogador.getCasa()+ valorDados, 27);
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


