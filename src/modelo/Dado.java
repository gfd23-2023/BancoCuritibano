package modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Dado
{
	private int valor; // valor atual do dado
	// coordenadas (mudei para "pos" pq eh mais curto hihih)
	private int posX; 
	private int posY;	

	// construtor
	public Dado() {
		this.jogaDados(); // inicia com um valor aleatorio
	}

	// getters e setters
	public int getValor() {
		return valor;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int x) {
		this.posX = x;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int y) {
		this.posY = y;
	}


	// muda o valor do dado para um numero aleatorio entre 1 e 6
	public void jogaDados() {
		int dados = (int) (Math.random() * 6) + 1;
		this.valor = dados;
	}

	// verifica se jogou dois dados iguais
	public static boolean dadosIguais(Dado dado1, Dado dado2) {
		if (dado1.valor == dado2.valor)
			return true;
		else
			return false;
	}
}

//Ideia: ser um botão que fica o tempo todo no tabuleiro
/*Só deve ser possível mexer os dados quando as compras forem feitas,
 *aluguéis pagos, não estiver preso e não estiver de férias*/
class janelaDado
{
	JFrame frame = new JFrame("Dados");
	JButton dado1 = new JButton("Dado 1");
	JButton dado2 = new JButton("Dado 2");
	JPanel buttonPanel = new JPanel();
	JPanel resultadoPanel = new JPanel();			//armazena o histórico dos dados
	int retornos[] = new int[2];					//vetor que vai devolver o valor dos dados
	
	//Apenas mostra os botões dos dados na tela
	public void exibeBotoes()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
		frame.setSize(200, 200);
		
		//Adiciona os botões no painel
		buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(dado1);
        buttonPanel.add(dado2);

		// Adiciona o painel de botões na parte superior
        frame.add(buttonPanel);

        // Adiciona o painel de resultados na parte central
        frame.add(resultadoPanel);

		frame.setVisible(true);
	}

	//Executa a ação dos botões
	public int exibeAcao()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
		frame.setSize(200, 200);

		dado1.addActionListener(new ActionListener() {
			@Override	//@Override evita erros ao sobrescrever métodos
			public void actionPerformed(ActionEvent e)
			{
				// Limpa o painel de resultados (as labels)
                resultadoPanel.removeAll();

				int deslocamento = Dado.geraNumero();
				retornos[0] = deslocamento;

				JLabel label = new JLabel(String.format("Dado 1: %d casas", deslocamento));
				label.setHorizontalAlignment(SwingConstants.LEFT); // Alinha à esquerda
				resultadoPanel.setLayout(new BoxLayout(resultadoPanel, BoxLayout.Y_AXIS));

				// Adiciona a nova label ao painel de resultados
                resultadoPanel.add(label);

                // Revalida e repinta o painel de resultados
                resultadoPanel.revalidate(); // Atualiza a interface para mostrar a label adicionada
                resultadoPanel.repaint();    // Repinta a interface gráfica
			}
		});

		dado2.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e)
            {
				// Limpa o painel de resultados (as labels)
                //resultadoPanel.removeAll();

				int deslocamento2 = Dado.geraNumero();
				retornos[1] = deslocamento2;

				JLabel label2 = new JLabel(String.format("Dado 2: %d casas", deslocamento2));
				label2.setHorizontalAlignment(SwingConstants.LEFT); // Alinha à esquerda
				resultadoPanel.setLayout(new BoxLayout(resultadoPanel, BoxLayout.Y_AXIS));

                // Adiciona a nova label ao painel de resultados
                resultadoPanel.add(label2);

                // Revalida e repinta o painel de resultados
                resultadoPanel.revalidate();
                resultadoPanel.repaint(); 

            }
        });

		frame.setVisible(true);
		
		int retorno = retornos[0] + retornos[1];
		
		return retorno;
	}
}
