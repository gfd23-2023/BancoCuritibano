public abstract class Carta {
  private int index;
  private String nome;
  private String descricao;
 
  public int getIndex() {
    return index;
  }
 
  public void setIndex(int index) {
    this.index = index;
  }
 
  public String getNome() {
    return nome;
  }
 
  public void setNome(String nome) {
    this.nome = nome;
  }
 
  public String getDescricao() {
    return descricao;
  }
 
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  abstract void acao(Jogador origem);
}

