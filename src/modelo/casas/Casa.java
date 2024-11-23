package modelo.casas;


public class Casa {
    protected String nome;
    protected int posicao;
    private int coordenadaX;
    private int coordenadaY;

    public void setCasa(String nome, int index, int coordenadaX, int coordenadaY) {
        this.nome = nome; // Atributo herdado de Casa (deve ser protected na classe base)
        this.posicao = index; // Atributo herdado de Casa (deve ser protected na classe base)
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getPosicao() {
        return posicao;
    }
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    public int getCoordenadaX() {
        return this.coordenadaX;
    }
    public int getCoordenadaY() {
        return this.coordenadaY;
    }
}
