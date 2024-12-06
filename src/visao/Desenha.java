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

		// define a cor da casa
		Color cor1 = new Color(245, 54, 102);
		Color cor2 = new Color(255,212,219);

		bloco.setForeground(cor1);
		bloco.setFont(new Font("CourierNew", Font.BOLD, tam/8));
		bloco.setBounds(x, y, tam, tam);

		Border borda = new LineBorder(cor1, 3);
		//Border ajuste = new EmptyBorder(1, 1, 1, 1);
		//Border bordaComp = new CompoundBorder(ajuste, borda);
		//bloco.setBorder(bordaComp);
		bloco.setBorder(borda);
		bloco.setBackground(cor2);
		bloco.setOpaque(true);

		display.janela.add(bloco);
    }




}
