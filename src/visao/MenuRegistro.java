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

public class MenuRegistro {
	
	JLabel texto = new JLabel(String.format("Digite seu nome!"));
	JPanel painelTexto = new JPanel();
	//campos para escrever os nomes dos jogadores	
	private JTextField jogador1 = new JTextField(20);
	private JTextField jogador2 = new JTextField(20);
	private JTextField jogador3 = new JTextField(20);
	private JTextField jogador4 = new JTextField(20);
	private JTextField jogador5 = new JTextField(20);
	private JTextField jogador6 = new JTextField(20);

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

	public void exibeRegistro(Tabuleiro tabuleiro) {

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
		tabuleiro.janela.getContentPane().add(painelTexto);

		//atualiza a tela
		tabuleiro.janela.revalidate();
		tabuleiro.janela.repaint();
	}
}
