import java.util.ArrayList;
import Jogador.java;

public class Casa {
    private String nome;
    private int index;
    private int numPessoasNaCasa;
    private int coordenadaX;
    private int coordenadaY;
    private ArrayList<Jogador> jogadores;
    private int aVenda;
    private int dono;
    private int precoAluguel;
    private int precoImovel;
    private int tam = 96;

    public Casa(String nome, int index, int coordenadaX, int coordenadaY, int compravel){
        
        this.jogadores = new ArrayList<>(); 
        this.numPessoasNaCasa = 0;
        this.nome = nome;
        this.index = index;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;

        if (compravel == 0){
            this.aVenda = 0;
            this.dono = -1;
            this.precoAluguel = 0;
            this.precoImovel = 0;
        }
    }

    public void Compravel(int aVenda, int dono, int precoAluguel, int precoImovel ){

        this.aVenda = aVenda;
        this.dono = dono;
        this.precoAluguel = precoAluguel;
        this.precoImovel = precoImovel;
    }

    public String getNome(){
        return this.nome;
    }

    public int getIndex(){
        return this.index;
    }

    public int getCoordenadaX(){
        return this.coordenadaX;
    }

    public int getCoordenadaY(){
        return this.coordenadaY;
    }

    public int getNumPessoasNaCasa(){
        return this.numPessoasNaCasa;
    }

    public void adicionarJogador(Jogador jogador) {
        this.jogadores.add(jogador);
        this.numPessoasNaCasa++;
        // Função para o jogador aparecer na tela
    }

    public void removerJogador(Jogador jogador) {
        this.jogadores.remove(jogador);
        if (this.numPessoasNaCasa > 0) {
            this.numPessoasNaCasa--;
        }
        // Resto do código para remover o jogador da tela
    }

    // Método para obter a lista de jogadores
    public ArrayList<Jogador> getJogadores() {
        return this.jogadores;
    }

}

