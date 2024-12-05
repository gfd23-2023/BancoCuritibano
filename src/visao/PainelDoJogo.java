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

public class PainelDoJogo extends JPanel {
    // Atributos de Configuracao de Tela
    final int tamanhoOriginalTela = 16;    
    final int escala = 6;    
    final int tamanhoTile = tamanhoOriginalTela * escala;
    final int maxColunasTela = 8; 
    final int maxLinhasTela = 8;
    final int screenWidth = tamanhoTile * maxColunasTela;
    final int screenHeight = tamanhoTile * maxLinhasTela; 

    //Atributos de Estado do Jogo
    private int rodada;  
    private boolean botaoProxRodada;
    private Jogador jogadorDaRodada;
    private int valorDados;

    //Atributos de classes Modelo 
    final Dado dado1 = new Dado();
    final Dado dado2 = new Dado();
    private ArrayList<Casa> casas = new ArrayList<>();
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    //Atributos de classes privadas responsáveis pela Visão
    final BotaoRodada botaoRodada =  new BotaoRodada();
    final BotoesDado botoesDado = new BotoesDado();
    final DesenhaJogadoresNoTabuleiro jogadoresNoTabuleiro = new DesenhaJogadoresNoTabuleiro();
    final DesenhaCasasNoTabuleiro casasNoTabuleiro = new DesenhaCasasNoTabuleiro();
    final PaineisAuxiliares paineisAuxiliares = new PaineisAuxiliares();


    //Funcao Construtora
    public PainelDoJogo(ArrayList<Casa> casas, ArrayList<Jogador> jogadores) {
      
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Define as dimensões preferidas do painel
        this.setBackground(Color.darkGray); // Define a cor de fundo do painel
        this.setDoubleBuffered(true); // Habilita o uso de buffer duplo para melhorar o desempenho gráfico
        this.casas = casas; // Inicializa as listas de casas com os valores recebidos
        this.jogadores = jogadores; // Inicializa as listas de jogadores com os valores recebidos
        this.botaoProxRodada = false; // Inicializa o estado do botão de próxima rodada como desativado

    }

    //retorna o tamanho do tile do painelJogo
    public int getTamanhoTile(){
        return this.tamanhoTile; 
    }

    //define a rodada e o jogador da rodada do jogo
    public void setRodada(int rodada, Jogador jogadorDaRodada){
        this.rodada = rodada;
        this.jogadorDaRodada = jogadorDaRodada;
    }

    //retorna a rodada que o jogo está
    public int getRodada(){
        return this.rodada;
    }

    //coloca em valorDados o resultado da soma de cada numero dos dados 1 e 2 
    public void puxaValorDados(){
        this.valorDados = dado1.getValor() + dado2.getValor();
    }

    //retorna o valor de valorDados
    public int getValorDados() {
        return valorDados;
    }
    
    //seta um valor para variavel valorDados
    public void setValorDados(int valorDados){
        this.valorDados = valorDados;
    }
    //define se o botao de proxima rodada foi pressionado ou nao
    public void setbotaoProxRodada(boolean expressao){
        this.botaoProxRodada = expressao;
    }

    //retorna o valor boolean que indica se o botao de proxima rodada foi pressionado ou nao
    public boolean getbotaoProxRodada(){
        return this.botaoProxRodada;
    }

    //define como falso que o atributo de dados rolados dentro do dado
    public void setFalseDadosRolados(){
        dado1.setDadoRolado(false);
        dado2.setDadoRolado(false);
        //dado1Rolado = false;
        //dado2Rolado = false;
    }

    //retorna o valor boolean que diz se os dados já foram rolados ou nao 
    public boolean getDadosRolados(){
        //System.out.println ("Dados rolado " + (dado1Rolado && dado2Rolado));
        //return (dado1Rolado && dado2Rolado);
        return(dado1.getDadoRolado() && dado2.getDadoRolado());
    }

