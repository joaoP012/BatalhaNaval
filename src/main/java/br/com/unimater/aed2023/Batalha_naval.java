package br.com.unimater.aed2023;

import java.util.Random;
import java.util.Scanner;

public class Batalha_naval {
    public static void main(String[] args) {
        int nov = 1;
        int partidas = 0;
        int pontosTotal = 0;
        Scanner scanner = new Scanner(System.in);

        while (nov == 1) {
            int tabela[][] = new int[10][10];
            int navios[][] = new int[10][10];
            int jogada[] = new int[2];
            int acerto = 0;
            int tentativas = 100;
            int pontos = 100;

            // JOGADORES
            partidas++;
            String nomeJogador;
            System.out.println("Informe o nome do Player:");
            nomeJogador = scanner.nextLine();

            System.out.println("1-Jogar\n2-Como Jogar?");
            System.out.println("Digite:");
            int inicio = scanner.nextInt();

            if (inicio == 2) {
                System.out.println("A cada rodada, o jogador informa o 'quadrado' referente à sua escolha. As coordenadas são separadas da seguinte forma: "
                        + "são 10 linhas e 10 colunas, ambas numeradas de 1 a 10. Para selecionar sua jogada, basta informar qual linha e qual coluna jogará na rodada. "
                        + "Os pontos são calculados da seguinte forma: (total de possíveis jogadas, que é 100, multiplicado pela quantidade de barcos) dividido pela "
                        + "quantidade de jogadas que o jogador precisou.");
            }

            inicializarTabela(tabela);
            inicializarNavios(navios);

            while (acerto != 6 && tentativas > 0) {
                imprimirTabela(tabela);
                fazerJogada(jogada, tabela, scanner);

                tentativas--;
                pontos--;
                acerto = acertou(jogada, navios, tabela, acerto);
            }

            System.out.println("\n\n\nJogo terminado. Você acertou os 6 navios em " + (100 - tentativas) + " tentativas");
            int pontuacao = (pontos * (100 - tentativas)) / 100;
            pontosTotal += pontuacao;
            System.out.println("\n\n\nSua pontuação foi: " + pontuacao + "%");

            if (partidas > 0) {
                int mediaGeral = pontosTotal / partidas;
                System.out.println("Média de Pontos: " + mediaGeral);
            } else {
                System.out.println("Nenhuma partida jogada ainda.");
            }

            System.out.println("Jogar Novamente? \n1-SIM\n2-NÃO");
            nov = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner
        }

        scanner.close();
    }

    public static void inicializarTabela(int tabela[][]) {
        for (int linha = 0; linha < 10; linha++) {
            for (int coluna = 0; coluna < 10; coluna++) {
                tabela[linha][coluna] = -1;
            }
        }
    }

    public static void imprimirTabela(int tabela[][]) {
    System.out.print("\n****************************\n");
    System.out.print("\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10\n");

    for (int linha = 0; linha < 10; linha++) {
        System.out.print((linha + 1) + "");
        for (int coluna = 0; coluna < 10; coluna++) {
            if (tabela[linha][coluna] == -1) {
                System.out.print("\t" + "~");
            } else if (tabela[linha][coluna] == 0) {
                System.out.print("\t" + "N");
            } else if (tabela[linha][coluna] == 1) {
                System.out.print("\t" + "O");
            } else if (tabela[linha][coluna] == 2) {
                System.out.print("\t" + "*");
            }
        }
        System.out.print("\n");
    }
    System.out.print("\n****************************\n");
}

    public static void inicializarNavios(int navios[][]) {
    Random rand = new Random();
    int a = 0;
    while (a < 6) {
        int k = rand.nextInt(10);
        int l = rand.nextInt(10);
        if (navios[k][l] != 1) {
            navios[k][l] = 1;
            a++;
        }
    }
    
    int b = 0;
    while (b < 2) {
        int k = rand.nextInt(9);
        int l = rand.nextInt(10);
        if (navios[k][l] != 1 && navios[k+1][l] != 1) {
            navios[k][l] = 1;
            navios[k+1][l] = 1;
            b++;
        }
    }
}

    public static void fazerJogada(int jogada[], int tabela[][], Scanner scanner) {
        System.out.println("Linha:");
        jogada[0] = scanner.nextInt() - 1;
        System.out.println("Coluna:");
        jogada[1] = scanner.nextInt() - 1;

        tabela[jogada[0]][jogada[1]] = 0;
    }

    public static int acertou(int jogada[], int navios[][], int tabela[][], int acerto) {
        if (navios[jogada[0]][jogada[1]] == 1) {
            System.out.printf("********\nVocê acertou o tiro (%d,%d)\n********\n", jogada[0] + 1, jogada[1] + 1);
            tabela[jogada[0]][jogada[1]] = 1;
            acerto++;
        }
        return acerto;
    }
}