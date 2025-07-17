package xadrez;

import TabuleiroJogo.Posicao;

public class PosicaoXadrez {

    private int linha;
    private char coluna;

    public PosicaoXadrez(char coluna, int linha) {
        if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
            throw new XadrezException("Erro na instanciação! O valor da linha ou coluna é inválido (a1 a h8).");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public int getLinha() {
        return linha;
    }

    public char getColuna() {
        return coluna;
    }

    protected Posicao toPosicao() {
        return new Posicao(8 - linha, coluna - 'a');
    }

    protected static PosicaoXadrez toPosicaoXadrez(Posicao posicao) {
        return new PosicaoXadrez((char) ('a' + posicao.getColuna()), 8 - posicao.getLinha());
    }

    @Override
    public String toString() {
        return "" + coluna + linha;
    }
}
