package visao;

import controlador.*;
import modelo.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.*;
import java.io.File;
import java.awt.*;
import java.awt.Dimension;


//criar e colocar o frame na tela
class TelaInicial extends JPanel
{
	private BufferedImage logo;

	public TelaInicial(String caminho) {
		try {
			logo = ImageIO.read(new File(caminho));
			repaint();
		} catch (IOException e) {
			System.out.printf("Erro ao carregar imagem %s\n", e.getMessage());
		}

		setBackground(new Color(255, 228, 225));
	}
		
	//Sobrescreve o método paintComponent para desenhar a imagem
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (logo != null) {
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
	JPanel painelTexto = new JPanel();

	public Jogar() {
		jogar.setFont(new Font("Times New Roman", Font.BOLD, 25));
        jogar.setBackground(new Color(255,192,203));
        jogar.setForeground(new Color(250,128,114));

		add(jogar);
	}


	public void BotaoJogar(Tabuleiro tabuleiro)
	{
		jogar.addActionListener(new ActionListener(){
			// ação do botao jogar
            @Override
            public void actionPerformed(ActionEvent e) {
				//limpa a tela
				tabuleiro.janela.getContentPane().removeAll();
				// muda estado do jogo para registro dos jogadores
				tabuleiro.jogo.setEstado(Estados.MENU_REGISTRO_JOGADORES);
				tabuleiro.atualizaTabuleiro();
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

	public void BotaoSair (Tabuleiro tabuleiro)
	{
		sair.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tabuleiro.janela.dispose();
                System.exit(0);
            }
        });
	}
}


public class MenuInicial {

	public void exibeMenu(Tabuleiro tabuleiro) {

		//cria os botões
        Jogar jogar = new Jogar();
        CarregarJogo carregarJogo = new CarregarJogo();
        Sair sair = new Sair();
		
		String caminho = "src/assets/logoBC.png";
/*
		//adiciona o painel com a imagem à janela
		TelaInicial painel = new TelaInicial(caminho);
		tabuleiro.janela.add(painel, BorderLayout.CENTER);
*/
		// carrega imagem da logo e redimensiona o seu tamanho
		// talvez criar alguma classe com um metodo que faça isso
		ImageIcon logo = new ImageIcon("src/assets/logoBC.png");
		Image image = logo.getImage();
		int scale = tabuleiro.escala * 50;
		Image logoRes = image.getScaledInstance(scale, scale, Image.SCALE_SMOOTH);
		logo = new ImageIcon(logoRes);
		// adiciona a logo no menu usando uma label
		JLabel imagem = new JLabel();
		imagem.setIcon(logo);
		imagem.setVerticalAlignment(JLabel.CENTER);
		imagem.setHorizontalAlignment(JLabel.CENTER);
		tabuleiro.janela.add(imagem);

		//cria um painel para acomodar os botões
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		painelBotoes.setBackground(new Color (255, 228, 225));

		//adiciona os botões ao painel
		painelBotoes.add(jogar);
		painelBotoes.add(carregarJogo);
		painelBotoes.add(sair);

		//adiciona o painel à parte inferior da janela
		tabuleiro.janela.add(painelBotoes, BorderLayout.SOUTH);
		tabuleiro.janela.revalidate();
		tabuleiro.janela.repaint();

		//ações dos botões
		jogar.BotaoJogar(tabuleiro);
		carregarJogo.BotaoCarregarJogo();
		sair.BotaoSair(tabuleiro);

		//torna visível
		tabuleiro.janela.setVisible(true);
	}
}
