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

    public boolean[][] movimentosPossiveis(PosicaoXadrez posicao) {
        Posicao posi = posicao.toPosicao();
        validacaoPosicaoOrigem(posi);
        return tabuleiro.peca(posi).movimentoPossivel();
    }

    public PecaXadrez movimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
        Posicao origem = posicaoOrigem.toPosicao();
        Posicao destino = posicaoDestino.toPosicao();

        validacaoPosicaoOrigem(origem);
        validacaoPosicaoDestino(origem, destino);

        Peca pecaCapturada = movimentoPeca(origem, destino);
        return (PecaXadrez) pecaCapturada;
    }

    private void validacaoPosicaoOrigem(Posicao posicaoOrigem) {
        if (!tabuleiro.eUmaPeca(posicaoOrigem)) {
            throw new XadrezException("Não existe peça na posição de origem!");
        }
        if (!tabuleiro.peca(posicaoOrigem).existeMovimentoPossivel()) {
            throw new XadrezException("Não existe movimentos possiveis para a peça escolhida!");
        }
    }

    private void validacaoPosicaoDestino(Posicao posicaoOrigem, Posicao posicaoDestino) {
        if (!tabuleiro.peca(posicaoOrigem).movimentoPossivel(posicaoDestino)) {
            throw new XadrezException("A peça escolhida, não pode ser movida para a posição de destino!");
        }
    }

    private Peca movimentoPeca(Posicao origem, Posicao destino) {
        Peca pecaRemover = tabuleiro.removerPeca(origem);
        Peca pecaCapturada = tabuleiro.removerPeca(destino);

        tabuleiro.localDaPeca(pecaRemover, destino);
        return pecaCapturada;
    }

    private void localPecaNova(char coluna, int linha, PecaXadrez pecaXadrez) {
        tabuleiro.localDaPeca(pecaXadrez, new PosicaoXadrez(coluna, linha).toPosicao());
    }


    private void iniciarPartida() {
        localPecaNova('c', 1, new Torre(tabuleiro, Cor.BRANCO));
        localPecaNova('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        localPecaNova('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        localPecaNova('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        localPecaNova('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        localPecaNova('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        localPecaNova('c', 7, new Torre(tabuleiro, Cor.PRETO));
        localPecaNova('c', 8, new Torre(tabuleiro, Cor.PRETO));
        localPecaNova('d', 7, new Torre(tabuleiro, Cor.PRETO));
        localPecaNova('e', 7, new Torre(tabuleiro, Cor.PRETO));
        localPecaNova('e', 8, new Torre(tabuleiro, Cor.PRETO));
        localPecaNova('d', 8, new Rei(tabuleiro, Cor.PRETO));
    }


}
