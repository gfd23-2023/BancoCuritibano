package visao;

import controlador.*;
import modelo.*;
import modelo.casas.*;
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
import java.io.Serializable;

// CLASSE DESENHA
// responsável por desenhar/exibir informações específicas de classes modelo

public class Desenha implements Serializable {

	public static void desenhaCasas(Display display) {
		// assumindo que a quantidade de casas deve ser múplipla de 4
		// calcular qual deve ser o tamanho das casas e suas posições
		int quant = display.jogo.casas.size() / 4; // quantidade por lado
		int tam = (display.Y_SCREEN * 8/10) / quant;
		int un = tam/3;

		String cor;
		// desenha casas superiores
		int j = 0;
		for (int i = 0; i < quant; i++) {
			cor = display.jogo.corCasa(i);
			desenhaProprietario(display, i, j*tam, tam, tam);
			desenhaBloco(j*tam, 0, display.jogo.casas.get(i).getNome(), cor, tam, display);
			j++;
		}
		// desenha casas da lateral direita
		j = 0;
		for (int i = quant; i < 2*quant; i++) {
			cor = display.jogo.corCasa(i);
			desenhaProprietario(display, i, quant*tam -tam, j*tam+un, tam);
			desenhaBloco(quant*tam, j*tam, display.jogo.casas.get(i).getNome(), cor, tam, display);
			j++;
		}
		// desenha casas inferiores
		j = quant;
		for (int i = 2*quant; i < 3*quant; i++) {
			cor = display.jogo.corCasa(i);
			desenhaProprietario(display, i, j*tam, quant*tam-un, tam);
			desenhaBloco(j*tam, quant*tam, display.jogo.casas.get(i).getNome(), cor, tam, display);
			j--;
		}
		// desenha casas da lateral esquerda
		j = quant;
		for (int i = 3*quant; i < 4*quant; i++) {
			cor = display.jogo.corCasa(i);
			desenhaProprietario(display, i, tam, j*tam+un, tam);
			desenhaBloco(0, j*tam, display.jogo.casas.get(i).getNome(), cor, tam, display);
			j--;
		}
	}


    private static void desenhaBloco(int x, int y, String nome, String cor, int tam, Display display) {
		// define a cor da casa
		Color cor1 = new Color(209, 71, 133);
		Color cor2 = new Color(255, 196, 223);
		switch (cor) {
			case "Vermelho": // vermelho
				cor1 = new Color(219, 68, 68);
				cor2 = new Color(235, 176, 176);
				break;
			case "Laranja": // laranja
				cor1 = new Color(230, 145, 67);
				cor2 = new Color(245, 207, 171);
				break;
			case "Amarelo": // amarelo
				cor1 = new Color(224, 197, 58);
				cor2 = new Color(255, 244, 189);
				break;
			case "Verde": // verde
				cor1 = new Color(72, 214, 54);
				cor2 = new Color(197, 250, 190);
				break;
			case "Azul": // azul
				cor1 = new Color(38, 181, 189);
				cor2 = new Color(148, 223, 227);
				break;
			case "Roxo": // roxo
				cor1 = new Color(74, 54, 163);
				cor2 = new Color(164, 150, 227);
				break;
		}

		// ajusta nome da casa (quebrando a linha)
		ArrayList<String> texto = quebraLinhas(nome, 10);
		int linhas = texto.size();
		JPanel bloco = new JPanel(new GridLayout(linhas+2, 1));
		Border ajuste = new EmptyBorder(2, 4, 2, 4);
		JLabel espaco1 = new JLabel(""); // espaco para ajuste no grid
		bloco.add(espaco1);
		for (int i = 0; i < linhas; i++) {
			JLabel linha = new JLabel(texto.get(i));
			linha.setForeground(cor1);
			linha.setFont(new Font("Courier New", Font.BOLD, tam/7));
			linha.setBorder(ajuste);
			linha.setHorizontalTextPosition(JLabel.CENTER);
			linha.setVerticalTextPosition(JLabel.CENTER);
			linha.setHorizontalAlignment(JLabel.CENTER);
			linha.setVerticalAlignment(JLabel.CENTER);
			bloco.add(linha);
		}


		bloco.setBounds(x, y, tam, tam);

		Border borda = new LineBorder(cor1, 3);
		bloco.setBorder(borda);
		bloco.setBackground(cor2);

		display.janela.add(bloco);
		display.janela.revalidate();
    }

