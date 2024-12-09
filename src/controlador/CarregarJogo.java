/*package controlador;

import modelo.*;
import modelo.casas.*;
import modelo.cartas.*;
import visao.*;
import java.util.*;
import com.google.gson.Gson;
import java.io.*;

public class CarregarJogo{
    Gson gson = new Gson();

    try(Reader reader = new FileReader("jogoSalvo.json")){
        Jogo jogoCarregado = gson.fromJson(reader, Jogo.class);
        System.out.println()("jogo carregado" + jogoCarregado);
    } catch (IOException e){
        e.printStackTrace();
    }
}
*/