    //configura o jogo para ir para proxima rodada
    public void Aumenta1Rodada(){
        this.rodada = this.rodada +1;
    }

    //retorna o jogador da rodada
    public Jogador getJogadorRodada (){
        return this.jogadorDaRodada;
    }

    //funcao que controla as classes responsáveis por exibição
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        casasNoTabuleiro.Exibe(g2, casas, getTamanhoTile());
        jogadoresNoTabuleiro.Exibe(casas, jogadores, g2);
        botoesDado.Exibe(this, g2);
        botaoRodada.Exibe(this); 
        paineisAuxiliares.Exibe(this, g2, jogadores);
    }

    //atualiza a tela chamando a funcao repaint()
    public void atualizarTela() {
        repaint(); // Solicita uma atualização do componente
    }

}

class PaineisAuxiliares{
    //Exibe os Paineis Auxiliares chamando metodos responsaveis por isso
    public void Exibe(PainelDoJogo painelJogo, Graphics2D g2, ArrayList<Jogador> jogadores){
        int valorDados = painelJogo.getValorDados();
        if (painelJogo.getRodada() != 0 && !painelJogo.getbotaoProxRodada() ){
            DesenhaPainel(g2, painelJogo.getRodada(), painelJogo.getJogadorRodada(), valorDados);
            DesenhaPainelProxRodada(g2,(jogadores.get((painelJogo.getJogadorRodada().getId()+1)%6)));
        }
        else{
            DesenhaPainelProxRodada(g2,(jogadores.get(0)));
        }

    }
    
    //Exibe o painel da Rodada Atual 
    private void DesenhaPainel (Graphics2D g2, int rodada, Jogador jogadorDaRodada, int valorDados){
        
        g2.setFont(new Font("New Courier", Font.PLAIN, 11)); // Define o estilo da fonte para o texto
        g2.setColor(Color.BLACK); // Define a cor do texto como preta

        int boxWidth = 222; // Largura da caixa de texto
        int boxHeight = 100; // Altura da caixa de texto
        int xPosition = 132; // Posição X da caixa de texto
        int yPosition = 122; // Posição Y da caixa de texto

        g2.setColor(new Color(220, 220, 220, 200)); // Define a cor do fundo da caixa com transparência
        g2.fillRect(xPosition, yPosition, boxWidth, boxHeight); // Desenha o fundo da caixa de texto

        g2.setColor(Color.BLACK); // Define a cor do texto para exibição
        String infoText = String.format(
            "Rodada: %d\nJogador da Rodada: %s\nValor dos Dados: %d\nCasaAtual: %d", 
            rodada, jogadorDaRodada.getNome(), valorDados, jogadorDaRodada.getCasa()
        );
        String[] lines = infoText.split("\n"); // Divide o texto em linhas para exibição
        for (int i = 0; i < lines.length; i++) {
            g2.drawString(lines[i], xPosition + 10, yPosition + 20 + (i * 15)); // Desenha cada linha na posição calculada
        }

    }

