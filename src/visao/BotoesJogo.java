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
import java.io.Serializable;
import java.awt.*;
import java.util.*;

// CLASSE BOTOES JOGO
// responsávavel por exibir os botões que excecutam ações do jogo

public class BotoesJogo implements Serializable {

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

	public static void exibeComprarOuProximo(Display display) {
		int un = display.Y_SCREEN/10;
		BComprar botao1 = new BComprar(un);
		BProximo botao2 = new BProximo(un);
		
		JPanel painel = new JPanel(new GridLayout(1,2));
		painel.setBounds(10*un,8*un,6*un,un);
		painel.add(botao1);
		painel.add(botao2);
		display.janela.setLayout(null);
		display.janela.add(painel);
		display.janela.revalidate();
		botao1.acaoComprar(display);
		botao2.acaoProximo(display);
	}	

	public static void exibePagarAluguel(Display display) {
		int un = display.Y_SCREEN/10;
		BAluguel botao = new BAluguel(un);
		display.janela.setLayout(null);
		display.janela.add(botao);
		display.janela.revalidate();
		botao.acaoAluguel(display);
	}	

	public static void exibeConstruir(Display display) {
		int un = display.Y_SCREEN/10;
		BConstruir botao = new BConstruir(un);
		display.janela.setLayout(null);
		display.janela.add(botao);
		display.janela.revalidate();
		botao.acaoConstruir(display);
	}	

	public static void exibeCadeia(Display display) {
		int un = display.Y_SCREEN/10;
		BCadeia botao = new BCadeia(un);
		display.janela.setLayout(null);
		display.janela.add(botao);
		display.janela.revalidate();
		botao.acaoCadeia(display);
	}	

	public static void exibePagarImposto(Display display) {
		int un = display.Y_SCREEN/10;
		BImposto botao = new BImposto(un);
		display.janela.setLayout(null);
		display.janela.add(botao);
		display.janela.revalidate();
		botao.acaoImposto(display);
	}	

	public static void exibeSalvarJogo(Display display){
		int un = display.Y_SCREEN/10;
		BSalvarJogo botao = new BSalvarJogo(un);
		display.janela.setLayout(null);
		display.janela.add(botao);
		display.janela.revalidate();
		botao.acaoSalvar(display);
	}
}

class BJogarDados extends JPanel
{
    JButton jogarDados = new JButton("JOGAR DADOS");

    //personalização do botão
    public BJogarDados(int tam)
    {
		jogarDados.setFont(new Font("Courier New", Font.BOLD, 20));
		jogarDados.setBackground(new Color(255,192,203));
		jogarDados.setForeground(new Color(250,128,114));
		jogarDados.setPreferredSize(new Dimension(4*tam, tam*2/3));
		jogarDados.setHorizontalTextPosition(JButton.CENTER);
		jogarDados.setVerticalTextPosition(JButton.CENTER);
		setBounds(10*tam,8*tam,3*tam,tam);
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
		setBounds(10*tam,8*tam,3*tam,tam);
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
				if (display.jogo.getEstado() == Estados.JOGAR_CADEIA)
					display.jogo.acaoCasa();
				else // carta
					// opcao 1 -> cadeia
					display.jogo.cartaPagarOuCadeia(1);

				display.janela.getContentPane().removeAll();
				display.jogo.setEstado(Estados.JOGAR_PROXIMO);
				display.atualizaDisplay();
            }
        });
	}
}

class BAluguel extends JPanel
{
    JButton aluguel = new JButton("PAGAR ALUGUEL");

    //personalização do botão
    public BAluguel(int tam)
    {
		aluguel.setFont(new Font("Courier New", Font.BOLD, 25));
		aluguel.setBackground(new Color(255,192,203));
		aluguel.setForeground(new Color(250,128,114));
		aluguel.setPreferredSize(new Dimension(4*tam, tam*2/3));
		aluguel.setHorizontalTextPosition(JButton.CENTER);
		aluguel.setVerticalTextPosition(JButton.CENTER);
		setBounds(11*tam,8*tam,4*tam,tam);
		add(aluguel);

    }

