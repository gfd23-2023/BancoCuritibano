import java.util.ArrayList;		//para o vetor de propriedades
import javax.swing.ImageIcon;	//para as imagens dos personagens ImageIcon: boa para exibição
								//BuferedImage: boa para manipulação
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe Player para registrar os dados do jogador
class Player {
    String nome;
    int renda;
    private ImageIcon sprite;

    // Método para criar um novo jogador
    public static Player constroi_player(String nome, ImageIcon foto) {
        Player novoPlayer = new Player();
        novoPlayer.nome = nome;
        novoPlayer.renda = 1500; // Cada jogador começa com 1.500 reais
        novoPlayer.sprite = foto;
        return novoPlayer;
    }
}

public class Player {
    private JFrame frame;
    private JTextField nomeField;
    private JTextField caminhoField;
    private JLabel imagemLabel;
    private ArrayList<Player> players = new ArrayList<>();

    public player() {
        // Configura a janela principal
        frame = new JFrame("Registro de Jogadores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        // Campo de texto para o nome do jogador
        nomeField = new JTextField(20);
        frame.add(new JLabel("Digite seu nome:"));
        frame.add(nomeField);

        // Campo de texto para o caminho da imagem
        caminhoField = new JTextField(20);
        frame.add(new JLabel("Caminho da imagem:"));
        frame.add(caminhoField);

        // Botão para adicionar o jogador
        JButton addButton = new JButton("Adicionar");
        frame.add(addButton);

        // Área para exibir a imagem
        imagemLabel = new JLabel();
        imagemLabel.setPreferredSize(new Dimension(400, 400));
        frame.add(imagemLabel);

        // Ação do botão "Adicionar"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String caminho = caminhoField.getText();

                // Verifica se o usuário digitou "ok" para encerrar
                if (nome.equalsIgnoreCase("ok") || caminho.equalsIgnoreCase("ok")) {
                    JOptionPane.showMessageDialog(frame, "Registro encerrado.");
                    frame.dispose(); // Fecha a janela
                } else {
                    // Cria e exibe o jogador com a imagem
                    ImageIcon foto = new ImageIcon(caminho);
                    Player player = Player.constroi_player(nome, foto);
                    players.add(player); // Adiciona o jogador à lista
                    imagemLabel.setIcon(foto); // Exibe a imagem
                    frame.repaint();
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Inicia o aplicativo
        new player();
    }
}

/*
class Player
{
	String nome;
	int renda;
	//talvez seja melhor fazer o vetor de propriedades ser do tipo terreno
	ArrayList<String> propriedades = new ArrayList<>();	//<> garante segurança ao vetor de terrenos
	private ImageIcon sprite;

	//constroi o player
	//tipo do retorno -> nome do método -> parâmetros
	static Player constroi_player (String nome, ImageIcon foto)
	{
		Player novo_player = new Player();

		novo_player.nome = nome;
		novo_player.renda = 1500;			//cada jogador começa com 1.500 reais
		novo_player.sprite = foto;			//tentar colocar o caminho da foto

		return novo_player;
	}
}

public class player
{
	public static void main (String args[])
	{

		//cria uma lista de jogadores
		//para adicionar novos jogadores: players.add(constroi_player(nome, foto));
		ArrayList<Player> players = new ArrayList<>();
		boolean ok = false;
		Scanner teclado_de_registro = new Scanner(System.in);
		String nome, caminho;
		ImageIcon foto;

		//A ideia aqui é adicionar quantos jogadores quiser
		while (!ok)
		{
			System.out.printf("Digite seu nome: ");
			nome = teclado_de_registro.nextLine();						//pega o nome até o ENTER
			if (nome.equalsIgnoreCase("ok")) ok = true;					//verifica se não foi digitado ok

			System.out.printf("Escolha seu personagem: ");
			caminho = teclado_de_registro.nextLine();					//a ideia é digitar o caminho da foto
			if (caminho.equalsIgnoreCase("ok")) ok = true;				//verifica se não foi digitado ok

			foto = new ImageIcon(caminho);								//cria um objeto "foto" com a imagem
			
			if (!ok) players.add(Player.constroi_player(nome, foto));					//adiciona o novo jogador na lista
		}

		System.out.printf("Personagens registrados!\n");

		teclado_de_registro.close();
	}
}*/
