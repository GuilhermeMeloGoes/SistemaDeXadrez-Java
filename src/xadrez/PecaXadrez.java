package xadrez;

import TabuleiroJogo.Peca;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;

public abstract class PecaXadrez extends Peca {

    private Cor cor;
    private int contMovimento;

    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public int getContMovimento() {
        return contMovimento;
    }

    public void acrescentarMovimento() {
        contMovimento++;
    }

    public void retirarMovimento() {
        contMovimento--;
    }

    public PosicaoXadrez getPosicaoXadrez() {
        return PosicaoXadrez.toPosicaoXadrez(posicao);
    }

    protected boolean eUmaPecaAdversaria(Posicao posicao) {
        PecaXadrez peca = (PecaXadrez) getTabuleiro().peca(posicao);
        return peca != null && peca.getCor() != cor;
    }

}
