package modelo.casas;
import modelo.*;

public class CasaGrafica extends Casa {
    private int coordenadaX;
    private int coordenadaY;

    public CasaGrafica(String nome, int index, int coordenadaX, int coordenadaY) {
        this.nome = nome; // Atributo herdado de Casa (deve ser protected na classe base)
        this.posicao = index; // Atributo herdado de Casa (deve ser protected na classe base)
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public int getCoordenadaX() {
        return this.coordenadaX;
    }

    public int getCoordenadaY() {
        return this.coordenadaY;
    }

    /*public int getNumPessoasNaCasa() {
        return this.numPessoasNaCasa;
    }

    public void adicionarJogador(int IDjogador) {
        this.IdJogadores.add(IDjogador);
        this.numPessoasNaCasa++;
    }

    public void removerJogador(int IDjogador) {
        this.IdJogadores.remove(Integer.valueOf(IDjogador)); // Remoção correta de Integer
        if (this.numPessoasNaCasa > 0) {
            this.numPessoasNaCasa--;
        }
    }

    public ArrayList<Integer> getJogadores() {
        return this.IdJogadores;
    }*/

    //implemetação vazia pois CasaPosicao não usa esse método abstrato
    @Override
    public void acaoChegada(Jogador origem){
        //implementacao vazia pois essa classe apesar de extender a classe nao faz nada com os seu método abtrato 
    }
}
