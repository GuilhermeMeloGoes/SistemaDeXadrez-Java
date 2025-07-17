package xadrez;

import TabuleiroJogo.Peca;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;

public abstract class PecaXadrez extends Peca {

    private Cor cor;

    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public PosicaoXadrez getPosicaoXadrez() {
        return PosicaoXadrez.toPosicaoXadrez(posicao);
    }

    protected boolean eUmaPecaAdversaria(Posicao posicao) {
        PecaXadrez peca = (PecaXadrez) getTabuleiro().peca(posicao);
        return peca != null && peca.getCor() != cor;
    }

}
