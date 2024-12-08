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

// CLASSE BOTOES JOGO
// responsávavel por exibir os botões que excecutam ações do jogo

public class BotoesJogo {

	public static void exibeJogarDados(Display display) {
		int un = display.Y_SCREEN/10;
		BJogarDados botao = new BJogarDados(un);
		display.janela.setLayout(null);
		display.janela.add(botao);
		display.janela.revalidate();
		botao.acaoJogarDados(display);
	}	

	public static void exibeRetirarCarta(Display display) {
		int un = display.Y_SCREEN/10;
		BRetirarCarta botao = new BRetirarCarta(un);
		display.janela.setLayout(null);
		display.janela.add(botao);
		display.janela.revalidate();
		botao.acaoRetirarCarta(display);
	}	

	public static void exibeProximo(Display display) {
		int un = display.Y_SCREEN/10;
		BProximo botao = new BProximo(un);
		display.janela.setLayout(null);
		display.janela.add(botao);
		display.janela.revalidate();
		botao.acaoProximo(display);
	}	

	public static void exibePagarOuCadeia(Display display) {
		int un = display.Y_SCREEN/10;
		BPagar botao1 = new BPagar(un);
		BCadeia botao2 = new BCadeia(un);
		
		JPanel painel = new JPanel(new GridLayout(1,2));
		painel.setBounds(11*un,8*un,4*un,un);
		painel.add(botao1);
		painel.add(botao2);
		display.janela.setLayout(null);
		display.janela.add(painel);
		display.janela.revalidate();
		botao1.acaoPagar(display);
		botao2.acaoCadeia(display);
	}	
}

class BJogarDados extends JPanel
{
    JButton jogarDados = new JButton("JOGAR DADOS");

    //personalização do botão
    public BJogarDados(int tam)
    {
		jogarDados.setFont(new Font("Courier New", Font.BOLD, 25));
		jogarDados.setBackground(new Color(255,192,203));
		jogarDados.setForeground(new Color(250,128,114));
		jogarDados.setPreferredSize(new Dimension(4*tam, tam*2/3));
		jogarDados.setHorizontalTextPosition(JButton.CENTER);
		jogarDados.setVerticalTextPosition(JButton.CENTER);
		setBounds(11*tam,8*tam,4*tam,tam);
		add(jogarDados);

    }

    public void acaoJogarDados(Display display)
    {
        jogarDados.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
				display.jogo.jogaDados();
				display.janela.getContentPane().removeAll();
				display.jogo.setEstado(Estados.JOGAR_MOVIMENTO);
				display.atualizaDisplay();
            }
        });
	}
}

class BRetirarCarta extends JPanel
{
    JButton retirar = new JButton("RETIRE UMA CARTA!");

    //personalização do botão
    public BRetirarCarta(int tam)
    {
		retirar.setFont(new Font("Courier New", Font.BOLD, 25));
		retirar.setBackground(new Color(255,192,203));
		retirar.setForeground(new Color(250,128,114));
		retirar.setPreferredSize(new Dimension(6*tam, tam*2/3));
		retirar.setHorizontalTextPosition(JButton.CENTER);
		retirar.setVerticalTextPosition(JButton.CENTER);
		setBounds(11*tam,8*tam,4*tam,tam);
		add(retirar);

    }

    public void acaoRetirarCarta(Display display)
    {
        retirar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
				display.janela.getContentPane().removeAll();
				display.jogo.setEstado(Estados.JOGAR_CARTA_ACAO);
				display.atualizaDisplay();
            }
        });
	}
}


class BProximo extends JPanel
{
    JButton proximo = new JButton("PRÓXIMO");

    //personalização do botão
    public BProximo(int tam)
    {
		proximo.setFont(new Font("Courier New", Font.BOLD, 25));
		proximo.setBackground(new Color(255,192,203));
		proximo.setForeground(new Color(250,128,114));
		proximo.setPreferredSize(new Dimension(4*tam, tam*2/3));
		proximo.setHorizontalTextPosition(JButton.CENTER);
		proximo.setVerticalTextPosition(JButton.CENTER);
		setBounds(11*tam,8*tam,4*tam,tam);
		add(proximo);

    }

    public void acaoProximo(Display display)
    {
        proximo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
				display.jogo.proximaJogada();
				display.janela.getContentPane().removeAll();
				display.jogo.setEstado(Estados.JOGAR_DADOS);
				display.atualizaDisplay();
            }
        });
	}
}

class BPagar extends JPanel
{
    JButton pagar = new JButton("PAGAR");

    //personalização do botão
    public BPagar(int tam)
    {
		pagar.setFont(new Font("Courier New", Font.BOLD, 25));
		pagar.setBackground(new Color(255,192,203));
		pagar.setForeground(new Color(250,128,114));
		pagar.setPreferredSize(new Dimension(2*tam, tam*2/3));
		pagar.setHorizontalTextPosition(JButton.CENTER);
		pagar.setVerticalTextPosition(JButton.CENTER);
		setBounds(11*tam,8*tam,2*tam,tam);
		add(pagar);

    }

    public void acaoPagar(Display display)
    {
        pagar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
				// opcao 0 -> pagar
				display.jogo.cartaPagarOuCadeia(0);
				display.janela.getContentPane().removeAll();
				display.jogo.setEstado(Estados.JOGAR_PROXIMO);
				display.atualizaDisplay();
            }
        });
	}
}

class BCadeia extends JPanel
{
    JButton cadeia = new JButton("CADEIA");

    //personalização do botão
    public BCadeia(int tam)
    {
		cadeia.setFont(new Font("Courier New", Font.BOLD, 25));
		cadeia.setBackground(new Color(255,192,203));
		cadeia.setForeground(new Color(250,128,114));
		cadeia.setPreferredSize(new Dimension(2*tam, tam*2/3));
		cadeia.setHorizontalTextPosition(JButton.CENTER);
		cadeia.setVerticalTextPosition(JButton.CENTER);
		setBounds(11*tam,8*tam,2*tam,tam);
		add(cadeia);

    }

    public void acaoCadeia(Display display)
    {
        cadeia.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
				// opcao 1 -> cadeia
				display.jogo.cartaPagarOuCadeia(1);
				display.janela.getContentPane().removeAll();
				display.jogo.setEstado(Estados.JOGAR_PROXIMO);
				display.atualizaDisplay();
            }
        });
	}
}
