package visao;
import controlador.*;
import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

// CLASSE DISPLAY
// responsável por trocar a exibição da tela com base no estado do jogo
// contem as configurações padrões da janela/display
public class Display implements Serializable {
	
	public Jogo jogo; // contem a instancia do jogo
	public Janela janela; // Jframe 
	
	// definicoes da tela
	final int escala = 6; // altere o valor da escala como quiser
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	final int X_SCREEN = gd.getDisplayMode().getWidth();		//obtem a largura do display DISPONÍVEL 
	final int Y_SCREEN = gd.getDisplayMode().getHeight();

	// construtor
	private Display() {
		this.jogo = Jogo.getInstancia(); // cria jogo
		this.janela = new Janela(X_SCREEN, Y_SCREEN);
	}

	// display é singleton
	private static Display instanciaUnica;
	// retorna a unica instancia do display (cria uma se nao existir)
	public static synchronized Display getInstancia() {
		if (instanciaUnica == null) 
			instanciaUnica = new Display();
		return instanciaUnica;
	}
	
	MenuInicial menu = new MenuInicial();
	MenuRegistro registro = new MenuRegistro();
	

	// dependendo do estado do jogo, exibe o menu correspondente
	public void atualizaDisplay() {
		switch (jogo.getEstado()) {

			case MENU_INICIAL:
				menu.exibeMenu(this);
				break;
			case MENU_REGISTRO_JOGADORES:
				registro.exibeRegistro(this);
				break;
			case JOGAR_DADOS:
				BotoesJogo.exibeJogarDados(this);
				BotoesJogo.exibeSalvarJogo(this);
				Tabuleiro.exibeTabuleiro(this);
				break;
			case JOGAR_MOVIMENTO:
				Tabuleiro.movimentoTabuleiro(this);
				break;
			case JOGAR_PROXIMO:
				BotoesJogo.exibeProximo(this);
				BotoesJogo.exibeSalvarJogo(this);
				Tabuleiro.exibeTabuleiro(this);
				break;
			case JOGAR_CASA:
				jogo.analisaCasa();
				atualizaDisplay();
				break;
			case JOGAR_PROPRIEDADE:
				DesenhaInfo.desenhaInfoPropriedade(this);
				BotoesJogo.exibeComprarOuProximo(this);
				BotoesJogo.exibeSalvarJogo(this);
				Tabuleiro.exibeTabuleiro(this);
				break;
			case JOGAR_ALUGUEL:
				DesenhaInfo.desenhaInfoPropriedade(this);
				BotoesJogo.exibePagarAluguel(this);
				BotoesJogo.exibeSalvarJogo(this);
				Tabuleiro.exibeTabuleiro(this);
				break;
			case JOGAR_CONSTRUIR:
				DesenhaInfo.desenhaInfoPropriedade(this);
				BotoesJogo.exibeConstruir(this);
				BotoesJogo.exibeSalvarJogo(this);
				Tabuleiro.exibeTabuleiro(this);
				break;
			case JOGAR_IMPOSTO:
				DesenhaInfo.desenhaInfoImposto(this);
				BotoesJogo.exibePagarImposto(this);
				BotoesJogo.exibeSalvarJogo(this);
				Tabuleiro.exibeTabuleiro(this);
				break;
			case JOGAR_CADEIA:
				BotoesJogo.exibeCadeia(this);
				BotoesJogo.exibeSalvarJogo(this);
				Tabuleiro.exibeTabuleiro(this);
				break;
			case JOGAR_CARTA:
				BotoesJogo.exibeRetirarCarta(this);
				BotoesJogo.exibeSalvarJogo(this);
				Tabuleiro.exibeTabuleiro(this);
				break;
			case JOGAR_CARTA_ACAO:
				Tabuleiro.exibeTabuleiro(this);
				BotoesJogo.exibeSalvarJogo(this);
				jogo.retiraCarta();
				atualizaDisplay();
				break;
			case JOGAR_CARTA_OPCAO:
				BotoesJogo.exibePagarOuCadeia(this);
				BotoesJogo.exibeSalvarJogo(this);
				Tabuleiro.exibeTabuleiro(this);
				break;
			case JOGAR_SALVAR:
				SalvarJogo.salvarJogo();
				break;
			case JOGAR_CARREGAR:
				SalvarJogo.carregarJogo(this);
				System.out.printf("estou no estado carregar\n");
				break;

		}
		System.out.println("estado atual: " + jogo.getEstado());
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
		// cria icone com a logo do jogo
		ImageIcon logo = new ImageIcon("../assets/logoBC.png");
		this.setIconImage(logo.getImage()); 
		this.setBackground(new Color(255, 228, 225));
		this.setVisible(true);
	}
}
