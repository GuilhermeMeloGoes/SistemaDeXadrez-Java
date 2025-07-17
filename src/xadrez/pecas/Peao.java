package xadrez.pecas;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    public Peao(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "P ";
    }

    @Override
    public boolean[][] movimentoPossivel() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posiAux = new Posicao(0, 0);

        if (getCor() == Cor.BRANCO) {
            // Para cima 1 casa
            posiAux.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(posiAux) && !getTabuleiro().eUmaPeca(posiAux)) {
                matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            }
            // Para cima 2 casa
            posiAux.setValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p1 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(posiAux) && !getTabuleiro().eUmaPeca(posiAux) && getTabuleiro().posicaoExistente(p1) && !getTabuleiro().eUmaPeca(p1) && getContMovimento() == 0) {
                matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            }
            // Para a diagonal cima esquerda
            posiAux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(posiAux) && eUmaPecaAdversaria(posiAux)) {
                matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            }
            // Para a diagonal cima direita
            posiAux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(posiAux) && eUmaPecaAdversaria(posiAux)) {
                matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            }
        } else {
            // Para cima 1 casa
            posiAux.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(posiAux) && !getTabuleiro().eUmaPeca(posiAux)) {
                matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            }
            // Para cima 2 casa
            posiAux.setValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p1 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(posiAux) && !getTabuleiro().eUmaPeca(posiAux) && getTabuleiro().posicaoExistente(p1) && !getTabuleiro().eUmaPeca(p1) && getContMovimento() == 0) {
                matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            }
            // Para a diagonal cima esquerda
            posiAux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(posiAux) && eUmaPecaAdversaria(posiAux)) {
                matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            }
            // Para a diagonal cima direita
            posiAux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(posiAux) && eUmaPecaAdversaria(posiAux)) {
                matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            }
        }
        return matriz;
    }
}