    private void DesenhaPainelProxRodada (Graphics2D g2, Jogador jogadorDaRodada){
        g2.setFont(new Font("New Courier", Font.PLAIN, 11)); // Define o estilo da fonte para o texto
        g2.setColor(Color.BLACK); // Define a cor do texto como preta

        int boxWidth = 222; // Largura da caixa de texto
        int boxHeight = 80; // Altura da caixa de texto
        int xPosition = 132; // Posição X da caixa de texto
        int yPosition = 250; // Posição Y da caixa de texto

        g2.setColor(new Color(220, 220, 220, 200)); // Define a cor do fundo da caixa com transparência
        g2.fillRect(xPosition, yPosition, boxWidth, boxHeight); // Desenha o fundo da caixa de texto

        g2.setColor(Color.BLACK); // Define a cor do texto para exibição
        boolean podejogar = !(jogadorDaRodada.estaNaCadeia()) || !(jogadorDaRodada.estaEsperando()); // Verifica se o jogador pode jogar
        String infoText;

        if (podejogar) {
            infoText = String.format(
                "Próxima Rodada Jogador %s\nCasaAtual: %d\nJOGUE OS DADOS!", 
                jogadorDaRodada.getNome(), jogadorDaRodada.getCasa()
            ); // Mensagem se o jogador pode jogar
        } else {
            infoText = String.format(
                "Próxima Rodada Jogador %s\nCasaAtual: %d\nJOGADOR NAO PODE JOGAR", 
                jogadorDaRodada.getNome(), jogadorDaRodada.getCasa()
            ); // Mensagem se o jogador não pode jogar
        }

        String[] lines = infoText.split("\n"); // Divide o texto em linhas para exibição
        for (int i = 0; i < lines.length; i++) {
            g2.drawString(lines[i], xPosition + 10, yPosition + 20 + (i * 15)); // Desenha cada linha na posição calculada
        }

    }

}

class DesenhaCasasNoTabuleiro{
    public void Exibe (Graphics2D g2, ArrayList<Casa> casas, int tamanhoTile){
        //For que passa casa por casa desenhando-as conforme suas coordenadas e configuracoes do PainelDoJogo
        for (int i = 0; i < casas.size(); i++) {
            DesenhaBloco(casas.get(i).getCoordenadaX(),
                        casas.get(i).getCoordenadaY(),
                        tamanhoTile, g2, casas.get(i).getNome());
        }
    }
    
    private void DesenhaBloco(int x, int y, int tamanhoTile, Graphics2D g2, String nomelugar) {
        // Desenhar o bloco branco
        g2.setColor(Color.white); // Define a cor do bloco
        g2.fillRect(x, y, tamanhoTile, tamanhoTile); // Preenche o bloco com a cor branca

        // Desenhar a borda do bloco
        g2.setColor(Color.black); // Cor da borda
        g2.drawRect(x, y, tamanhoTile, tamanhoTile); // Desenha a borda do bloco

        // Configurar a cor e a fonte para o texto
        g2.setColor(Color.black); // Cor do texto
        g2.setFont(new Font("Arial", Font.BOLD, 10)); // Define a fonte para o texto

        FontMetrics fm = g2.getFontMetrics(); // Obter métricas da fonte para calcular o espaço do texto
        int textHeight = fm.getAscent(); // Altura do texto para centralizar

        // Dividir o texto em linhas, considerando o limite de largura do bloco
        int maxTextWidth = tamanhoTile - 10;  // Ajuste de margem para não encostar nas bordas
        String[] words = nomelugar.split(" "); // Dividir o nome do lugar em palavras
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            // Verificar se a próxima palavra cabe na linha atual
            String testLine = line.length() == 0 ? word : line + " " + word;
            int lineWidth = fm.stringWidth(testLine); // Calcular a largura da linha testada

            if (lineWidth <= maxTextWidth) {
                line.append(line.length() == 0 ? word : " " + word); // Adiciona palavra à linha
            } else {
                lines.add(line.toString()); // Adiciona a linha cheia ao array
                line = new StringBuilder(word); // Começa uma nova linha com a palavra que não coube
            }
        }
        // Adicionar a última linha
        lines.add(line.toString()); // Adiciona a última linha ao array

        // Calcular posição para centralizar o texto dentro do bloco
        int totalTextHeight = lines.size() * textHeight; // Altura total do texto
        int startY = y + (tamanhoTile - totalTextHeight) / 2 + textHeight; // Calcula a posição Y para centralizar

        // Desenhar as linhas de texto no bloco
        for (String lineText : lines) {
            int textWidth = fm.stringWidth(lineText); // Largura de cada linha de texto
            int textX = x + (tamanhoTile - textWidth) / 2; // Calcula a posição X para centralizar
            g2.drawString(lineText, textX, startY); // Desenha o texto no bloco
            startY += textHeight; // Ajusta a posição Y para a próxima linha de texto
        }