	public static void desenhaProprietario(Display display, int index, int x, int y, int tam) {

		Propriedade casa = display.jogo.casaPropriedade(index);

		Color cor1 = new Color(209, 71, 133);
		Color cor2 = new Color(255, 196, 223);

		if (casa != null) {
			// se houver proprietatio
			if (casa.getIdProprietario() != -1) {
				// escolhe cor com base no id do proprietario
				switch (casa.getIdProprietario()) {
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
				String texto = String.format("CASAS: %d", casa.getQuantConstrucoes());
				JLabel prop = new JLabel(texto);
				prop.setForeground(cor1);
				prop.setFont(new Font("Courier New", Font.BOLD, 12));
				Border ajuste = new EmptyBorder(5, 5, 5, 5);
				prop.setBorder(ajuste);	
				prop.setPreferredSize(new Dimension(tam, tam/3));

				prop.setHorizontalTextPosition(JLabel.CENTER);
				prop.setVerticalTextPosition(JLabel.TOP);
				prop.setHorizontalAlignment(JLabel.CENTER);
				prop.setVerticalAlignment(JLabel.TOP);

				JPanel painel = new JPanel();
				Border borda = new LineBorder(cor1, 3);
				painel.setBackground(cor2);
				painel.setBorder(borda);
				painel.setBounds(x,y,tam,tam/3);

				painel.add(prop);
				display.janela.add(painel);
				display.janela.revalidate();
			}
		}

	}

	public static void desenhaDados(Display display) {

		// guarda caminho da imagem do dado atual
		String caminho1 = String.format("src/assets/dado%d.png", display.jogo.dado1.getValor());
		String caminho2 = String.format("src/assets/dado%d.png", display.jogo.dado2.getValor());

		// carrega imagem e redimensiona o seu tamanho
		ImageIcon imgDado1 = new ImageIcon(caminho1);
		Image image = imgDado1.getImage();
		int tam = display.Y_SCREEN/10;
		Image imgDadoRes = image.getScaledInstance(tam, tam, Image.SCALE_SMOOTH);
		imgDado1 = new ImageIcon(imgDadoRes);

		ImageIcon imgDado2 = new ImageIcon(caminho2);
		image = imgDado2.getImage();
		imgDadoRes = image.getScaledInstance(tam, tam, Image.SCALE_SMOOTH);
		imgDado2 = new ImageIcon(imgDadoRes);

		JLabel dado1 = new JLabel(imgDado1);
		JLabel dado2 = new JLabel(imgDado2);

		Border ajuste = new EmptyBorder(10, 10, 10, 10);
		dado1.setBorder(ajuste);
		dado2.setBorder(ajuste);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(1,2));

		painel.setBounds(tam*5/2, 2*tam, 4*tam, 2*tam);

		painel.add(dado1);
		painel.add(dado2);

		display.janela.add(painel);
		display.janela.revalidate();
	}


	
	public static void desenhaJogadores(Display display) {
		// calcula disposição das casas no tabuleiro
		int quant = display.jogo.casas.size() / 4; // quantidade por lado
		int tam = (display.Y_SCREEN * 8/10) / quant; // tamanho das casas
													 //
		
		int num = display.jogo.jogadores.size();
		for (int i = 0; i < num; i++) {
			
			int x, y;
			int casa = display.jogo.jogadores.get(i).getCasa();
			// ajusta tamanho e posicao no tabuleiro
			if (casa < quant) {
				x = casa*tam;
				y = 0;
			} else if (casa < 2*quant) {
				x = quant*tam;
				y = (casa%quant)*tam;
			} else if (casa < 3*quant) {
				x = (quant - casa%quant)*tam;
				y = quant*tam;
			} else {
				x = 0;
				y = (quant - casa%quant)*tam;
			}

			posicionaJogador(display, display.jogo.jogadores.get(i).getId(), x, y, tam);

		}

		display.janela.repaint();
	}

