package modelo;


public class Dado
{
	private int valor; // valor atual do dado
	private boolean dadoRolado = false;


	// getters e setters
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
		return false;
	}

	public void setDadoRolado (boolean expressao){
		this.dadoRolado = expressao;
	}

	public boolean getDadoRolado (){
		return dadoRolado;
	}
}