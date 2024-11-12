import java.util.ArrayList;		//para o vetor de propriedades
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro {
    private JFrame frame;
    private JTextField nomeField;
    private JTextField caminhoField;
    private JLabel imagemLabel;
    private ArrayList<Jogador> players = new ArrayList<>();

    public Registro() {
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
                    Jogador player = new Jogador(nome, foto);
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
        new Registro();
    }
}
