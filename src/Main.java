import java.util.*;
import javax.swing.JFrame;
import modelo.*;
import modelo.casas.*;
import visao.*;

public class Main {
  
    public static void main(String[] args) {

        ArrayList<Casa> casas = new ArrayList<>();
        InicializaCasas(casas);

        ArrayList<Jogador> jogadores = new ArrayList<>();
        InicializaJogadores(jogadores);

        InicializaJogadoresNaCasaInicio(jogadores);

        //Banco banco = new Banco();
        Dado dado = new Dado();

        JFrame window = new JFrame();   
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha no X
        window.setResizable(false);
        window.setTitle("Monopoly");

        GamePanel gamePanel = new GamePanel(casas, jogadores);
        window.add(gamePanel);
        window.pack(); // Ajusta o JFrame ao tamanho do JPanel

        window.setLocationRelativeTo(null); // Centraliza a janela na tela
        window.setVisible(true);
        
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Escreva 1 para ver o jogador verde indo para casa \"Passeio Publico\":");
        int numteclado = scanner.nextInt();
        gamePanel.setRodada(1, jogadores.get(1), 0);
        if (numteclado == 1){
            MoveJogador(casas, jogadores.get(1),1);
            gamePanel.repaint();
        }

        System.out.println("Escreva 2 para ver o joagador amarelo indo para casa \"Parque Tangua\":");
        numteclado = scanner.nextInt();
        gamePanel.setRodada(2, jogadores.get(4), 0);
        if (numteclado == 2){
            MoveJogador(casas, jogadores.get(4),11);
            gamePanel.repaint();
        }

       
    }

    public static void MoveJogador(ArrayList <Casa> casas, Jogador jogador, int NovaCasa){
        casas.get(jogador.getCasa()).removerJogador(jogador);
        jogador.setCasa(NovaCasa);
        casas.get(NovaCasa).adicionarJogador(jogador);
    }
        }*/

    }

    /*public static void MoveJogador(ArrayList <Casa> casas, Jogador jogador, int NovaCasa){
        casas.get(jogador.getCasa()).removerJogador(jogador);
        jogador.setCasa(NovaCasa);
        casas.get(NovaCasa).adicionarJogador(jogador);
    }*/

    public static void InicializaCasas(ArrayList<Casa> casas) {
        
        int tam = 96;

        // Adiciona as casas com seus respectivos nomes, posições e valores
        casas.add(new Casa("Início", 0, tam * 7, tam * 7));
        casas.add(new Casa("Passeio Público", 1, tam * 6, tam * 7));
        casas.add(new Casa("Sorte ou Azar?", 2, tam * 5, tam * 7));
        casas.add(new Casa("Calçadão R.XV", 3, tam * 4, tam * 7));
        casas.add(new Casa("Gibiteca", 4, tam * 3, tam * 7));
        casas.add(new Casa("UFPR Prédio Histórico", 5, tam * 2, tam * 7));
        casas.add(new Casa("Paço da Liberdade", 6, tam * 1, tam * 7));
        casas.add(new Casa("Cadeia", 7, tam * 0, tam * 7));
        casas.add(new Casa("Sorte ou Azar?", 8, tam * 0, tam * 6));
        casas.add(new Casa("Museu Oscar Niemeyer", 9, tam * 0, tam * 5));
        casas.add(new Casa("Santa Felicidade", 10, tam * 0, tam * 4));
        casas.add(new Casa("UFPR Politécnico", 11,      tam * 0, tam * 3));
        casas.add(new Casa("UFPR Jardim Botânico", 12,  tam * 0, tam * 2));
        casas.add(new Casa("Jardim Botânico", 13,       tam * 0, tam * 1));
        casas.add(new Casa("Linha de Turismo (Volte para o Início)", 14, tam * 0, tam * 0));
        casas.add(new Casa("Teatro Guaíra", 15,         tam * 1, tam * 0));
        casas.add(new Casa("Shopping Barigui", 16,      tam * 2, tam * 0));
        casas.add(new Casa("Parque Barigui", 17,        tam * 3, tam * 0));
        casas.add(new Casa("Mercado Municipal", 18,     tam * 4, tam * 0));
        casas.add(new Casa("Parque Tingui", 19,         tam * 5, tam * 0));
        casas.add(new Casa("Bosque Alemão", 20,         tam * 6, tam * 0));
        casas.add(new Casa("BLITZ (Pague R$100 ou vá preso)", 21, tam * 7, tam * 0));
        casas.add(new Casa("Praça do Japão", 22,        tam * 7, tam * 1));
        casas.add(new Casa("Sorte ou Azar?", 23,        tam * 7, tam * 2));
        casas.add(new Casa("Torre Panorâmica", 24,      tam * 7, tam * 3));
        casas.add(new Casa("Catedral Curitiba", 25,     tam * 7, tam * 4));
        casas.add(new Casa("Shopping Pátio Batel", 26,  tam * 7, tam * 5));
        casas.add(new Casa("Prefeitura de Curitiba", 27,tam * 7, tam * 6));
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
            jogadores.get(i).setCasa(0, 28);
            System.out.printf("Jogador %d na Casa %d\n", jogadores.get(i).getId(), jogadores.get(i).getCasa());
        }  
        
    }
}


