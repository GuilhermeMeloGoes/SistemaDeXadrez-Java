package xadrez.pecas;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Rei extends PecaXadrez {

    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "R ";
    }

    public boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentoPossivel() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posiAux = new Posicao(0, 0);

        // Para cima
        posiAux.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        // Para Esquerda
        posiAux.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        // Para Direita
        posiAux.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        // Para Baixo
        posiAux.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        // Para a diagonal cima direita
        posiAux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        // Para a diagonal cima esquerda
        posiAux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        // Para a diagonal baixo direita
        posiAux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        // Para a diagonal baixo esquerda
        posiAux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(posiAux) && podeMover(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        return matriz;
    }
}
