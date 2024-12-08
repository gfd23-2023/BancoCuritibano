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

// CLASSE DESENHA PAINEIS DE INFORMAÇÃO
public class DesenhaInfo {

	// desenha painel com as informações da rodada 
	// exibe numero da rodada e jogador da vez
	public static void desenhaInfoRodada(Display display) {

		display.janela.setLayout(null);
		// painel da rodada e jogador da vez
		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(2,1)); // duas linhas, uma coluna

		// cria labels para guardar o texto
		JLabel rodada = new JLabel();
		JLabel jogador = new JLabel();

		// adiciona textos
		rodada.setText(String.format("Rodada: %d", display.jogo.getRodada()));
		// nome do jogador da vez
		String nome = display.jogo.jogadores.get(display.jogo.getJogada()).getNome();
		jogador.setText(String.format("Jogador: %s", nome));

		// centraliza o texto
		rodada.setHorizontalTextPosition(JLabel.CENTER);
		rodada.setVerticalTextPosition(JLabel.TOP);
		jogador.setHorizontalTextPosition(JLabel.CENTER);
		jogador.setVerticalTextPosition(JLabel.BOTTOM);
		
		// define as cores
		Color cor1 = new Color(209, 71, 133);
		Color cor2 = new Color(255, 196, 223);

		// seta cores dos textos
		rodada.setForeground(cor1);
		rodada.setFont(new Font("CourierNew", Font.BOLD, 20));
		jogador.setForeground(cor1);
		jogador.setFont(new Font("CourierNew", Font.BOLD, 20));

		int pos = display.X_SCREEN*3/5;
		int tam = display.Y_SCREEN/20;
		painel.setBounds(pos, tam, 8*tam, 2*tam);

		Border ajuste = new EmptyBorder(10, 10, 10, 10);
		rodada.setBorder(ajuste);
		jogador.setBorder(ajuste);
		painel.setBackground(cor2);

		painel.add(rodada);
		painel.add(jogador);

		display.janela.add(painel);
		display.janela.revalidate();
	}

	// desenha painel com as informações de todos os jogadores
	// posiciona do canto direito do display como uma tabela 3x2
	// cada celula contem info sobre nome, dinheiro e estado do jogador
	public static void desenhaInfoJogadores(Display display) {
		// seta tamanhos proporcionais
		int x = display.X_SCREEN *3/5;
		int y = display.Y_SCREEN *1/5;
		int tam = display.Y_SCREEN/20;

		// cria paineis de todos os jogadores e posiciona lado a lado (tabela)
		int quant = display.jogo.jogadores.size();
		for (int i = 0; i < quant; i++) {
			JPanel painel = criaPainelJogador(display, display.jogo.jogadores.get(i));
			if (i < 3)
				painel.setBounds(x, y+(2*i*tam), 4*tam, 2*tam);
			else
				painel.setBounds(x+4*tam, y+(2*(i-3)*tam), 4*tam, 2*tam);

			display.janela.add(painel);
		}
		
		display.janela.revalidate();
		display.janela.repaint();
	}

	// cria um painel de um jogador em especifico
	private static JPanel criaPainelJogador(Display display, Jogador jogador) {

		// cria campos para infos
		JLabel nome = new JLabel(jogador.getNome());
		JLabel dinheiro = new JLabel(String.format("Dinheiro: %d", jogador.getDinheiro()));
		JLabel estado = new JLabel();

		// escolhe cor com base nos ids
		Color cor1 = new Color(0,0,0);
		Color cor2 = new Color(0,0,0);

		switch (jogador.getId()) {
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

		// seta fontes e cores
		nome.setForeground(cor1);
		nome.setFont(new Font("CourierNew", Font.BOLD, 20));
		dinheiro.setForeground(cor1);
		dinheiro.setFont(new Font("CourierNew", Font.BOLD, 15));
		estado.setForeground(cor2);
		estado.setFont(new Font("CourierNew", Font.BOLD, 15));
		estado.setOpaque(true);

		// configura texto do estado
		if (jogador.getId() == display.jogo.getJogada()) {
			estado.setText("JOGANDO");
			estado.setBackground(cor1);
		}
		else if (jogador.estaFalido()) {
			estado.setText("FALIDO");
			estado.setBackground(new Color(245, 54, 102));
			estado.setForeground(new Color(255,255,255));
		}
		else if (jogador.estaNaCadeia()) {
			estado.setText("PRESO");
			estado.setBackground(new Color(245, 54, 102));
			estado.setForeground(new Color(255,255,255));
		}
		else if (jogador.estaEsperando()) {
			String texto = String.format("ESPERANDO (%d)", jogador.getRodadasEsperar() - jogador.getRodadasEsperando());
			estado.setText(texto);
			estado.setBackground(new Color(245, 54, 102));
			estado.setForeground(new Color(255,255,255));
		}
		else {
			estado.setText("");
			estado.setBackground(cor2);
		}

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(3,1)); // 3 linhas, 1 coluna
		
		painel.add(nome);
		painel.add(dinheiro);
		painel.add(estado);

		painel.setBackground(cor2);
		Border borda = new LineBorder(cor1, 5);
		painel.setBorder(borda);

		return painel;
	}

	public static void desenhaInfoPropriedade(Display display) {

		Propriedade casa = (Propriedade) display.jogo.casaJogada();
		Color cor1 = new Color(42, 56, 61);
		Color cor2 = new Color(139, 168, 179);
		
		JLabel nome	= new JLabel(casa.getNome());
		nome.setForeground(cor2);
		nome.setFont(new Font("CourierNew", Font.BOLD, 20));
		nome.setBackground(cor1);
		nome.setOpaque(true);

		JLabel aluguel = new JLabel(String.format("Aluguel: %d", casa.consultaAluguel(0)));
		JLabel casa1 = new JLabel(String.format("1 Casa: %d", casa.consultaAluguel(1)));
		JLabel casa2 = new JLabel(String.format("2 Casas: %d", casa.consultaAluguel(2)));
		JLabel casa3 = new JLabel(String.format("3 Casas: %d", casa.consultaAluguel(3)));
		JLabel casa4 = new JLabel(String.format("4 Casas: %d", casa.consultaAluguel(4)));
		JLabel valor = new JLabel(String.format("Valor compra: %d", casa.getValor()));
		JLabel constr = new JLabel(String.format("Valor construção: %d", casa.getValorConstrucao()));

		Font fonte = (new Font("CourierNew", Font.BOLD, 13));
		aluguel.setFont(fonte);
		casa1.setFont(fonte);
		casa2.setFont(fonte);
		casa3.setFont(fonte);
		casa4.setFont(fonte);
		valor.setFont(fonte);
		constr.setFont(fonte);

		aluguel.setForeground(cor1);
		casa1.setForeground(cor1);
		casa2.setForeground(cor1);
		casa3.setForeground(cor1);
		casa4.setForeground(cor1);
		valor.setForeground(cor1);
		constr.setForeground(cor1);

		JPanel painel = new JPanel(new GridLayout(8,1));
		painel.add(nome);
		painel.add(aluguel);
		painel.add(casa1);
		painel.add(casa2);
		painel.add(casa3);
		painel.add(casa4);
		painel.add(valor);
		painel.add(constr);

		painel.setBackground(cor2);
		Border borda = new LineBorder(cor1, 4);
		painel.setBorder(borda);
		
		int pos = display.X_SCREEN*3/5;
		int tam = display.Y_SCREEN/20;
		painel.setBounds(pos, 10*tam, 8*tam, 5*tam);

		display.janela.add(painel);
		display.janela.revalidate();
	}
	
}
