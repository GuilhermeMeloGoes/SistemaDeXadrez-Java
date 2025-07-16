package application;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PartidaXadrez partida = new PartidaXadrez();

        while (true) {
            UI.imprimirTabuleiro(partida.getPecasXadrez());
            System.out.print("\nOrigem: ");
            PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);

            System.out.print("\nDestino: ");
            PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

            PecaXadrez pecaCapturada = partida.movimentoXadrez(origem, destino);
        }

    }
}
