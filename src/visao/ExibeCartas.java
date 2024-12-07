/*Layout e exibição das cartas*/

package visao;

import controlador.*;
import modelo.cartas.*;
import modelo.*;

//Imports para desenhar o retângulo da carta
/*----------------------------------------*/
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics2D;
/*----------------------------------------*/

//Imports para manipular a imagem
/*----------------------------------------*/
import java.io.File;
import java.awt.Font;
import java.awt.Composite;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;
/*----------------------------------------*/

//Import para o texto
/*----------------------------------------*/
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
/*----------------------------------------*/

public class ExibeCartas extends JPanel
{
	private BufferedImage logoFundo;				//imagem que vai ficar no fundo da carta
	String mensagem;								//mensagem da carta

	public void exibeCartas(Display display, Carta carta)
	{
		/*cor do painel ATUAL (talvez isso precise mudar)*/
        setBackground(new Color(255, 228, 225));

		//carrega a imagem
		try {
			logoFundo = ImageIO.read(new File("src/assets/logoBC.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//pega a mesagem da carta
		mensagem = carta.getDescricao();

		//adiciona o painel ATUAL à janela
		display.janela.getContentPane().add(this);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		//captura as dimensões do painel
        int larguraPainel = getWidth();
        int alturaPainel = getHeight();

        //dimensões da carta
        int larguraCarta = getWidth()/7;
        int alturaCarta = getHeight()/3;
        int arcoLargura = 30;               //raio horizontal dos cantos arredondados
        int arcoAltura = 30;                //raio vertical dos cantos arredondados

        //calcula posição do centro do painel
        int posX = (larguraPainel - larguraCarta)/2;
        int posY = (alturaPainel - alturaCarta)/2;

		//desenha o retângulo da carta
		/*----------------------------------------------------------------------------------*/
		//converte Graphics para Graphics2D
		Graphics2D g2d = (Graphics2D) g;

		//cor do preenchimento da carta
		g2d.setColor(new Color(175,238,238));		//um tom de azul (se nn ficar bom colocar 230,230,250 lavanda)
	

		//desenha a carta preenchida de azul
		g2d.fillRoundRect(posX, posY, larguraCarta, alturaCarta, arcoLargura, arcoAltura);

		//ajusta a borda
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(posX, posY, larguraCarta, alturaCarta, arcoLargura, arcoAltura);
		/*----------------------------------------------------------------------------------*/

		//manipula a imagem do fundo
		/*----------------------------------------------------------------------------------*/
		if (logoFundo != null)
		{
			int larguraLogo = logoFundo.getWidth()/2 - 50;
			int alturaLogo = logoFundo.getHeight()/2 - 50;

			int coordX = (larguraPainel - larguraLogo)/2;
			int coordY = (alturaPainel - alturaLogo)/2;

			Composite originalComposite = g2d.getComposite();
			float opacidade = 0.1f;							//0.0 transparente, 1.0 opaco
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidade));
			g2d.drawImage(logoFundo, coordX, coordY, larguraLogo, alturaLogo, this);
			g2d.setComposite(originalComposite);			//restaura o composite original
		}
		/*----------------------------------------------------------------------------------*/

		// Ajusta dinamicamente o texto para ficar centralizado
		FontMetrics fm = g2d.getFontMetrics();
		ArrayList<String> linhas = new ArrayList<>(); // Lista que armazenará as linhas quebradas
		StringBuilder linhaAtual = new StringBuilder();
		int larguraMaxima = larguraCarta - 30;

		for (String palavra : mensagem.split(" ")) 
		{
			// Cria uma linha temporária juntando a linha atual com a próxima palavra
			String linhaTemp = linhaAtual.toString() + (linhaAtual.length() > 0 ? " " : "") + palavra;

			// Verifica se a largura da linha temporária excede o limite
			if (fm.stringWidth(linhaTemp) > larguraMaxima) {
			// Adiciona a linha atual à lista
			linhas.add(linhaAtual.toString());
			// Começa uma nova linha com a palavra atual
			linhaAtual = new StringBuilder(palavra);
			} 
			else 
			{
				// Adiciona a palavra à linha atual
				if (linhaAtual.length() > 0) {
					linhaAtual.append(" ");
				}
				linhaAtual.append(palavra);
			}
		}

		// Adiciona a última linha, se existir
		if (linhaAtual.length() > 0) {
			linhas.add(linhaAtual.toString());
		}

		// Centraliza e desenha as linhas na carta
		int linhaAltura = fm.getHeight();
		int startY = posY + (alturaCarta - (linhas.size() * linhaAltura)) / 2;

		for (int i = 0; i < linhas.size(); i++) 
		{
			int linhaLargura = fm.stringWidth(linhas.get(i));
			int linhaX = posX + (larguraCarta - linhaLargura) / 2;
			int linhaY = startY + (i * linhaAltura);
			g2d.drawString(linhas.get(i), linhaX, linhaY);
		}
	}

	//passar a casa como parâmetro e usar o texto e descrição dela
	public void desenhaCarta(Display display)
	{
		display.janela.revalidate();
		display.janela.repaint();
	}
}
