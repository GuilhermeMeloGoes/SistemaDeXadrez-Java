package application;

import xadrez.Cor;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

import java.util.InputMismatchException;
import java.util.Scanner;

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

    public static void imprimirTabuleiro(PecaXadrez[][] pecas) {
        int i = 0;
        int j = 0;
        for (i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + "  ");
            for (j = 0; j < pecas[i].length; j++) {
                imprimirPeca(pecas[i][j]);
            }
            System.out.println();
        }

        System.out.print("   ");
        for (i = 0; i < letras.length; i++) {
            System.out.print(letras[i] + "  ");
        }
        System.out.println();
    }

    private static void imprimirPeca(PecaXadrez peca) {

        if (peca == null) {
            System.out.print("- ");
        } else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }

        System.out.print(" ");

    }


}
