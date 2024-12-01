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

public class Tabuleiro {
	
	public Jogo jogo; // cria novo jogo
	public Janela janela;	
	
	// definicoes da tela
	final int escala = 8;
	final int X_SCREEN = 100 * escala;
	final int Y_SCREEN = 100 * escala;

	// construtor
	public Tabuleiro() {
		this.jogo = jogo.getInstancia(); // cria jogo
		this.janela = new Janela(X_SCREEN, Y_SCREEN);
	}
}

// frame/janela do jogo
// contem as definicoes padrao da janela
class Janela extends JFrame {

	public Janela(int X_SCREEN, int Y_SCREEN) {
		// definicoes da janela
		this.setTitle("Banco Curitibano");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(X_SCREEN, Y_SCREEN);
		this.setVisible(true);
		// cria icone com a logo do jogo
		ImageIcon logo = new ImageIcon("../assets/logoBC.png");
		this.setIconImage(logo.getImage()); 
		this.setBackground(new Color(255, 228, 225));
	}
}