	private static void posicionaJogador(Display display, int id, int x, int y, int tam) {

		// quadradinho jogador
		JLabel quad = new JLabel();
		Color cor = new Color(0,0,0);
		// unidade de posicao
		int un = tam/3;
		int lado = tam/4;
		int offset = (un - lado)/2;


		switch (id) {
			case 0: // vermelho
				cor = new Color(219, 68, 68);
				quad.setBounds(x+offset, y, lado, lado);
				break;
			case 1: // laranja
				cor = new Color(230, 145, 67);
				quad.setBounds(x+un+offset, y, lado, lado);
				break;
			case 2: // amarelo
				cor = new Color(224, 197, 58);
				quad.setBounds(x+2*un+offset, y, lado, lado);
				break;
			case 3: // verde
				cor = new Color(72, 214, 54);
				quad.setBounds(x+offset, y+2*un, lado, lado);
				break;
			case 4: // azul
				cor = new Color(38, 181, 189);
				quad.setBounds(x+offset+un, y+2*un, lado, lado);
				break;
			case 5: // roxo
				cor = new Color(74, 54, 163);
				quad.setBounds(x+2*un+offset, y+2*un, lado, lado);
				break;
		}

		quad.setBackground(cor);
		quad.setOpaque(true);
		quad.setText("+");
		quad.setForeground(cor);
		quad.setFont(new Font("Courier New", Font.BOLD, 20));

		display.janela.add(quad);
	}

	public static void desenhaCarta(Display display, boolean exibe) {
		// seta tamanhos proporcionais
		int x = display.X_SCREEN *1/5;
		int y = display.Y_SCREEN*2/5;
		int tam = display.Y_SCREEN/10;
	
		JPanel painel = new JPanel();

		if (exibe) {
			// pega infos carta do baralho
			JLabel nome = new JLabel(display.jogo.cartas.getFirst().getNome());
			nome.setForeground(new Color(38, 181, 189));
			nome.setFont(new Font("Courier New", Font.BOLD, 20));
			nome.setHorizontalTextPosition(JLabel.CENTER);
			nome.setVerticalTextPosition(JLabel.CENTER);
			nome.setHorizontalAlignment(JLabel.CENTER);
			nome.setVerticalAlignment(JLabel.CENTER);

			String descricao = display.jogo.cartas.getFirst().getDescricao();	
			ArrayList<String> texto = quebraLinhas(descricao, 15);
			int linhas = texto.size() + 1;
			painel = new JPanel(new GridLayout(linhas, 1));
			painel.add(nome);
			Border ajuste = new EmptyBorder(10, 10, 10, 10);
			for (int i = 0; i < linhas-1; i++) {
				JLabel linha = new JLabel(texto.get(i));
				linha.setForeground(new Color(38, 181, 189));
				linha.setFont(new Font("Courier New", Font.BOLD, 14));
				linha.setBorder(ajuste);
				painel.add(linha);
			}
		}
		else {
			// carrega imagem da logo e redimensiona o seu tamanho
			ImageIcon logo = new ImageIcon("src/assets/logoBCtransp.png");
			Image image = logo.getImage();
			Image logoRes = image.getScaledInstance(2*tam, 2*tam, Image.SCALE_SMOOTH);
			logo = new ImageIcon(logoRes);
			// adiciona a logo numa label
			JLabel imagem = new JLabel();
			imagem.setIcon(logo);
			imagem.setVerticalAlignment(JLabel.BOTTOM);
			imagem.setHorizontalAlignment(JLabel.CENTER);
			imagem.setHorizontalAlignment(JLabel.CENTER);
			imagem.setVerticalAlignment(JLabel.BOTTOM);
			imagem.setBounds(x, y, 2*tam, 3*tam);

			painel.add(imagem);
		}
		painel.setBackground(new Color(175,238,238));
		painel.setBounds(x, y, tam*2, tam*5/2);

		Border borda = new LineBorder(new Color(72, 90, 93), 2);
		painel.setBorder(borda);

		display.janela.add(painel);
		display.janela.revalidate();

	}




	// funcao auxiliar que quebra um texto em um vetor de linhas com no maximo tam caracteres
	private static ArrayList<String> quebraLinhas(String texto, int largura) {
		
		ArrayList<String> linhas = new ArrayList<>(); // Lista que armazenará as linhas quebradas
		StringBuilder linhaAtual = new StringBuilder();

		for (String palavra : texto.split(" ")) 
		{
			// Cria uma linha temporária juntando a linha atual com a próxima palavra
			String linhaTemp = linhaAtual.toString() + (linhaAtual.length() > 0 ? " " : "") + palavra;

			// verifica se quantidade de caracteres da linha temporária excede o limite
			if (linhaTemp.length() > largura) {
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
	
		return linhas;
	}






}
