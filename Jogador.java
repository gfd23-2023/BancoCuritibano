package main;

import java.io.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;     //para o vetor de propriedades

public class Jogador {
  private String nome;
  private ImageIcon foto;
  private double dinheiro;
  private int casaAtual;
  private boolean falido;
<<<<<<< HEAD
  private boolean preso;
//  private ArrayList<> players = new ArrayList<>();
=======
  private boolean naCadeia;
  private int rodadasNaCadeia;
  private boolean esperando;
  private int rodadasEsperando;
>>>>>>> 1b73b5571b578e962bb26dde8ef2c51681d76502

  /* Construtores */
  Jogador() {};

  Jogador(String nome, ImageIcon foto) {
    this.nome = nome;
    this.foto = foto;
    this.dinheiro = (double) 2000;
    this.casaAtual = 0;
    this.falido = false;
<<<<<<< HEAD
	this.preso = false;
=======
    this.naCadeia = false;
    this.rodadasNaCadeia = 0;
    this.esperando = false;
    this.rodadasEsperando = 0;
>>>>>>> 1b73b5571b578e962bb26dde8ef2c51681d76502
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

<<<<<<< HEAD
} 
=======
  public boolean estaNaCadeia() {
    return naCadeia;
  }

  public void setNaCadeia(boolean naCadeia) {
    this.naCadeia = naCadeia;
  }

  public int getRodadasNaCadeia() {
    return rodadasNaCadeia;
  }

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
>>>>>>> 1b73b5571b578e962bb26dde8ef2c51681d76502
