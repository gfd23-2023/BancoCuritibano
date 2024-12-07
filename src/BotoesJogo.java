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
