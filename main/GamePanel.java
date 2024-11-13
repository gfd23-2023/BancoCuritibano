package main;

import java.awt.Color;
import java.awt.Dimension;     
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;


public class GamePanel extends JPanel {
    // Screen settings
    final int originalTileSize = 16;    
    final int scale = 6;    
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 8; 
    final int maxScreenRow = 8;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;   
    private ArrayList<Casa> casas = new ArrayList<>();
    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private boolean printedOnce = false;
    //Thread gameThread;

    public GamePanel(ArrayList<Casa> casas, ArrayList<Jogador> jogadores) {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true); 
        this.casas = casas;
        this.jogadores = jogadores;
    }
  
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        
        for (int i = 0; i < casas.size(); i++) {
            DesenhaBloco(casas.get(i).getCoordenadaX(),
                         casas.get(i).getCoordenadaY(),
                         tileSize, g2, casas.get(i).getNome());
        }
        DesenhaJogadores(casas, jogadores, g2); 
    }

    //public void repaint ()

    private void DesenhaJogadores(ArrayList<Casa> casas, ArrayList<Jogador> jogadores, Graphics2D g2){
        for (int i = 0; i < 6; i++){
            DesenhaJogador(casas.get(jogadores.get(i).getCasa()), jogadores.get(i), g2);
        }
    }

    private int PosicaoJogadorNaCasa(Casa casa, Jogador jogador){
        
        for (int k = 0; k < casa.getJogadores().size(); k++){
            if (casa.getJogadores().get(k).getNome().equals(jogador.getNome()))
                return k;
        }
        return -1;
    }

    private void SetColorG2 (Jogador jogador, Graphics2D g2){
        if (jogador.getNome().equals("Vermelho"))
            g2.setColor(Color.red);
        else if (jogador.getNome().equals("Verde"))
            g2.setColor(Color.green);
        else if (jogador.getNome().equals("Cinza"))
            g2.setColor(Color.gray);    
        else if (jogador.getNome().equals("Amarelo"))
            g2.setColor(Color.yellow);    
        else if (jogador.getNome().equals("Azul"))
            g2.setColor(Color.blue);    
        else if (jogador.getNome().equals("Roxo"))
            g2.setColor(Color.magenta); 
        else 
            System.out.println("Error na decisao da cor do joagador");
    }

    private void DesenhaJogador(Casa casa, Jogador jogador, Graphics2D g2){
        
        int posicao = PosicaoJogadorNaCasa(casa, jogador);
        if (posicao == -1) return;

        SetColorG2(jogador, g2);
        int compr = tileSize/7;
            
        switch (posicao) {
            case 0:
                g2.fillRect(casa.getCoordenadaX() + compr, casa.getCoordenadaY() + compr, compr, compr);
                break;
            case 1:
                g2.fillRect(casa.getCoordenadaX() + 3 * compr, casa.getCoordenadaY() + compr, compr, compr);
                break;
            case 2:
                g2.fillRect(casa.getCoordenadaX() + 5 * compr, casa.getCoordenadaY() + compr, compr, compr);
                break;
            case 3:
                g2.fillRect(casa.getCoordenadaX() + compr, casa.getCoordenadaY() + 72, compr, compr);
                break;
            case 4:
                g2.fillRect(casa.getCoordenadaX() + 3 * compr, casa.getCoordenadaY() + 72, compr, compr);
                break;
            case 5:
                g2.fillRect(casa.getCoordenadaX() + 5 * compr, casa.getCoordenadaY() + 72, compr, compr);
                break;
            default:
                System.out.println("Erro na Insercao da Peca\n");
                break;
        }       
    }
        
        
   private void DesenhaBloco(int x, int y, int tileSize, Graphics2D g2, String nomelugar) {
        // Desenhar o bloco branco
        g2.setColor(Color.white);
        g2.fillRect(x, y, tileSize, tileSize);

        // Desenhar a borda do bloco
        g2.setColor(Color.black); // Cor da borda
        g2.drawRect(x, y, tileSize, tileSize);

        // Configurar a cor e a fonte para o texto
        g2.setColor(Color.black);
        g2.setFont(new Font("Arial", Font.BOLD, 10));

        FontMetrics fm = g2.getFontMetrics();
        int textHeight = fm.getAscent();
        
        // Dividir o texto em linhas, considerando o limite de largura do bloco
        int maxTextWidth = tileSize - 10;  // Ajuste de margem
        String[] words = nomelugar.split(" ");
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            // Verificar se a próxima palavra cabe na linha atual
            String testLine = line.length() == 0 ? word : line + " " + word;
            int lineWidth = fm.stringWidth(testLine);

            if (lineWidth <= maxTextWidth) {
                line.append(line.length() == 0 ? word : " " + word);
            } else {
                lines.add(line.toString());
                line = new StringBuilder(word); // Começa uma nova linha com a palavra que não coube
            }
        }
        // Adicionar a última linha
        lines.add(line.toString());

        // Calcular posição para centralizar o texto dentro do bloco
        int totalTextHeight = lines.size() * textHeight;
        int startY = y + (tileSize - totalTextHeight) / 2 + textHeight;

        // Desenhar as linhas de texto no bloco
        for (String lineText : lines) {
            int textWidth = fm.stringWidth(lineText);
            int textX = x + (tileSize - textWidth) / 2;
            g2.drawString(lineText, textX, startY);
            startY += textHeight;
        }

        // Resetar a cor para o próximo bloco
        g2.setColor(Color.white);
    }
}

