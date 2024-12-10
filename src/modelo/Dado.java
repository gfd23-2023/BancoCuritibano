package modelo;

import java.io.Serializable;

public class Dado implements Serializable
{
	private int valor; // valor atual do dado

	// construtor
	public Dado() {
		this.jogaDados(); // inicia com um valor aleatorio
	}

	// getter
	public int getValor() {
		return valor;
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
