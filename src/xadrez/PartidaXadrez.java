package xadrez;

import TabuleiroJogo.Peca;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        iniciarPartida();
    }

    public PecaXadrez[][] getPecasXadrez() {
        PecaXadrez[][] matPeca = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                matPeca[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }

        return matPeca;
    }

    private void iniciarPartida() {
        tabuleiro.localDaPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(2, 1));
        tabuleiro.localDaPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(0, 4));
        tabuleiro.localDaPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(7, 4));
    }

}
