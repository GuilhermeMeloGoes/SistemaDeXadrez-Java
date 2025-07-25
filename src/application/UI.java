package application;

import TabuleiroJogo.Posicao;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    static char[] letras = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
        try {
            String s = sc.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));
            return new PosicaoXadrez(coluna, linha);
        } catch (RuntimeException ex) {
            throw new InputMismatchException("Erro ao ler a posição do xadrez! Valores válidos são do a1 ao h8.");
        }
    }

    public static void imprimirPartida(PartidaXadrez partida, List<PecaXadrez> pecasCapturadas) {
        imprimirTabuleiro(partida.getPecasXadrez());
        System.out.println();
        exibirPecasCapturadas(pecasCapturadas);
        System.out.println("\nTurno: " + partida.getTurno());

        if (!partida.getCheckMate()) {
            System.out.println("Aguardando Jogador: " + partida.getJogadorAtual());
            if (partida.getCheck()) {
                System.out.println("Você está em check!");
            }
        } else {
            System.out.println("CheckMate!");
            System.out.println("Vencedor: " + partida.getJogadorAtual());
        }
    }

    public static void imprimirTabuleiro(PecaXadrez[][] pecas) {
        int i = 0;
        int j = 0;
        for (i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + "  ");
            for (j = 0; j < pecas[i].length; j++) {
                imprimirPeca(pecas[i][j], false);
            }
            System.out.println();
        }

        System.out.print("   ");
        for (i = 0; i < letras.length; i++) {
            System.out.print(letras[i] + "  ");
        }
        System.out.println();
    }

    public static void imprimirTabuleiro(PecaXadrez[][] pecas, boolean[][] matrizMovimentosPossiveis) {
        int i = 0;
        int j = 0;
        for (i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + "  ");
            for (j = 0; j < pecas[i].length; j++) {
                imprimirPeca(pecas[i][j], matrizMovimentosPossiveis[i][j]);
            }
            System.out.println();
        }

        System.out.print("   ");
        for (i = 0; i < letras.length; i++) {
            System.out.print(letras[i] + "  ");
        }
        System.out.println();
    }

    private static void imprimirPeca(PecaXadrez peca, boolean background) {

        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }

        if (peca == null) {
            System.out.print("- " + ANSI_RESET);
        } else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }

        System.out.print(" ");

    }

    public static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void exibirPecasCapturadas(List<PecaXadrez> pecas) {
        List<PecaXadrez> brancas = pecas.stream().filter(p -> p.getCor() == Cor.BRANCO).collect(Collectors.toList());
        List<PecaXadrez> pretas = pecas.stream().filter(p -> p.getCor() == Cor.PRETO).collect(Collectors.toList());

        System.out.println("Peças capturadas: ");
        System.out.print("Brancas: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(brancas.toArray()));
        System.out.print(ANSI_RESET);

        System.out.print("Pretas: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(pretas.toArray()));
        System.out.print(ANSI_RESET);
    }


}