        // Resetar a cor para o próximo bloco
        g2.setColor(Color.white); // Reinicia a cor para a próxima operação

    }
}

class DesenhaJogadoresNoTabuleiro{
    public void Exibe (ArrayList <Casa> casas, ArrayList <Jogador> jogadores, Graphics2D g2){
        // For que passa desenhando jogador por jogador de acordo com suas atributos
        for (int i = 0; i < 6; i++){
            DesenhaJogador(casas.get(jogadores.get(i).getCasa()), jogadores.get(i), g2);
        }
    }
    
    private void DesenhaJogador(Casa casa, Jogador jogador, Graphics2D g2){
        
        DefineCorJogador(jogador, g2); //Define qual cor vai ser usada para exibir o jogador
        int compr = 14;
            
        switch (jogador.getId()) { //Define a onde que o jogador vai ser desenhado conforme seu id e a casa que está
            case 0 -> g2.fillRect(casa.getCoordenadaX() + compr, casa.getCoordenadaY() + compr, compr, compr);
            case 1 -> g2.fillRect(casa.getCoordenadaX() + 3 * compr, casa.getCoordenadaY() + compr, compr, compr);
            case 2 -> g2.fillRect(casa.getCoordenadaX() + 5 * compr, casa.getCoordenadaY() + compr, compr, compr);
            case 3 -> g2.fillRect(casa.getCoordenadaX() + compr, casa.getCoordenadaY() + 72, compr, compr);
            case 4 -> g2.fillRect(casa.getCoordenadaX() + 3 * compr, casa.getCoordenadaY() + 72, compr, compr);
            case 5 -> g2.fillRect(casa.getCoordenadaX() + 5 * compr, casa.getCoordenadaY() + 72, compr, compr);
            default -> System.out.println("Erro na Insercao da Peca\n");
        }       
    }
    
    //Define a cor que o gráfico vai usar conforme o id do johador
    private void DefineCorJogador(Jogador jogador, Graphics2D g2){
        switch (jogador.getId()) {
            case 0 -> g2.setColor(Color.red);
            case 1 -> g2.setColor(Color.green);
            case 2 -> g2.setColor(Color.gray);
            case 3 -> g2.setColor(Color.yellow);
            case 4 -> g2.setColor(Color.blue);
            case 5 -> g2.setColor(Color.magenta);
            default -> System.out.println("Error na decisao da cor do joagador");
        }
    }
}

class BotaoRodada{
    //Exibe uma botão pra os jogadores poderem ir para próxima rodada e assim continuar o jogo
    public void Exibe(PainelDoJogo painelJogo){
        // Use HTML para exibir o texto em duas linhas
        JButton btnRodada = new JButton("<html>Próxima<br>Rodada</html>");
        Font fontePersonalizada = new Font("Arial", Font.PLAIN, 9); //Define a fonte que vai ser usada 
        btnRodada.setFont(fontePersonalizada); 
        btnRodada.setBackground(new Color(255, 182, 193)); // Rosa claro (Light Pink)

        btnRodada.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // Adicionar ação ao botão
        btnRodada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painelJogo.setbotaoProxRodada(true);
                painelJogo.Aumenta1Rodada();
            }
            
        });
        
        painelJogo.setLayout(null); // Usar layout nulo para posicionamento manual
        btnRodada.setBounds(painelJogo.tamanhoTile * 4 + 145, painelJogo.tamanhoTile + 140, 65, 40); // Ajuste a posição e tamanho, se necessário
        painelJogo.add(btnRodada);
    }
}

class BotoesDado{
    public void Exibe (PainelDoJogo painelJogo, Graphics2D g2){
        ExibeBotoesDado(painelJogo, g2);
        ExibeResultadosDado(g2, painelJogo);
    }

