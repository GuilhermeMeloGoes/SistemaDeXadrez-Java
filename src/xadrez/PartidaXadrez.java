package xadrez;

import TabuleiroJogo.Peca;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import xadrez.pecas.*;

import java.util.ArrayList;
import java.util.List;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;
    private int turno;
    private Cor jogadorAtual;
    private boolean check;
    private boolean checkMate;
    private PecaXadrez promocao;

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

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public PecaXadrez getPromocao() {
        return promocao;
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

        PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.peca(destino);

        // Movimento Especial Promoção
        promocao = null;
        if (pecaMovida instanceof Peao) {
            if (pecaMovida.getCor() == Cor.BRANCO && destino.getLinha() == 0 || pecaMovida.getCor() == Cor.PRETO && destino.getLinha() == 7) {
                promocao = (PecaXadrez) tabuleiro.peca(destino);
                promocao = trocaPecaPromocao("r");
            }
        }

        check = testCheck(oponente(jogadorAtual));

        if (testCheckMate(oponente(jogadorAtual))) {
            checkMate = true;
        } else {
            proximoTurno();
        }

        return (PecaXadrez) pecaCapturada;
    }

    public PecaXadrez trocaPecaPromocao(String tipoPeca) {
        if (promocao == null) {
            throw new IllegalStateException("Não tem peça a ser trocada!");
        }
        if (!tipoPeca.equals("B") && !tipoPeca.equals("C") && !tipoPeca.equals("T") && !tipoPeca.equals("r")) {
            return promocao;
        }

        Posicao posi = promocao.getPosicaoXadrez().toPosicao();
        Peca p = tabuleiro.removerPeca(posi);
        pecasTabuleiro.remove(p);

        PecaXadrez novaPeca = novaPeca(tipoPeca, promocao.getCor());

        tabuleiro.localDaPeca(novaPeca, posi);
        pecasTabuleiro.add(novaPeca);

        return novaPeca;
    }

    private PecaXadrez novaPeca(String tipo, Cor cor) {
        if (tipo.equals("B")) return new Bispo(tabuleiro, cor);
        if (tipo.equals("C")) return new Cavalo(tabuleiro, cor);
        if (tipo.equals("r")) return new Rainha(tabuleiro, cor);
        return new Torre(tabuleiro, cor);
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
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
        p.retirarMovimento();
        tabuleiro.localDaPeca(p, origem);

        if (pecaCapturada != null) {
            tabuleiro.localDaPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasTabuleiro.add(pecaCapturada);
        }

        // Movimento especial roque lado rei
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
            tabuleiro.localDaPeca(torre, origemTorre);
            torre.retirarMovimento();
        }

        // Movimento especial roque lado da rainha
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
            tabuleiro.localDaPeca(torre, origemTorre);
            torre.retirarMovimento();
        }

    }

    private Peca movimentoPeca(Posicao origem, Posicao destino) {
        PecaXadrez pecaRemover = (PecaXadrez) tabuleiro.removerPeca(origem);
        pecaRemover.acrescentarMovimento();
        Peca pecaCapturada = tabuleiro.removerPeca(destino);

        tabuleiro.localDaPeca(pecaRemover, destino);

        if (pecaCapturada != null) {
            pecasTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        // Movimento especial roque lado rei
        if (pecaRemover instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
            tabuleiro.localDaPeca(torre, destinoTorre);
            torre.acrescentarMovimento();
        }

        // Movimento especial roque lado da rainha
        if (pecaRemover instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
            tabuleiro.localDaPeca(torre, destinoTorre);
            torre.acrescentarMovimento();
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
            if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Cor cor) {
        if (!testCheck(cor)) {
            return false;
        }

        List<Peca> listPeca = pecasTabuleiro.stream().filter(p -> ((PecaXadrez) p).getCor() == cor).toList();
        for (Peca p : listPeca) {
            boolean[][] mat = p.movimentoPossivel();

            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    if (mat[i][j]) {
                        Posicao origem = ((PecaXadrez) p).getPosicaoXadrez().toPosicao();
                        Posicao destino = new Posicao(i, j);
                        Peca pecaCapturada = movimentoPeca(origem, destino);
                        boolean testCheck = testCheck(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);

                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }

        }
        return true;
    }

    private void localPecaNova(char coluna, int linha, PecaXadrez pecaXadrez) {
        tabuleiro.localDaPeca(pecaXadrez, new PosicaoXadrez(coluna, linha).toPosicao());
        pecasTabuleiro.add(pecaXadrez);
    }

    private void iniciarPartida() {

        localPecaNova('a', 1, new Torre(tabuleiro, Cor.BRANCO));
        localPecaNova('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        localPecaNova('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        localPecaNova('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
        localPecaNova('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
        localPecaNova('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        localPecaNova('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
        localPecaNova('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
        localPecaNova('a', 2, new Peao(tabuleiro, Cor.BRANCO));
        localPecaNova('b', 2, new Peao(tabuleiro, Cor.BRANCO));
        localPecaNova('c', 2, new Peao(tabuleiro, Cor.BRANCO));
        localPecaNova('d', 2, new Peao(tabuleiro, Cor.BRANCO));
        localPecaNova('e', 2, new Peao(tabuleiro, Cor.BRANCO));
        localPecaNova('f', 2, new Peao(tabuleiro, Cor.BRANCO));
        localPecaNova('g', 2, new Peao(tabuleiro, Cor.BRANCO));
        localPecaNova('h', 2, new Peao(tabuleiro, Cor.BRANCO));


        localPecaNova('a', 8, new Torre(tabuleiro, Cor.PRETO));
        localPecaNova('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
        localPecaNova('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
        localPecaNova('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
        localPecaNova('d', 8, new Rainha(tabuleiro, Cor.PRETO));
        localPecaNova('h', 8, new Torre(tabuleiro, Cor.PRETO));
        localPecaNova('c', 8, new Bispo(tabuleiro, Cor.PRETO));
        localPecaNova('f', 8, new Bispo(tabuleiro, Cor.PRETO));
        localPecaNova('a', 7, new Peao(tabuleiro, Cor.PRETO));
        localPecaNova('b', 7, new Peao(tabuleiro, Cor.PRETO));
        localPecaNova('c', 7, new Peao(tabuleiro, Cor.PRETO));
        localPecaNova('d', 7, new Peao(tabuleiro, Cor.PRETO));
        localPecaNova('e', 7, new Peao(tabuleiro, Cor.PRETO));
        localPecaNova('f', 7, new Peao(tabuleiro, Cor.PRETO));
        localPecaNova('g', 7, new Peao(tabuleiro, Cor.PRETO));
        localPecaNova('h', 7, new Peao(tabuleiro, Cor.PRETO));


    }


}
