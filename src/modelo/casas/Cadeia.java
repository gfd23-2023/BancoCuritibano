package modelo.casas;
import java.util.Scanner;
import modelo.Jogador;

public class Cadeia extends Casa {
    void acaoChegada(Jogador origem) {
        origem.setNaCadeia(true);
    }

    void tentativaSair(Jogador origem) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        origem.setRodadasNaCadeia(1);

        if (origem.getRodadasNaCadeia() == 1) {

            System.out.println("Você pode tentar sair da prisão: ");
            System.out.println("1 - Tentar pagar 50 reais para sair (Dinheiro disponível: " + origem.getDinheiro() + ")");
            System.out.println("2 - Usar carta sair da prisao");
            System.out.println("3 - Tentar tirar dupla nos dados");

            opcao = scanner.nextInt();  
            if (opcao == 1) {
                // Lógica para pagar 50 reais
            } else if (opcao == 2) {
                // Verificar lista de cartas do jogador. Se tiver "Sair da prisao", remove a carta e tira ele da prisao.
            } else if (opcao == 3) {
                // Lógica para sortear dois dados (Passar dados como parâmetro (?))
            } else {
                System.out.println("Opção invalida.");
            }

        } else if (origem.getRodadasNaCadeia() == 2) {

            System.out.println("Você pode tentar sair da prisão: ");
            System.out.println("1 - Tentar pagar 50 reais para sair (Dinheiro disponível: " + origem.getDinheiro() + ")");
            System.out.println("2 - Tentar tirar dupla nos dados");

            opcao = scanner.nextInt();  
            if (opcao == 1) {
                // Lógica para pagar 50 reais (verificar dinheiro e, se sair, mudar estado naCadeia e zerar rodadasNaCadeia)
            } else if (opcao == 2) {
                // Verificar lista de cartas do jogador. Se tiver "Sair da prisao", remove a carta e tira ele da prisao.
            } else {
                System.out.println("Opção invalida");
            }

        } else if (origem.getRodadasNaCadeia() == 3) {

            System.out.println("Você pode tentar sair da prisão: ");
            System.out.println("1 - Tentar tirar dupla nos dados");

            opcao = scanner.nextInt();  
            if (opcao == 1) {
                // Lógica para sortear dois dados (Passar dados como parâmetro (?))
            } else {
                System.out.println("Opção invalida.");
            }

        }

        scanner.close();
    }

}
