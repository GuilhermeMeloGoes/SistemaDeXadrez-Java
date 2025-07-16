package application;

import TabuleiroJogo.TabuleiroException;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PartidaXadrez partida = new PartidaXadrez();

        while (true) {
            try {
                UI.limparConsole();
                UI.imprimirPartida(partida);
                System.out.print("\nOrigem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);

                boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);

                UI.limparConsole();
                UI.imprimirTabuleiro(partida.getPecasXadrez(), movimentosPossiveis);

                System.out.print("\nDestino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

                PecaXadrez pecaCapturada = partida.movimentoXadrez(origem, destino);
            } catch (TabuleiroException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

    }
}
