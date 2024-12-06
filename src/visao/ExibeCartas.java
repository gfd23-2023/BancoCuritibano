/*Layout e exibição das cartas*/

package visao;

import controlador.*;
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

public class ExibeCartas extends JPanel
{
	private BufferedImage logoFundo;				//imagem que vai ficar no fundo da carta
	//talvez colocar o texto aqui, não sei

	public void exibeCartas(Tabuleiro tabuleiro)
	{
		/*cor do painel ATUAL (talvez isso precise mudar)*/
        setBackground(new Color(255, 228, 225));

		//carrega a imagem
		try {
			logoFundo = ImageIO.read(new File("src/assets/logoBC.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//adiciona o painel ATUAL à janela
		tabuleiro.janela.getContentPane().add(this);
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
			float opacidade = 0.2f;							//0.0 transparente, 1.0 opaco
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidade));
			g2d.drawImage(logoFundo, coordX, coordY, larguraLogo, alturaLogo, this);
			g2d.setComposite(originalComposite);			//restaura o composite original
		}
		/*----------------------------------------------------------------------------------*/
	}

	public void desenhaCarta(Tabuleiro tabuleiro)
	{
		tabuleiro.janela.revalidate();
		tabuleiro.janela.repaint();
	}
}
