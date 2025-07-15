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
        int linha = 8 - this.linha;
        int coluna = this.coluna - 'a';

        return new Posicao(linha, coluna);
    }

    protected static PosicaoXadrez toPosicaoXadrez (Posicao posicao) {
        int linha =  8 - posicao.getLinha();
        char coluna = (char) ('a' - posicao.getColuna());

        return new PosicaoXadrez(coluna, linha);
    }

    @Override
    public String toString() {
        return "" + coluna + linha;
    }
}
