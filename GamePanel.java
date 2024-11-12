import java.awt.Color;
import java.awt.Dimension;     
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{
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
    Thread gameThread;

    public GamePanel(ArrayList<Casa> casas, ArrayList<Jogador> jogadores) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true); 
        this.casas = casas;
        this.jogadores = jogadores;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }    
    
    @Override
    public void run(){}
    
    public void update () {}

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
    public static void DesenhaJogadores(ArrayList<Casa> casas, ArrayList<Jogador> jogadores){
        int indexcasa;
        Jogador jogador;
        Casa casa;
        for (int i = 0; i < jogadores.size(); i++){
            jogador = jogadores.get(i);
            indexcasa = jogador.getCasa();
            casa = casas.get(indexcasa);
            for (int j = 0; j< casa.getNumPessoasNaCasa(); j++){
                EncontraEDesenhaJogador(casa, jogador);
            }
        }
        }

    public static int EncontraEDesenhaJogador(Casa casa, Jogador jogador){
        ArrayList<Jogador> jogadoresCasa = new ArrayList<>();
        jogadoresCasa = casa.getJogadores();
        int indexJogadorNaCasa = -1;
        for (int k = 0; k < jogadoresCasa.size(); k++){
            if (jogadoresCasa.get(k).getNome().equals(jogador.getNome()))
                indexJogadorNaCasa = k;
        }
        if (indexJogadorNaCasa == -1) return 0;
        DesenhaJogador(casa, jogador, indexJogadorNaCasa);
        return 1;
    }
    public static int DesenhaJogador(Casa casa, Jogador jogador, int indexJogadorNaCasa){
        if (indexJogadorNaCasa == 0){
            /*Vou fazer a funcao de desenhar os jogadores aqui de acordo com o numero de jogadores na casa */
        }
        return 1;
    }
        



}

