package visao;

import controlador.*;
import modelo.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;
import java.io.File;
import java.awt.*;
import java.util.*;

// CLASSE DESENHA
// responsável por desenhar/exibir informações específicas de classes modelo

public class Desenha {

	public static void desenhaCasas(Display display) {
		// assumindo que a quantidade de casas deve ser múplipla de 4
		// calcular qual deve ser o tamanho das casas e suas posições
		int quant = display.jogo.casas.size() / 4; // quantidade por lado
		int tam = (display.Y_SCREEN * 8/10) / quant;

		// desenha casas superiores
		int j = 0;
		for (int i = 0; i < quant; i++) {
			desenhaBloco(j*tam, 0, display.jogo.casas.get(i).getNome(), tam, display);
			j++;
		}
		// desenha casas da lateral direita
		j = 0;
		for (int i = quant; i < 2*quant; i++) {
			desenhaBloco(quant*tam, j*tam, display.jogo.casas.get(i).getNome(), tam, display);
			j++;
		}
		// desenha casas inferiores
		j = quant;
		for (int i = 2*quant; i < 3*quant; i++) {
			desenhaBloco(j*tam, quant*tam, display.jogo.casas.get(i).getNome(), tam, display);
			j--;
		}
		// desenha casas da lateral esquerda
		j = quant;
		for (int i = 3*quant; i < 4*quant; i++) {
			desenhaBloco(0, j*tam, display.jogo.casas.get(i).getNome(), tam, display);
			j--;
		}
	}


    private static void desenhaBloco(int x, int y, String nome, int tam, Display display) {
		
		// cria uma label/retangulo com o nome da casa
		JLabel bloco = new JLabel(nome);
		// centraliza o texto
		bloco.setHorizontalTextPosition(JLabel.CENTER);
		bloco.setVerticalTextPosition(JLabel.CENTER);
		bloco.setHorizontalAlignment(JLabel.CENTER);
		bloco.setVerticalAlignment(JLabel.CENTER);

		// define a cor da casa
		Color cor1 = new Color(245, 54, 102);
		Color cor2 = new Color(255,212,219);

		bloco.setForeground(cor1);
		bloco.setFont(new Font("CourierNew", Font.BOLD, tam/6));
		bloco.setBounds(x, y, tam, tam);

		Border borda = new LineBorder(cor1, 3);
		bloco.setBorder(borda);
		bloco.setBackground(cor2);
		bloco.setOpaque(true);

		display.janela.add(bloco);
    }


	public static void desenhaRodada(Display display) {

		display.janela.setLayout(null);
		// painel da rodada e jogador da vez
		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(2,1)); // duas linhas, uma coluna

		// cria labels para guardar o texto
		JLabel rodada = new JLabel();
		JLabel jogador = new JLabel();

		// adiciona textos
		rodada.setText(String.format("Rodada: %d", display.jogo.getRodada()));
		// nome do jogador da vez
		String nome = display.jogo.jogadores.get(display.jogo.getJogada()).getNome();
		jogador.setText(String.format("Jogador: %s", nome));

		// centraliza o texto
		rodada.setHorizontalTextPosition(JLabel.CENTER);
		rodada.setVerticalTextPosition(JLabel.TOP);
		rodada.setHorizontalAlignment(JLabel.CENTER);
		rodada.setVerticalAlignment(JLabel.TOP);
		jogador.setHorizontalTextPosition(JLabel.CENTER);
		jogador.setVerticalTextPosition(JLabel.BOTTOM);
		jogador.setHorizontalAlignment(JLabel.CENTER);
		jogador.setVerticalAlignment(JLabel.BOTTOM);
		
		// define as cores
		Color cor1 = new Color(245, 54, 102);
		Color cor2 = new Color(255,212,219);

		// seta cores dos textos
		rodada.setForeground(cor1);
		rodada.setFont(new Font("CourierNew", Font.BOLD, 20));
		jogador.setForeground(cor1);
		jogador.setFont(new Font("CourierNew", Font.BOLD, 20));

		int pos = display.X_SCREEN*3/5;
		int tam = display.Y_SCREEN/20;
		painel.setBounds(pos, tam, 8*tam, 2*tam);

		Border ajuste = new EmptyBorder(10, 10, 10, 10);
		rodada.setBorder(ajuste);
		jogador.setBorder(ajuste);
		painel.setBackground(cor2);

		painel.add(rodada);
		painel.add(jogador);

