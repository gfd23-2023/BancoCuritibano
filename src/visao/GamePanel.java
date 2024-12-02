package visao;
import java.awt.Color;
import java.awt.Dimension;     
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import modelo.Dado;
import modelo.Jogador;
import modelo.casas.*;

public class GamePanel extends JPanel {
    // Atributos de Configuracao de Tela
    final int originalTileSize = 16;    
    final int scale = 6;    
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 8; 
    final int maxScreenRow = 8;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow; 

    //Atributos de Estado do Jogo
    private int rodada;  
    private boolean continuar;
    private Jogador jogadorDaRodada;
    private int valorDados;
    private boolean dado1Rolado = false;    //rever se tal atributo deve estar aqui ou dentro da classe dado
    private boolean dado2Rolado = false;    //rever se tal atributo deve estar aqui ou dentro da classe dado
    
    //Classes externas que serão usadas
    final Dado dado1 = new Dado();
    final Dado dado2 = new Dado();
    private ArrayList<Casa> casas = new ArrayList<>();
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    //Funcao Construtora
    public GamePanel(ArrayList<Casa> casas, ArrayList<Jogador> jogadores) {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true); 
        this.casas = casas;
        this.jogadores = jogadores;
        AdicionarBotoesDado(this.dado1, this.dado2);
        AdicionarBotaoRodada();
        this.continuar = false;
       
    }

    //Funcoes Públicas Curtas
    public void setRodada(int rodada, Jogador jogadorDaRodada){
        this.rodada = rodada;
        this.jogadorDaRodada = jogadorDaRodada;
    }

    public int getRodada(){
        return this.rodada;
    }

    public void setValorDados(int valorDados){
        this.valorDados = valorDados;
    }

    public int RetornaValorDados() {
            return dado1.getValor() + dado2.getValor(); // Somar valores
    }
             
    public void SetContinuar(boolean expressao){
        this.continuar = expressao;
    }

    public boolean getContinuar(){
        return this.continuar;
    }

    public void setFalseDadosRolados(){
        dado1Rolado = false;
        dado2Rolado = false;
    }

    public boolean getDadosRolados(){
        System.out.println ("Dados rolado " + (dado1Rolado && dado2Rolado));
        return (dado1Rolado && dado2Rolado);
    }

    public void Aumenta1Rodada(){
        this.rodada = this.rodada +1;
    }

    //Funcoes Privadas Relacionadas com Desenho
    private void AdicionarBotaoRodada() {
        // Use HTML para exibir o texto em duas linhas
        JButton btnRodada = new JButton("<html>Próxima<br>Rodada</html>");
        Font fontePersonalizada = new Font("Arial", Font.PLAIN, 8);
        btnRodada.setFont(fontePersonalizada);
        
        //btnRodada.setBackground(Color.lightGray);
        btnRodada.setBackground(new Color(255, 182, 193)); // Rosa claro (Light Pink)

        btnRodada.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // Adicionar ação ao botão
        btnRodada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetContinuar(true);
                Aumenta1Rodada();
            }
            
        });
        
        // Configurar layout para posicionar o botão manualmente
        this.setLayout(null); // Usar layout nulo para posicionamento manual
        btnRodada.setBounds(tileSize * 4 + 130, tileSize + 120, 60, 50); // Ajuste a posição e tamanho, se necessário
        this.add(btnRodada);
    }

    private void AdicionarBotoesDado(Dado dado1, Dado dado2){
        JButton btnDado1 = new JButton("Rolar Dado 1");
        JButton btnDado2 = new JButton("Rolar Dado 2");

        Font fontePersonalizada = new Font("Arial", Font.PLAIN, 10); // Fonte Arial, estilo normal, tamanho 12
        btnDado1.setFont(fontePersonalizada);
        btnDado2.setFont(fontePersonalizada);

        // Definir borda preta e cor de fundo sólida para os botões
        btnDado1.setBackground(Color.LIGHT_GRAY); // Cor de fundo sólida
        btnDado1.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta
        btnDado2.setBackground(Color.LIGHT_GRAY); // Cor de fundo sólida
        btnDado2.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta


        btnDado1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!dado1Rolado){
                    dado1.jogaDados();
                    dado1Rolado = true;
                    repaint();
                }
                else {
                    System.out.println("Já rolou os dados nessa jogada");
                }
            }
        });
        
        btnDado2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if dadosroladas = true nao nao deixar rolar dnv
                // deixar eu rodar os dados somente quando set continuar == nao 
                    //fazer tipo uma funcao que libera para os dados rolar se set continuar == nao 
                if(!dado2Rolado){
                    dado2.jogaDados();
                    dado2Rolado = true;
                    repaint();
                }
                else{
                    System.out.println("Já rolou os dados nessa jogada");
                }
            }
        });

        // Adicionar botões ao painel
        this.setLayout(null); // Usar layout nulo para posicionar manualmente
        btnDado1.setBounds(tileSize*4 , tileSize + 16, 100, 50); // Posição do botão 1
        btnDado2.setBounds(tileSize*4 + 100,  tileSize + 16 , 100, 50); // Posição do botão 2

        this.add(btnDado1);
        this.add(btnDado2);
    }

    private void DesenhaResultadosDado(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.BOLD, 10));
        String dado1Text = "Dado 1: " + dado1.getValor();
        String dado2Text = "Dado 2: " + dado2.getValor();
    
        // Desenhar os textos em posições visíveis e longe dos botões
        g2.drawString(dado1Text, tileSize * 4 +7 , tileSize * 2); // Posição abaixo
        g2.drawString(dado2Text, tileSize * 4 + 107, tileSize * 2);
    }

    private void DesenhaPainel (Graphics2D g2, int rodada, Jogador jogadorDaRodada, int valorDados){
        // Definir o estilo da fonte e cor para o texto
        g2.setFont(new Font("Arial", Font.PLAIN, 10));
        g2.setColor(Color.BLACK);

        int boxWidth = 220;
        int boxHeight = 100;
        int xPosition = 122;
        int yPosition = 122;

        // Desenhar a caixa de fundo para o texto
        g2.setColor(new Color(220, 220, 220, 200)); // Fundo cinza com um pouco de transparência
        g2.fillRect(xPosition, yPosition, boxWidth, boxHeight);

        // Desenhar as informações na caixa de texto
        g2.setColor(Color.BLACK);
        String infoText = String.format
        ("Rodada: %d\nJogador da Rodada: %s\nValor dos Dados: %d\nCasaAtual: %d", 
        rodada, jogadorDaRodada.getNome(), valorDados, jogadorDaRodada.getCasa());
        String[] lines = infoText.split("\n");
        for (int i = 0; i < lines.length; i++) {
            g2.drawString(lines[i], xPosition + 10, yPosition + 20 + (i * 15));
        }
    }

    private void DesenhaPainelProxRodada (Graphics2D g2, Jogador jogadorDaRodada){
        g2.setFont(new Font("Arial", Font.PLAIN, 10));
        g2.setColor(Color.BLACK);

        // Caixa de texto no canto superior direito
        int boxWidth = 220;
        int boxHeight = 60;
        int xPosition = 122;
        int yPosition = 250;

        // Desenhar a caixa de fundo para o texto
        g2.setColor(new Color(220, 220, 220, 200)); // Fundo cinza com um pouco de transparência
        g2.fillRect(xPosition, yPosition, boxWidth, boxHeight);

        // Desenhar as informações na caixa de texto
        g2.setColor(Color.BLACK);
        boolean podejogar = !(jogadorDaRodada.estaNaCadeia()) || !(jogadorDaRodada.estaEsperando());
        String infoText;
        if (podejogar){
            infoText = String.format
            ("Próxima Rodada Jogador %s\nCasaAtual: %d\nJOGUE OS DADOS!", 
            jogadorDaRodada.getNome(), jogadorDaRodada.getCasa());
        }
        else{
            infoText = String.format
            ("Próxima Rodada Jogador %s\nCasaAtual: %d\nJOGADOR NAO PODE JOGAR", 
            jogadorDaRodada.getNome(), jogadorDaRodada.getCasa());
        }

        String[] lines = infoText.split("\n");
        for (int i = 0; i < lines.length; i++) {
            g2.drawString(lines[i], xPosition + 10, yPosition + 20 + (i * 15));
        }

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
        
        SetColorG2(jogador, g2);
        int compr = tileSize/7;
            
        switch (jogador.getId()) {
            case 0 -> g2.fillRect(casa.getCoordenadaX() + compr, casa.getCoordenadaY() + compr, compr, compr);
            case 1 -> g2.fillRect(casa.getCoordenadaX() + 3 * compr, casa.getCoordenadaY() + compr, compr, compr);
            case 2 -> g2.fillRect(casa.getCoordenadaX() + 5 * compr, casa.getCoordenadaY() + compr, compr, compr);
            case 3 -> g2.fillRect(casa.getCoordenadaX() + compr, casa.getCoordenadaY() + 72, compr, compr);
            case 4 -> g2.fillRect(casa.getCoordenadaX() + 3 * compr, casa.getCoordenadaY() + 72, compr, compr);
            case 5 -> g2.fillRect(casa.getCoordenadaX() + 5 * compr, casa.getCoordenadaY() + 72, compr, compr);
            default -> System.out.println("Erro na Insercao da Peca\n");
        }       
    }

    private void DesenhaJogadores (ArrayList <Casa> casas, ArrayList <Jogador> jogadores, Graphics2D g2){
        for (int i = 0; i < 6; i++){
            DesenhaJogador(casas.get(jogadores.get(i).getCasa()), jogadores.get(i), g2);
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

    //Funcoes Públicas de Desenho
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Desenhar o fundo e outros elementos
        g2.setColor(Color.white);

        for (int i = 0; i < casas.size(); i++) {
            DesenhaBloco(casas.get(i).getCoordenadaX(),
                        casas.get(i).getCoordenadaY(),
                        tileSize, g2, casas.get(i).getNome());
        }
        DesenhaJogadores(casas, jogadores, g2);

        // Certificar-se de que os textos de Dado estão desenhando
        DesenhaResultadosDado(g2);
        if (rodada != 0){
            DesenhaPainel(g2, rodada, jogadorDaRodada, valorDados);
            DesenhaPainelProxRodada(g2,(jogadores.get((jogadorDaRodada.getId()+1)%6)));
        }
        else{
            DesenhaPainelProxRodada(g2,(jogadores.get(0)));
        }
    }

    public void atualizarTela() {
        repaint(); // Solicita uma atualização do componente
    }


}

