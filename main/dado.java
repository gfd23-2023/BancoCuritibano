/*Classe que gera números aleatórios para os deslocamentos*/
/*Precisamos pensar em uma forma de impedir a ação dos botões dos
 *dados quando não for momento de girar os dados*/

package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Dado
{
	public static int geraNumero ()
	{
		int dados = (int) (Math.random () * 6) + 1;			//gera números de 1 ate 12

		return dados;
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

	public void exibeAcao()
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
	}
}

public class dado
{
	public static void main (String agrs[])
	{
		janelaDado janela = new janelaDado();
		janela.exibeBotoes();
		janela.exibeAcao();
	}
}