    private void ExibeBotoesDado(PainelDoJogo painelJogo, Graphics2D g2){
        //Declara e define as escritas que serão feitas nos botões
        JButton btnDado1 = new JButton("Rolar Dado 1");
        JButton btnDado2 = new JButton("Rolar Dado 2");

        //Define como os textos seram exibidos em ambos botões
        Font fontePersonalizada = new Font("Arial", Font.PLAIN, 10); // Fonte Arial, estilo normal, tamanho 12
        btnDado1.setFont(fontePersonalizada);
        btnDado2.setFont(fontePersonalizada);

        // Definir borda preta e cor de fundo sólida para os botões
        btnDado1.setBackground(new Color(255, 179, 102)); // Cor de fundo sólida
        btnDado1.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta
        btnDado2.setBackground(new Color(255, 179, 102)); // Cor de fundo sólida
        btnDado2.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta

        // Define o que acontece assim que o botão do dado 1 é pressionado 
        btnDado1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!painelJogo.dado1.getDadoRolado()){  //verifica se o dado ja foi jogado nessa rodada
                    painelJogo.dado1.jogaDados();        //joga os dados (fornece uma numero aleatorio de 1 a 6)
                    painelJogo.dado1.setDadoRolado(true); //muda a condicao do dado dizendo que ele já foi rolado nessa jogada
                    //ExibeResultadosDado(g2, painelJogo);
                    painelJogo.repaint();
                }
                else {
                    System.out.println("Já rolou os dados nessa jogada"); //aparece se o usuario já rolou o dado 1 nessa jogada
                }
            }
        });
        
        // Define o que acontece assim que o botão do dado 2 é pressionado 
        btnDado2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!painelJogo.dado2.getDadoRolado()){   //verifica se o dado ja foi jogado nessa rodada
                    painelJogo.dado2.jogaDados();        //joga os dados (fornece uma numero aleatorio de 1 a 6)
                    painelJogo.dado2.setDadoRolado(true);    //muda a condicao do dado dizendo que ele já foi rolado nessa jogada
                    //ExibeResultadosDado(g2, painelJogo);
                    painelJogo.repaint();    //repinta o painel de jogo
                }
                else{
                    System.out.println("Já rolou os dados nessa jogada"); //aparece se o usuário já rolou o dado 1 nessa jogada
                }
            }
        });

        // Adicionar botões ao painel
        painelJogo.setLayout(null); // Usar layout nulo para posicionar manualmente
        btnDado1.setBounds(painelJogo.tamanhoTile*4 +30, painelJogo.tamanhoTile + 46, 100, 60); // Posição do botão 1
        btnDado2.setBounds(painelJogo.tamanhoTile*4 + 130,  painelJogo.tamanhoTile + 46 , 100, 60); // Posição do botão 2

        painelJogo.add(btnDado1);
        painelJogo.add(btnDado2);
    }

    private void ExibeResultadosDado(Graphics2D g2, PainelDoJogo painelJogo) {
        g2.setColor(Color.white); // Define a cor do texto como branco
        g2.setFont(new Font("Arial", Font.BOLD, 10)); // Define a fonte e o tamanho do texto

        // Cria as strings que exibem os valores dos dados
        String dado1Text = "Dado 1: " + painelJogo.dado1.getValor(); 
        String dado2Text = "Dado 2: " + painelJogo.dado2.getValor();

        // Desenha as strings de texto nas posições especificadas
        g2.drawString(dado1Text, painelJogo.getTamanhoTile() * 4 +38 , painelJogo.getTamanhoTile() * 2 +30); // Desenha o valor do Dado 1 em uma posição visível
        g2.drawString(dado2Text, painelJogo.getTamanhoTile() * 4 + 138, painelJogo.getTamanhoTile() * 2 + 30); // Desenha o valor do Dado 2 um pouco à direita do Dado 1
    
    }

}
