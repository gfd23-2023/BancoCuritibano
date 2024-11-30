//TEMOS QUE PENSAR NO BOTÃO JOGAR
//IDEIA: USAR O ESTADO DE "BOTÃO PRESSIONADO" PARA INFORMAR A AÇÃO FEITA
//DEPOIS, TABULEIRO VAI CUIDAR DAS REQUISIÇÕES DA VISÃO, OU SEJA, VERIFICA OS ESTADOS DO JOGO
//E, COM BASE NESSES ESTADOS, ELE CHAMA OS MÉTODOS/ CLASSES PARA TRATAR O ESTADO

/*Criar uma tela inicial e iniciar o menu*/

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.*;
import java.io.File;
import java.awt.*;

//criar e colocar o frame na tela
class TelaInicial extends JPanel
{
	private BufferedImage logo;

	public TelaInicial(String caminho)
	{
		try
		{
			logo = ImageIO.read(new File(caminho));
			repaint();
		} catch (IOException e)
		{
			System.out.printf("Erro ao carregar imagem %s\n", e.getMessage());
		}

		setBackground(new Color(255, 228, 225));
	}
		
	//Sobrescreve o método paintComponent para desenhar a imagem
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (logo != null)
		{
			int larguraOriginal = logo.getWidth()/2;
            int alturaOriginal = logo.getHeight()/2;

			// Obtém as dimensões do painel e acha o meio
			int larguraPainel = this.getWidth()/2;
			int alturaPainel = this.getHeight()/2;

			g.drawImage(logo, larguraPainel - larguraOriginal, alturaPainel - alturaOriginal, 
						larguraOriginal*2, 2*alturaOriginal, this);
		}
	}
}

//Cria os botões
//Jogar
class Jogar extends JPanel
{
	JButton jogar = new JButton("Jogar!");
	JLabel texto = new JLabel(String.format("Digite seu nome!"));
	JPanel painelTexto = new JPanel();
	//campos para escrever os nomes dos jogadores	
	private JTextField jogador1 = new JTextField(20);
	private JTextField jogador2 = new JTextField(20);
	private JTextField jogador3 = new JTextField(20);
	private JTextField jogador4 = new JTextField(20);
	private JTextField jogador5 = new JTextField(20);
	private JTextField jogador6 = new JTextField(20);

	public Jogar()
	{
		jogar.setFont(new Font("Times New Roman", Font.BOLD, 25));
        jogar.setBackground(new Color(255,192,203));
        jogar.setForeground(new Color(250,128,114));

		add(jogar);
	}

	public void PersonalizaCampos()
	{
		//personaliza a label
		texto.setVerticalAlignment(SwingConstants.TOP);
		texto.setFont(new Font("Times New Roman", Font.BOLD, 30));
		texto.setForeground(new Color(250,128,114));

		//define os tamanhos dos campos para escrever os nomes
		jogador1.setPreferredSize(new Dimension(50, 40));       //50px x 40x
        jogador2.setPreferredSize(new Dimension(50, 40));
        jogador3.setPreferredSize(new Dimension(50, 40));
        jogador4.setPreferredSize(new Dimension(50, 40));
        jogador5.setPreferredSize(new Dimension(50, 40));
        jogador6.setPreferredSize(new Dimension(50, 40));

		//ajusta as bordas cor = IndianRed
		jogador1.setBorder(BorderFactory.createLineBorder(new Color(205,92,92), 2));
		jogador2.setBorder(BorderFactory.createLineBorder(new Color(205,92,92), 2));
		jogador3.setBorder(BorderFactory.createLineBorder(new Color(205,92,92), 2));
		jogador4.setBorder(BorderFactory.createLineBorder(new Color(205,92,92), 2));
		jogador5.setBorder(BorderFactory.createLineBorder(new Color(205,92,92), 2));
		jogador6.setBorder(BorderFactory.createLineBorder(new Color(205,92,92), 2));

		//personaliza a fonte
		jogador1.setFont(new Font("Courier New", Font.BOLD, 15));
		jogador2.setFont(new Font("Courier New", Font.BOLD, 15));
		jogador3.setFont(new Font("Courier New", Font.BOLD, 15));
		jogador4.setFont(new Font("Courier New", Font.BOLD, 15));
		jogador5.setFont(new Font("Courier New", Font.BOLD, 15));
		jogador6.setFont(new Font("Courier New", Font.BOLD, 15));

		//cor do fundo
		jogador1.setBackground(new Color(250,240,230));
		jogador2.setBackground(new Color(250,240,230));
		jogador3.setBackground(new Color(250,240,230));
		jogador4.setBackground(new Color(250,240,230));
		jogador5.setBackground(new Color(250,240,230));
		jogador6.setBackground(new Color(250,240,230));
	}

