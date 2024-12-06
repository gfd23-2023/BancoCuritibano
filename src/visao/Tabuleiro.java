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

public class Tabuleiro extends JPanel {
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Display display = Display.getInstancia();
		Graphics2D g2 = (Graphics2D) g;
		Desenha.desenhaCasas(display, g2);
	}

	public void exibeTabuleiro(Display display) {
		display.janela.repaint();
	}



}
