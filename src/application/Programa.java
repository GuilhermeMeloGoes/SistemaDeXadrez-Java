package application;

import TabuleiroJogo.TabuleiroException;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PartidaXadrez partida = new PartidaXadrez();
        List<PecaXadrez> pecasCapturadas = new ArrayList<>();

        while (!partida.getCheckMate()) {
            try {
                UI.limparConsole();
                UI.imprimirPartida(partida, pecasCapturadas);
                System.out.print("\nOrigem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);

                boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);

                UI.limparConsole();
                UI.imprimirTabuleiro(partida.getPecasXadrez(), movimentosPossiveis);

                System.out.print("\nDestino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

                PecaXadrez pecaCapturada = partida.movimentoXadrez(origem, destino);

                if (pecaCapturada != null) {
                    pecasCapturadas.add(pecaCapturada);
                }
            } catch (TabuleiroException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

        UI.limparConsole();
        UI.imprimirPartida(partida, pecasCapturadas);
        UI.imprimirPartida(partida, pecasCapturadas);

    }
}
