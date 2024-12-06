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

	public void exibeTabuleiro(Display display) {
		
		Desenha.desenhaCasas(display);
		Desenha.desenhaRodada(display);
		Desenha.desenhaDados(display);
		display.janela.repaint();
	}




}
