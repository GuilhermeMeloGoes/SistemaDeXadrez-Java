package xadrez.pecas;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    public boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentoPossivel() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posiAux = new Posicao(0, 0);

        posiAux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        posiAux.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        posiAux.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        posiAux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        posiAux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        posiAux.setValores(posicao.getLinha() +2 , posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        posiAux.setValores(posicao.getLinha() + 2, posicao.getColuna() +-1);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        posiAux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        return matriz;
    }

    @Override
    public String toString() {
        return "C ";
    }
}
