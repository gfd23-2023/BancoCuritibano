package controlador;

import java.io.*;

public class SalvarJogo{

    public static void salvarJogo() {
        Jogo jogoSalvar = Jogo.getInstancia();

		try {

			FileOutputStream arquivoSalvamento = new FileOutputStream("/src/controlador/jogoSalvo.ser");
			ObjectOutputStream objetoSalvamento = new ObjectOutputStream(arquivoSalvamento);
			objetoSalvamento.writeObject(jogoSalvar);
			objetoSalvamento.close();
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
}

