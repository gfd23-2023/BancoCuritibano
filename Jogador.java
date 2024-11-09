import java.io.*;

public class Jogador {
  private String nome;
  private String foto;
  private double dinheiro;
  private int casaAtual;
  private boolean falido;

  /* Construtores */
  Jogador() {};

  Jogador(String nome, String foto) {
    this.nome = nome;
    this.foto = foto;
    this.dinheiro = (double) 2000;
    this.casaAtual = 0;
    this.falido = false;
  }

  /* Getters e Setters */
  String getNome() {
    return nome;
  }

  void setNome(String nome) {
    this.nome = nome;
  }

  String getFoto() {
    return foto;
  }

  void setFoto(String foto) {
    this.foto = foto;
  }

  double getDinheiro() {
    return dinheiro;
  }

  void setDinheiro(double dinheiro) {
    this.dinheiro = dinheiro;
  }

  int getCasa() {
    return casaAtual;
  }

  void setCasa(int casaNova) {
    this.casaAtual = casaNova;
  }

  boolean ehFalido() {
    return falido;
  }

  void setFalido() {
    falido = true;
  }

} 