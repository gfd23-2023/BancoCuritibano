package main;

import java.util.ArrayList;
import javax.swing.JFrame;

public class Main {
  
    public static void main(String[] args) {

        ArrayList<Casa> casas = new ArrayList<>();
        InicializaCasas(casas);

        ArrayList<Jogador> jogadores = new ArrayList<>();
        InicializaJogadores(jogadores);

        JFrame window = new JFrame();   
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha no X
        window.setResizable(false);
        window.setTitle("Monopoly");

        GamePanel gamePanel = new GamePanel(casas, jogadores);
        window.add(gamePanel);
        window.pack(); // Ajusta o JFrame ao tamanho do JPanel

        window.setLocationRelativeTo(null); // Centraliza a janela na tela
        window.setVisible(true);

        gamePanel.startGameThread();


    }
       
    public static void InicializaCasas(ArrayList<Casa> casas) {
        
        int tam = 96;
        // Adiciona as casas com seus respectivos nomes, posições e valores
        casas.add(new Casa("Início", 0, tam * 7, tam * 7, 0));
        casas.add(new Casa("Passeio Público", 1, tam * 6, tam * 7, 1));
        casas.add(new Casa("Sorte ou Azar?", 2, tam * 5, tam * 7, 0));
        casas.add(new Casa("Calçadão R.XV", 3, tam * 4, tam * 7, 1));
        casas.add(new Casa("Parque Barigui", 4, tam * 3, tam * 7, 1));
        casas.add(new Casa("Parada de Ônibus", 5, tam * 2, tam * 7, 0));
        casas.add(new Casa("Praça Tiradentes", 6, tam * 1, tam * 7, 1));
        casas.add(new Casa("Mercado Municipal", 7, tam * 0, tam * 7, 1));
        casas.add(new Casa("Rua 24 Horas", 8, tam * 0, tam * 6, 1));
        casas.add(new Casa("Cemitério Municipal", 9, tam * 0, tam * 5, 1));
        casas.add(new Casa("Sorte ou Azar?", 10, tam * 0, tam * 4, 0));
        casas.add(new Casa("Parque Tanguá", 11, tam * 0, tam * 3, 1));
        casas.add(new Casa("Parada de Ônibus", 12, tam * 0, tam * 2, 0));
        casas.add(new Casa("Museu Oscar Niemeyer", 13, tam * 0, tam * 1, 1));
        casas.add(new Casa("Jardim Botânico", 14, tam * 0, tam * 0, 1));
        casas.add(new Casa("Arena da Baixada", 15, tam * 1, tam * 0, 1));
        casas.add(new Casa("Sorte ou Azar?", 16, tam * 2, tam * 0, 0));
        casas.add(new Casa("Museu Paranaense", 17, tam * 3, tam * 0, 1));
        casas.add(new Casa("Memorial Árabe", 18, tam * 4, tam * 0, 1));
        casas.add(new Casa("Parada de Ônibus", 19, tam * 5, tam * 0, 0));
        casas.add(new Casa("Praça do Japão", 20, tam * 6, tam * 0, 1));
        casas.add(new Casa("Teatro Guaíra", 21, tam * 7, tam * 0, 1));
        casas.add(new Casa("Rua Mateus Leme", 22, tam * 7, tam * 1, 1));
        casas.add(new Casa("Sorte ou Azar?", 23, tam * 7, tam * 2, 0));
        casas.add(new Casa("Universidade Federal", 24, tam * 7, tam * 3, 1));
        casas.add(new Casa("Centro Histórico", 25, tam * 7, tam * 4, 1));
        casas.add(new Casa("Parada de Ônibus", 26, tam * 7, tam * 5, 0));
        casas.add(new Casa("Santa Felicidade", 27, tam * 7, tam * 6, 1));
    }

    public static void InicializaJogadores(ArrayList<Jogador> jogadores){
        jogadores.add(new Jogador("Vermelho", null));
        jogadores.add(new Jogador("Verde", null));
        jogadores.add(new Jogador("Azul", null));
        jogadores.add(new Jogador("Roxo", null));
        jogadores.add(new Jogador("Amarelo", null));
        jogadores.add(new Jogador("Cinza", null));
      
    }
}