    public void acaoAluguel(Display display)
    {
        aluguel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
				display.jogo.acaoCasa();
				display.janela.getContentPane().removeAll();
				display.jogo.setEstado(Estados.JOGAR_PROXIMO);
				display.atualizaDisplay();
            }
        });
	}
}

class BComprar extends JPanel
{
    JButton comprar = new JButton("COMPRAR");

    //personalização do botão
    public BComprar(int tam)
    {
		comprar.setFont(new Font("Courier New", Font.BOLD, 23));
		comprar.setBackground(new Color(255,192,203));
		comprar.setForeground(new Color(250,128,114));
		comprar.setPreferredSize(new Dimension(4*tam, tam*2/3));
		comprar.setHorizontalTextPosition(JButton.CENTER);
		comprar.setVerticalTextPosition(JButton.CENTER);
		setBounds(11*tam,8*tam,4*tam,tam);
		add(comprar);

    }

    public void acaoComprar(Display display)
    {
        comprar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
				display.jogo.acaoCasa();
				display.janela.getContentPane().removeAll();
				display.jogo.setEstado(Estados.JOGAR_PROXIMO);
				display.atualizaDisplay();
            }
        });
	}
}

class BConstruir extends JPanel
{
    JButton construir = new JButton("CONSTRUIR");

    //personalização do botão
    public BConstruir(int tam)
    {
		construir.setFont(new Font("Courier New", Font.BOLD, 23));
		construir.setBackground(new Color(255,192,203));
		construir.setForeground(new Color(250,128,114));
		construir.setPreferredSize(new Dimension(2*tam, tam*2/3));
		construir.setHorizontalTextPosition(JButton.CENTER);
		construir.setVerticalTextPosition(JButton.CENTER);
		setBounds(11*tam,8*tam,3*tam,tam);
		add(construir);

    }

    public void acaoConstruir(Display display)
    {
        construir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
				display.jogo.acaoCasa();
				display.janela.getContentPane().removeAll();
				display.jogo.setEstado(Estados.JOGAR_PROXIMO);
				display.atualizaDisplay();
            }
        });
	}
}

class BImposto extends JPanel
{
    JButton imposto = new JButton("PAGAR IMPOSTO");

    //personalização do botão
    public BImposto(int tam)
    {
		imposto.setFont(new Font("Courier New", Font.BOLD, 23));
		imposto.setBackground(new Color(255,192,203));
		imposto.setForeground(new Color(250,128,114));
		imposto.setPreferredSize(new Dimension(4*tam, tam*2/3));
		imposto.setHorizontalTextPosition(JButton.CENTER);
		imposto.setVerticalTextPosition(JButton.CENTER);
		setBounds(11*tam,8*tam,4*tam,tam);
		add(imposto);

    }

    public void acaoImposto(Display display)
    {
        imposto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
				display.jogo.acaoCasa();
				display.janela.getContentPane().removeAll();
				display.jogo.setEstado(Estados.JOGAR_PROXIMO);
				display.atualizaDisplay();
            }
        });
	}
}

class BSalvarJogo extends JPanel
{
	JButton salvamento = new JButton("SALVAR JOGO");

	public BSalvarJogo(int tam){
		salvamento.setFont(new Font ("Courier New", Font.BOLD, 20));
		salvamento.setBackground(new Color(255,192,203));
		salvamento.setForeground(new Color(250,128,114));
		salvamento.setPreferredSize(new Dimension(4*tam, tam*2/3));
		salvamento.setHorizontalTextPosition(JButton.CENTER);
		salvamento.setVerticalTextPosition(JButton.CENTER);
		setBounds(13* tam , 8*tam, 3*tam, tam);
		add(salvamento);
	}

	public void acaoSalvar(Display display){
		salvamento.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				display.jogo.setEstado(Estados.JOGAR_SALVAR);
				display.atualizaDisplay();
			}
		});
	}

}
