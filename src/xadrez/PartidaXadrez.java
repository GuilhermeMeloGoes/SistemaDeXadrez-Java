package xadrez;

import TabuleiroJogo.Peca;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;
    private int turno;
    private Cor jogadorAtual;
    private boolean check;

    private List<Peca> pecasTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cor.BRANCO;
        check = false;
        iniciarPartida();
    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean getcheck() {
        return check;
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

    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
        Posicao posi = posicaoOrigem.toPosicao();
        validacaoPosicaoOrigem(posi);
        return tabuleiro.peca(posi).movimentoPossivel();
    }


    public PecaXadrez movimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
        Posicao origem = posicaoOrigem.toPosicao();
        Posicao destino = posicaoDestino.toPosicao();

        validacaoPosicaoOrigem(origem);
        validacaoPosicaoDestino(origem, destino);

        Peca pecaCapturada = movimentoPeca(origem, destino);

        if (testCheck(jogadorAtual)) {
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("Você não pode se colocar em check!");
        }

        check = testCheck(oponente(jogadorAtual));

        proximoTurno();
        return (PecaXadrez) pecaCapturada;
    }

    private void validacaoPosicaoOrigem(Posicao posicaoOrigem) {
        if (!tabuleiro.eUmaPeca(posicaoOrigem)) {
            throw new XadrezException("Não existe peça na posição de origem!");
        }
        if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicaoOrigem)).getCor()) {
            throw new XadrezException("A peça escolhida não é a sua!");
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

    private void proximoTurno() {
        turno++;
        jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        Peca p = tabuleiro.removerPeca(destino);
        tabuleiro.localDaPeca(p, origem);

        if (pecaCapturada != null) {
            tabuleiro.localDaPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasTabuleiro.add(pecaCapturada);
        }

    }

    private Peca movimentoPeca(Posicao origem, Posicao destino) {
        Peca pecaRemover = tabuleiro.removerPeca(origem);
        Peca pecaCapturada = tabuleiro.removerPeca(destino);

        tabuleiro.localDaPeca(pecaRemover, destino);

        if (pecaCapturada != null) {
            pecasTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        return pecaCapturada;
    }

    private Cor oponente(Cor cor) {
        return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    private PecaXadrez rei(Cor cor) {
        List<Peca> listaPecas = pecasTabuleiro.stream().filter(p -> ((PecaXadrez) p).getCor() == cor).toList();
        for (Peca p : listaPecas) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("Não existe o rei da cor " + cor + " no tabuleiro!");
    }

    private boolean testCheck(Cor cor) {
        Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
        List<Peca> pecasOponente = pecasTabuleiro.stream().filter(p -> ((PecaXadrez) p).getCor() == oponente(cor)).toList();

        for (Peca p : pecasOponente) {
            boolean[][] mat = p.movimentoPossivel();
            if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()] ) {
                return true;
            }
        }
        return false;
    }

    private void localPecaNova(char coluna, int linha, PecaXadrez pecaXadrez) {
        tabuleiro.localDaPeca(pecaXadrez, new PosicaoXadrez(coluna, linha).toPosicao());
        pecasTabuleiro.add(pecaXadrez);
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