		display.janela.add(painel);
		display.janela.revalidate();
	}

	public static void desenhaDados(Display display) {

		// guarda caminho da imagem do dado atual
		String caminho1 = String.format("src/assets/dado%d.png", display.jogo.dado1.getValor());
		String caminho2 = String.format("src/assets/dado%d.png", display.jogo.dado2.getValor());

		// carrega imagem e redimensiona o seu tamanho
		ImageIcon imgDado1 = new ImageIcon(caminho1);
		Image image = imgDado1.getImage();
		int tam = display.Y_SCREEN/10;
		Image imgDadoRes = image.getScaledInstance(tam, tam, Image.SCALE_SMOOTH);
		imgDado1 = new ImageIcon(imgDadoRes);

		ImageIcon imgDado2 = new ImageIcon(caminho2);
		image = imgDado2.getImage();
		imgDadoRes = image.getScaledInstance(tam, tam, Image.SCALE_SMOOTH);
		imgDado2 = new ImageIcon(imgDadoRes);

		JLabel dado1 = new JLabel(imgDado1);
		JLabel dado2 = new JLabel(imgDado2);

		Border ajuste = new EmptyBorder(10, 10, 10, 10);
		dado1.setBorder(ajuste);
		dado2.setBorder(ajuste);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(1,2));

		painel.setBounds(2*tam, 2*tam, 4*tam, 2*tam);

		painel.add(dado1);
		painel.add(dado2);

		display.janela.add(painel);
		display.janela.revalidate();
	}


	
	public static void desenhaInfoJogadores(Display display) {
		// seta tamanhos proporcionais
		int x = display.X_SCREEN *3/5;
		int y = display.Y_SCREEN *1/5;
		int tam = display.Y_SCREEN/20;

		int quant = display.jogo.jogadores.size();
		for (int i = 0; i < quant; i++) {
			JPanel painel = criaPainelJogador(display, display.jogo.jogadores.get(i));
			painel.setBounds(x, y+(i*2*tam), 4*tam, 2*tam);
			display.janela.add(painel);
		}
		
		display.janela.revalidate();
		display.janela.repaint();
	}

	private static JPanel criaPainelJogador(Display display, Jogador jogador) {

		// cria campos para infos
		JLabel nome = new JLabel(jogador.getNome());
		JLabel dinheiro = new JLabel(String.format("Dinheiro: %d", jogador.getDinheiro()));
		JLabel estado = new JLabel();

		// escolhe cor com base nos ids
		Color cor1 = new Color(0,0,0);
		Color cor2 = new Color(0,0,0);

		switch (jogador.getId()) {
			case 0: // vermelho
				cor1 = new Color(219, 68, 68);
				cor2 = new Color(235, 176, 176);
				break;
			case 1: // laranja
				cor1 = new Color(230, 145, 67);
				cor2 = new Color(245, 207, 171);
				break;
			case 2: // amarelo
				cor1 = new Color(224, 197, 58);
				cor2 = new Color(255, 244, 189);
				break;
			case 3: // verde
				cor1 = new Color(72, 214, 54);
				cor2 = new Color(164, 232, 155);
				break;
			case 4: // azul
				cor1 = new Color(38, 181, 189);
				cor2 = new Color(148, 223, 227);
				break;
			case 5: // roxo
				cor1 = new Color(74, 54, 163);
				cor2 = new Color(164, 150, 227);
				break;
		}

		// seta fontes e cores
		nome.setForeground(cor1);
		nome.setFont(new Font("CourierNew", Font.BOLD, 20));
		dinheiro.setForeground(cor1);
		dinheiro.setFont(new Font("CourierNew", Font.BOLD, 15));
		estado.setForeground(cor2);
		estado.setFont(new Font("CourierNew", Font.BOLD, 15));
		estado.setOpaque(true);

		if (jogador.getId() == display.jogo.getJogada()) {
			estado.setText("JOGANDO");
			estado.setBackground(cor1);
		}
		else if (jogador.estaFalido()) {
			estado.setText("FALIDO");
			estado.setBackground(cor1);
		}
		else {
			estado.setText("");
			estado.setBackground(cor2);
		}

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(3,1)); // 3 linhas, 1 coluna
		
		painel.add(nome);
		painel.add(dinheiro);
		painel.add(estado);

		painel.setBackground(cor2);
		Border borda = new LineBorder(cor1, 5);
		painel.setBorder(borda);

		return painel;
	}

	public static void desenhaJogadores(Display display) {
		// calcula disposição das casas no tabuleiro
		int quant = display.jogo.casas.size() / 4; // quantidade por lado
		int tam = (display.Y_SCREEN * 8/10) / quant; // tamanho das casas
													 //
		
		int num = display.jogo.jogadores.size();
		for (int i = 0; i < num; i++) {
			JPanel painel = posicaoJogador(display, display.jogo.jogadores.get(i));
			int casa = display.jogo.jogadores.get(i).getCasa();
			// ajusta tamanho e posicao no tabuleiro
			if (casa < quant) 
				painel.setBounds(casa*tam, 0, tam, tam);
			else if (casa < 2*quant)
				painel.setBounds((quant%casa)*tam, casa*tam, tam, tam);
			else if (casa < 3*quant)
				painel.setBounds((quant - quant%casa)*tam, quant*tam, tam, tam);
			else
				painel.setBounds(0, (quant - quant%casa)*tam, tam, tam);


			painel.setBackground(Color.black);
			display.janela.add(painel);
		}

		display.janela.revalidate();
		display.janela.repaint();
	}

	private static JPanel posicaoJogador(Display display, Jogador jogador) {

		// painel da posição do jogador
		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(3,3));
		GridBagConstraints pos = new GridBagConstraints(); // posicao do grid
		pos.gridx = 0;	//coluna
		pos.gridy = 0;	//linha
		// quadradinho jogador
		JLabel quad = new JLabel();
		Color cor = new Color(0,0,0);

		switch (jogador.getId()) {
			case 0: // vermelho
				cor = new Color(219, 68, 68);
				pos.gridx = 0;
				pos.gridy = 0;
				painel.add(quad, pos);
				break;
			case 1: // laranja
				cor = new Color(230, 145, 67);
				pos.gridx = 1;
				pos.gridy = 0;
				painel.add(quad, pos);
				break;
			case 2: // amarelo
				cor = new Color(224, 197, 58);
				pos.gridx = 2;
				pos.gridy = 0;
				painel.add(quad, pos);
				break;
			case 3: // verde
				cor = new Color(72, 214, 54);
				pos.gridx = 0;
				pos.gridy = 2;
				painel.add(quad, pos);
				break;
			case 4: // azul
				cor = new Color(38, 181, 189);
				pos.gridx = 1;
				pos.gridy = 2;
				painel.add(quad, pos);
				break;
			case 5: // roxo
				cor = new Color(74, 54, 163);
				pos.gridx = 2;
				pos.gridy = 2;
				painel.add(quad, pos);
				break;
		}

		quad.setBackground(cor);
		quad.setOpaque(true);

		return painel;
	}

}
