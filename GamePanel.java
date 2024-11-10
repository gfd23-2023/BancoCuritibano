package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;       
import java.awt.Graphics2D;     
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.util.ArrayList;


public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    final int originalTileSize = 16;    
    final int scale = 6;    
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 8; 
    final int maxScreenRow = 8;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;   
    private ArrayList<String> Lugares = new ArrayList<>();
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true); 

        Lugares.add("Início");
        Lugares.add("Passeio Público");
        Lugares.add("Sorte ou    Azar?");
        Lugares.add("Calçadão R.XV");
        Lugares.add("Gibiteca");
        Lugares.add("UFPR Prédio Histórico");
        Lugares.add("Paço da Liberdade");
        Lugares.add("Cadeia");
        Lugares.add("Sorte ou    Azar?");
        Lugares.add("Museu Oscar Niemeyer");
        Lugares.add("Santa Felicidade");
        Lugares.add("UFPR Politécnico");
        Lugares.add("UFPR Jardim Botânico");
        Lugares.add("Jardim Botânico");
        Lugares.add("Linha de Turismo (Volte para o Início)");
        Lugares.add("Teatro Guaíra");
        Lugares.add("Shopping Barigui");
        Lugares.add("Parque Barigui");
        Lugares.add("Mercado Municipal");
        Lugares.add("Parque Tingui");
        Lugares.add("Bosque Alemão");
        Lugares.add("BLITZ (Pague R$100 ou vá preso)");
        Lugares.add("Praça do Japão");
        Lugares.add("Sorte ou    Azar?");
        Lugares.add("Torre Panorâmica");
        Lugares.add("Catedral Curitiba");
        Lugares.add("Shopping Pátio Batel");
        Lugares.add("Prefeitura de Curitiba");
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }    
    
    @Override
    public void run(){
        //while(gameThread != null){
        //    update();
        //    repaint();
        //}
    }
    
    public void update () {}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Definir a cor dos blocos como branca
        g2.setColor(Color.white);
        
        int x = 96*7;
        int y = 96*7;
        int i = 0;
        for (x = 96 * 7; x >= 0 && i < Lugares.size(); x -= 96) {
            DesenhaBloco(x, y, tileSize, g2, Lugares.get(i));
            i++; 
        }
        x = 0;
        for (y = 96 * 6; y >= 0 && i < Lugares.size(); y -= 96){
            DesenhaBloco(x, y, tileSize, g2, Lugares.get(i));
            i++; 
        }
        y = 0;
        for (x = 96; x<= 96*7 && i < Lugares.size(); x += 96){
            DesenhaBloco(x, y, tileSize, g2, Lugares.get(i));
            i++;
        }
        x = 96*7;
        for (y = 96; y <= 96*7 && i < Lugares.size(); y += 96){
            DesenhaBloco(x, y, tileSize, g2, Lugares.get(i));
            i++;
        }
        g2.dispose();
    }

    public void DesenhaBloco(int x, int y, int tileSize, Graphics2D g2, String nomelugar) {
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

