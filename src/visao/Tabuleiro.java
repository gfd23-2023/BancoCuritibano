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

// CLASSE TABULEIRO 
// responsável por exibir o tabuleiro 
// relacionada com a exibição dos elementos que compõem um estado do jogo
// tem métodos de exibição para cada informação do jogo
// chama métodos de outras classes auxiliares da visão

public class Tabuleiro {

	// jogando dados
	public void exibeTabuleiro(Display display) {
		// desenha infos do tabuleiro
		Desenha.desenhaCarta(display, false);
		Desenha.desenhaJogadores(display);
		Desenha.desenhaCasas(display);
		DesenhaInfo.desenhaInfoRodada(display);
		Desenha.desenhaDados(display);
		DesenhaInfo.desenhaInfoJogadores(display);
		display.janela.repaint();
	}

	public void movimentoTabuleiro(Display display) {

		exibeTabuleiro(display);
		// numero de casas que vai andar
		int num = display.jogo.valorDados();
		Timer timer = new Timer(400, new ActionListener() {
			int cont = 0; // contagem de quantas casas andou
			@Override
			public void actionPerformed(ActionEvent e) {
				cont++;
				display.jogo.movimentaJogador();
				display.janela.getContentPane().removeAll(); // limpa tela
				exibeTabuleiro(display); // atualiza tabuleiro
				if (cont == num) {
					((Timer) e.getSource()).stop();
					display.jogo.setEstado(Estados.JOGAR_PROXIMO);
					display.atualizaDisplay();
				}
			}
		});

		timer.start();
	}

	public void exibeCartaTabuleiro(Display display) {
		Desenha.desenhaCarta(display, true);
		display.jogo.retiraCarta();
		display.janela.repaint();
	}


}
