package controlador;

import java.io.*;
import java.io.Serializable;
import javax.swing.SwingUtilities;
import visao.*;

public class SalvarJogo{

    public static void salvarJogo() {

		Jogo jogoSalvar = Jogo.getInstancia();
		try {

			FileOutputStream arquivoSalvamento = new FileOutputStream("jogoSalvo.ser");
			ObjectOutputStream objetoSalvamento = new ObjectOutputStream(arquivoSalvamento);
			objetoSalvamento.writeObject(jogoSalvar);
			objetoSalvamento.close();

			System.out.printf("salvei\n");
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }

	public static void carregarJogo(Display display)
	{
		Jogo jogoSalvar = Jogo.getInstancia();
		System.out.printf("entrei no método para carregar\n");
		File arquivo = new File("jogoSalvo.ser");
        
        if (!arquivo.exists()) {
            System.err.println("Arquivo de salvamento não encontrado!");
            return;
        }

		try (FileInputStream arquivoCarregado = new FileInputStream(arquivo);
			ObjectInputStream objetoCarregado = new ObjectInputStream(arquivoCarregado))
		{

			Jogo jogoSalvo = (Jogo) objetoCarregado.readObject();
			jogoSalvar.atualizaJogo(jogoSalvo.getEstado(), jogoSalvo.getRodada(), jogoSalvo.getJogada(),
									jogoSalvo.jogadores, jogoSalvo.casas, jogoSalvo.cartas, jogoSalvo.dado1, jogoSalvo.dado2,
									jogoSalvo.banco);
			

			jogoSalvar.setEstado(Estados.JOGAR_DADOS);

			display.atualizaDisplay();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return;
	}
}