	public void BotaoJogar(JFrame janela)
	{
		jogar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
				//limpa a tela
				janela.getContentPane().removeAll();

				//define cor de fundo para o painel
				painelTexto.setBackground(new Color(255, 228, 225));

				//utiliza o gridbaglayout para empilhar os paineis verticalmente
				painelTexto.setLayout(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();//pelo que eu entendi esse layout é como uma matriz
				gbc.gridx = 0;							//coluna
				gbc.gridy = 0;							//linha
				gbc.insets =  new Insets(10, 0, 10, 0);	//espaçamento (topo, esquerda, baixo, direita)

				//adiciona o texto no painel
				painelTexto.add(texto, gbc);
				gbc.gridy = 1;			//posição na matriz do layout

				//autoexplicativo
				PersonalizaCampos();

				//posicionamento dos campos no layout
				painelTexto.add(jogador1, gbc);
				gbc.gridy = 2;					//posição na matriz do layout
				painelTexto.add(jogador2, gbc);
				gbc.gridy = 3;
				painelTexto.add(jogador3, gbc);
				gbc.gridy = 4;
				painelTexto.add(jogador4, gbc);
				gbc.gridy = 5;
				painelTexto.add(jogador5, gbc);
				gbc.gridy = 6;
				painelTexto.add(jogador6, gbc);

				//adiciona  o painel na tela
				janela.getContentPane().add(painelTexto);

				//atualiza a tela
				janela.revalidate();
				janela.repaint();
            }

        });
	}
}

//Carregar Jogo
class CarregarJogo extends JPanel
{
	JButton carregarJogo = new JButton("Carregar Jogo");

	//possivelmente vamos precisar passar um arquivo como parâmetro
	public CarregarJogo()
	{
		carregarJogo.setFont(new Font("Times New Roman", Font.BOLD, 25));
        carregarJogo.setBackground(new Color(255,192,203));
        carregarJogo.setForeground(new Color(250,128,114));

		add(carregarJogo);
	}

	public void BotaoCarregarJogo()
	{
		carregarJogo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Colocar a lógica de carregar o jogo aqui
            }
        });
	}
}

//Sair
class Sair extends JPanel
{
	JButton sair = new JButton("Sair!");

	public Sair()
	{
		sair.setFont(new Font("Times New Roman", Font.BOLD, 25));
        sair.setBackground(new Color(255,192,203));
        sair.setForeground(new Color(250,128,114));

		add(sair);
	}

	public void BotaoSair (JFrame janela)
	{
		sair.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                janela.dispose();
                System.exit(0);
            }
        });
	}
}

//manupulação dos botões Jogar/Carregar Jogo/Sair
class ManipulaMenu
{

}

/*Fiz como main para testar
 *ou isso precisa ir para o código principal, ou vai ser uma classe da visão
 *(acho que ser da visão é mais adequado, mas ainda estou na dúvida)*/
public class Menu
{

	public static void main (String args[])
	{
		JFrame janela = new JFrame("Banco Curitibano");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(1000, 1000);

		//cria os botões
        Jogar jogar = new Jogar();
        CarregarJogo carregarJogo = new CarregarJogo();
        Sair sair = new Sair();
		
		String caminho = "logo2.png";

		//adiciona o painel com a imagem à janela
		TelaInicial painel = new TelaInicial(caminho);
		janela.add(painel, BorderLayout.CENTER);

		//cria um painel para acomodar os botões
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		painelBotoes.setBackground(new Color (255, 228, 225));

		//adiciona os botões ao painel
		painelBotoes.add(jogar);
		painelBotoes.add(carregarJogo);
		painelBotoes.add(sair);

		//adiciona o painel à parte inferior da janela
		janela.add(painelBotoes, BorderLayout.SOUTH);
		janela.revalidate();
		janela.repaint();

		//ações dos botões
		jogar.BotaoJogar(janela);
		carregarJogo.BotaoCarregarJogo();
		sair.BotaoSair(janela);

		//torna visível
		janela.setVisible(true);
	}
}
