package application;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import xadrez.PartidaXadrez;

public class Programa {
    public static void main(String[] args) {

        PartidaXadrez partida = new PartidaXadrez();

        UI.imprimirTabuleiro(partida.getPecasXadrez());


    }
}
