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


public class MenuInicial {

	public void exibeMenu(Display display) {

		//cria os botões
        Jogar jogar = new Jogar();
        CarregarJogo carregarJogo = new CarregarJogo();
        Sair sair = new Sair();
		
		// carrega imagem da logo e redimensiona o seu tamanho
		ImageIcon logo = new ImageIcon("src/assets/logoBC.png");
		Image image = logo.getImage();
		int scale = display.escala * 100;
		Image logoRes = image.getScaledInstance(scale, scale + 100, Image.SCALE_SMOOTH);
		logo = new ImageIcon(logoRes);
		// adiciona a logo no menu usando uma label
		JLabel imagem = new JLabel();
		imagem.setIcon(logo);
		imagem.setVerticalAlignment(JLabel.CENTER);
		imagem.setHorizontalAlignment(JLabel.CENTER);
		imagem.setBackground(new Color (255, 228, 225));
		imagem.setOpaque(true);
		display.janela.add(imagem);

		//cria um painel para acomodar os botões
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		painelBotoes.setBackground(new Color (255, 228, 225));

		//adiciona os botões ao painel
		painelBotoes.add(jogar);
		painelBotoes.add(carregarJogo);
		painelBotoes.add(sair);

		//adiciona o painel à parte inferior da janela
		display.janela.add(painelBotoes, BorderLayout.SOUTH);
		display.janela.revalidate();
		display.janela.repaint();

		//ações dos botões
		jogar.BotaoJogar(display);
		carregarJogo.BotaoCarregarJogo();
		sair.BotaoSair(display);

		//torna visível
		display.janela.setVisible(true);
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


	public void BotaoJogar(Display display)
	{
		jogar.addActionListener(new ActionListener(){
			// ação do botao jogar
            @Override
            public void actionPerformed(ActionEvent e) {
				//limpa a tela
				display.janela.getContentPane().removeAll();
				// muda estado do jogo para registro dos jogadores
				display.jogo.setEstado(Estados.MENU_REGISTRO_JOGADORES);
				display.atualizaDisplay();
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

	public void BotaoSair (Display display)
	{
		sair.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                display.janela.dispose();
                System.exit(0);
            }
        });
	}
}


