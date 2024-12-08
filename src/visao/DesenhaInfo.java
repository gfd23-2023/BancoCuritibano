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

// CLASSE DESENHA PAINEIS DE INFORMAÇÃO
public class DesenhaInfo {

	// desenha painel com as informações da rodada 
	// exibe numero da rodada e jogador da vez
	public static void desenhaInfoRodada(Display display) {

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
		jogador.setHorizontalTextPosition(JLabel.CENTER);
		jogador.setVerticalTextPosition(JLabel.BOTTOM);
		
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

	// desenha painel com as informações de todos os jogadores
	// posiciona do canto direito do display como uma tabela 3x2
	// cada celula contem info sobre nome, dinheiro e estado do jogador
	public static void desenhaInfoJogadores(Display display) {
		// seta tamanhos proporcionais
		int x = display.X_SCREEN *3/5;
		int y = display.Y_SCREEN *1/5;
		int tam = display.Y_SCREEN/20;

		// cria paineis de todos os jogadores e posiciona lado a lado (tabela)
		int quant = display.jogo.jogadores.size();
		for (int i = 0; i < quant; i++) {
			JPanel painel = criaPainelJogador(display, display.jogo.jogadores.get(i));
			if (i < 3)
				painel.setBounds(x, y+(2*i*tam), 4*tam, 2*tam);
			else
				painel.setBounds(x+4*tam, y+(2*(i-3)*tam), 4*tam, 2*tam);

			display.janela.add(painel);
		}
		
		display.janela.revalidate();
		display.janela.repaint();
	}

	// cria um painel de um jogador em especifico
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

		// configura texto do estado
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
/*
	public static void desenhaInfoPropriedade(Display display, Casa casa) {
		// exibir nome
		// valor compra
		// valor aluguel
		// valor das contrucoes
	}
*/
	


	
}
