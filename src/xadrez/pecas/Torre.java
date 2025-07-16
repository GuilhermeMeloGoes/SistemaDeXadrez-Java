package xadrez.pecas;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {
    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "T ";
    }

    @Override
    public boolean[][] movimentoPossivel() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posiAux = new Posicao(0, 0);

        // Para cima
        posiAux.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().posicaoExistente(posiAux) && !getTabuleiro().eUmaPeca(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            posiAux.setLinha(posiAux.getLinha() - 1);
        }
        if (getTabuleiro().posicaoExistente(posiAux) && eUmaPecaAdversaria(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        // Para Esquerda
        posiAux.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExistente(posiAux) && !getTabuleiro().eUmaPeca(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            posiAux.setColuna(posiAux.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExistente(posiAux) && eUmaPecaAdversaria(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        // Para Direita
        posiAux.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExistente(posiAux) && !getTabuleiro().eUmaPeca(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            posiAux.setColuna(posiAux.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExistente(posiAux) && eUmaPecaAdversaria(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        // Para Baixo
        posiAux.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().posicaoExistente(posiAux) && !getTabuleiro().eUmaPeca(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
            posiAux.setLinha(posiAux.getLinha() + 1);
        }
        if (getTabuleiro().posicaoExistente(posiAux) && eUmaPecaAdversaria(posiAux)) {
            matriz[posiAux.getLinha()][posiAux.getColuna()] = true;
        }

        return matriz;
    }
}
