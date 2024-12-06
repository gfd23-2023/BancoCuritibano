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
import java.util.*;

// CLASSE DESENHA
// responsável por desenhar/exibir informações específicas de classes modelo

public class Desenha {

	public static void desenhaCasas(Display display, Graphics2D g) {
		// assumindo que a quantidade de casas deve ser múplipla de 4
		// calcular qual deve ser o tamanho das casas e suas posições
		int quant = display.jogo.casas.size() / 4; // quantidade por lado
		int tam = display.Y_SCREEN / quant;

		// desenha casas superiores
		int j = 0;
		for (int i = 0; i < quant; i++) {
			desenhaBloco(j*tam, 0, display.jogo.casas.get(i).getNome(), tam, g);
			j++;
		}
		// desenha casas da lateral direita
		j = 0;
		for (int i = quant; i < 2*quant; i++) {
			desenhaBloco(quant*tam, j*tam, display.jogo.casas.get(i).getNome(), tam, g);
			j++;
		}
		// desenha casas inferiores
		j = quant;
		for (int i = 2*quant; i < 3*quant; i++) {
			desenhaBloco(j*tam, quant*tam, display.jogo.casas.get(i).getNome(), tam, g);
			j--;
		}
		// desenha casas da lateral esquerda
		j = quant;
		for (int i = 3*quant; i < 4*quant; i++) {
			desenhaBloco(0, j*tam, display.jogo.casas.get(i).getNome(), tam, g);
			j--;
		}
	}


    private static void desenhaBloco(int x, int y, String nome, int tam, Graphics2D g) {

        // Desenhar o bloco
        g.setColor(Color.pink.brighter()); // Define a cor do bloco
        g.fillRect(x, y, tam, tam); // Preenche o bloco com a cor branca

        // Desenhar a borda do bloco
        g.setColor(Color.pink.darker()); // Cor da borda
        g.drawRect(x, y, tam, tam); // Desenha a borda do bloco

        // Configurar a cor e a fonte para o texto
        g.setColor(Color.pink.darker()); // Cor do texto
        g.setFont(new Font("Courier New", Font.BOLD, tam/5)); // Define a fonte para o texto

        FontMetrics fm = g.getFontMetrics(); // Obter métricas da fonte para calcular o espaço do texto
        int textHeight = fm.getAscent(); // Altura do texto para centralizar

        // Dividir o texto em linhas, considerando o limite de largura do bloco
        int maxTextWidth = tam - 10;  // Ajuste de margem para não encostar nas bordas
        String[] words = nome.split(" "); // Dividir o nome do lugar em palavras
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            // Verificar se a próxima palavra cabe na linha atual
            String testLine = line.length() == 0 ? word : line + " " + word;
            int lineWidth = fm.stringWidth(testLine); // Calcular a largura da linha testada

            if (lineWidth <= maxTextWidth) {
                line.append(line.length() == 0 ? word : " " + word); // Adiciona palavra à linha
            } else {
                lines.add(line.toString()); // Adiciona a linha cheia ao array
                line = new StringBuilder(word); // Começa uma nova linha com a palavra que não coube
            }
        }
        // Adicionar a última linha
        lines.add(line.toString()); // Adiciona a última linha ao array

        // Calcular posição para centralizar o texto dentro do bloco
        int totalTextHeight = lines.size() * textHeight; // Altura total do texto
        int startY = y + (tam - totalTextHeight) / 2 + textHeight; // Calcula a posição Y para centralizar

        // Desenhar as linhas de texto no bloco
        for (String lineText : lines) {
            int textWidth = fm.stringWidth(lineText); // Largura de cada linha de texto
            int textX = x + (tam - textWidth) / 2; // Calcula a posição X para centralizar
            g.drawString(lineText, textX, startY); // Desenha o texto no bloco
            startY += textHeight; // Ajusta a posição Y para a próxima linha de texto
        }

    }
}
