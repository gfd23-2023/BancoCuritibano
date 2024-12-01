// JOGADOR
// guarda todos os dados do estado atual do jogador
// maioria dos metodos sao getters e setters dos atributos

package modelo;
import javax.swing.ImageIcon;

public class Jogador {
    private int id;
    private String nome;
    //private ImageIcon foto;
    private double dinheiro;
    private int casaAtual;
    private boolean falido;
    private boolean naCadeia;
    private int rodadasNaCadeia;
    private boolean esperando;
    private int rodadasEsperando;

    // Construtor 
    public Jogador(int id, String nome) {
        this.id = id;
        this.nome = nome;
        //this.foto = foto;
        this.dinheiro = (double) 2000;
        this.casaAtual = 0;
        this.falido = false;
        this.naCadeia = false;
        this.rodadasNaCadeia = 0;
        this.esperando = false;
        this.rodadasEsperando = 0;
    }
    
    // Getters e Setters 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
/*
    public ImageIcon getFoto() {
        return foto;
    }

    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }
*/
    public double getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(double dinheiro) {
        this.dinheiro = dinheiro;
    }

    public int getCasa() {
        return casaAtual;
    }

    public void setCasa(int casaNova, int ultimaCasa) {
        // Se a casa ultrapassa o limite superior do tabuleiro, roda para 0
        if (casaNova > ultimaCasa) {
            this.casaAtual = casaNova % (ultimaCasa + 1);
        } else if (casaNova < 0) { // Se a casa ultrapassa o limite inferior do tabuleiro, roda para o limite superior
            this.casaAtual = (ultimaCasa + casaNova + 1);
        } else { // Se está dentro do intervalo, só atribui
            this.casaAtual = casaNova;
        }
    }

    public boolean ehFalido() {
        return falido;
    }

    public void setFalido() {
        falido = true;
    }

    public boolean estaNaCadeia() {
        return naCadeia;
    }

    public void setNaCadeia(boolean naCadeia) {
        this.naCadeia = naCadeia;
    }

    public int getRodadasNaCadeia() {
        return rodadasNaCadeia;
    }

    // Se zerarOuAumentar for 0, zera o inteiro rodadasNaCadeia. Se for 1, incrementa 1 rodada.
    public void setRodadasNaCadeia(int zerarOuAumentar) {
        if (zerarOuAumentar == 0) {
            rodadasNaCadeia = 0;
        } else if (zerarOuAumentar == 1) {
            rodadasNaCadeia++;
        } else {
            System.out.println("Erro. Código zerarOuAumentar inválido.");
        }
    }

    public boolean estaEsperando() {
        return esperando;
    }

    public void setEsperando(boolean esperando) {
        this.esperando = esperando;
    }

    public int getRodadasEsperando() {
        return rodadasEsperando;
    }

    // Se zerarOuAumentar for 0, zera o inteiro rodadasEsperando. Se for 1, incrementa 1 rodada.
    public void setRodadasEsperando(int zerarOuAumentar) {
        if (zerarOuAumentar == 0) {
            rodadasEsperando = 0;
        } else if (zerarOuAumentar == 1) {
            rodadasEsperando++;
        } else {
            System.out.println("Erro. Código zerarOuAumentar inválido.");
        }
    }
}
