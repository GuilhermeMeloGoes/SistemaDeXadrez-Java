package xadrez.pecas;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    private PartidaXadrez partidaXadrez;

    public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
    }

    @Override
    public String toString() {
        return "R ";
    }

    public boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    private boolean testTorreRoque(Posicao posicao) {
        PecaXadrez peca = (PecaXadrez) getTabuleiro().peca(posicao);
        return peca != null && peca.getCor() == getCor() && peca.getContMovimento() == getContMovimento() && peca instanceof Torre;
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

        // Movimento especial Roque
        if (getContMovimento() == 0 && !partidaXadrez.getCheck()) {
            // Movimento especial Roque lado do rei
            Posicao posicaoTorre1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (testTorreRoque(posicaoTorre1)) {
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);

                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
                    matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
                }
            }

            // Movimento especial Roque do lado da rainha
            Posicao posicaoTorre2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if (testTorreRoque(posicaoTorre2)) {
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);

                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
                    matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
                }
            }

        }

        return matriz;
    }
}
