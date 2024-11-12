import java.io.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;     //para o vetor de propriedades

public class Jogador {
  private String nome;
  private ImageIcon foto;
  private double dinheiro;
  private int casaAtual;
  private boolean falido;
  private boolean preso;
//  private ArrayList<> players = new ArrayList<>();

  /* Construtores */
  Jogador() {};

  Jogador(String nome, ImageIcon foto) {
    this.nome = nome;
    this.foto = foto;
    this.dinheiro = (double) 2000;
    this.casaAtual = 0;
    this.falido = false;
	this.preso = false;
  }

  /* Getters e Setters */
  String getNome() {
    return nome;
  }

  void setNome(String nome) {
    this.nome = nome;
  }

  ImageIcon getFoto() {
    return foto;
  }

  void setFoto(ImageIcon foto) {
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
