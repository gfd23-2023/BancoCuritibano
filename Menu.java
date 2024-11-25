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

	public Jogar()
	{
		jogar.setFont(new Font("Times New Roman", Font.BOLD, 25));
        jogar.setBackground(new Color(255,192,203));
        jogar.setForeground(new Color(250,128,114));

		add(jogar);
	}

	public void BotaoJogar()
	{
		jogar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Dar um jeito de concectar esse botão com o código principal
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
 *(acho que ser da visão é mais adequado, não pensei muito sobre ainda)*/
public class Menu
{
	public static void main (String args[])
	{
		JFrame janela = new JFrame("Banco Curitibano");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(1000, 1000);
		
		String caminho = "logo2.png";

		//adiciona o painel com a imagem à janela
		TelaInicial painel = new TelaInicial(caminho);
		janela.add(painel, BorderLayout.CENTER);

		//cria os botões
		Jogar jogar = new Jogar();
		CarregarJogo carregarJogo = new CarregarJogo();
		Sair sair = new Sair();

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
		jogar.BotaoJogar();
		carregarJogo.BotaoCarregarJogo();
		sair.BotaoSair(janela);

		//torna visível
		janela.setVisible(true);
	}
}
