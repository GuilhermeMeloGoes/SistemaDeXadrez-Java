package TabuleiroJogo;

public class Tabuleiro {

    private int linhas;
    private int colunas;

    private Peca[][] peca;

    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas < 1) {
            throw new TabuleiroException("Erro criação do tabuleiro: é necessário ter pelo menos 1 linha ou 1 coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        peca = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca peca(int linha, int coluna) {
        if (!posicaoExistente(linha, coluna)) {
            throw new TabuleiroException("Posição fora do tabuleiro!");
        }
        return peca[linha][coluna];
    }

    public Peca peca(Posicao posicao) {
        if (!posicaoExistente(posicao)) {
            throw new TabuleiroException("Posição fora do tabuleiro!");
        }
        return peca[posicao.getLinha()][posicao.getColuna()];
    }

    public void localDaPeca(Peca peca, Posicao posicao) {
        if (eUmaPeca(posicao)) {
            throw new TabuleiroException("Ja existe uma peça nessa posição: " + posicao);
        }
        this.peca[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }

    public Peca removerPeca(Posicao posicao) {
        if (!posicaoExistente(posicao)) {
            throw new TabuleiroException("Posição fora do tabuleiro!");
        }

        if (peca(posicao) == null) {
            return null;
        }

        Peca auxPeca = peca(posicao);
        auxPeca.posicao = null;
        peca[posicao.getLinha()][posicao.getColuna()] = null;

        return auxPeca;

    }

    public boolean posicaoExistente(int linha, int coluna) {
        if (linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas) {
            return true;
        }
        return false;
    }

    public boolean posicaoExistente(Posicao posicao) {
        return posicaoExistente(posicao.getLinha(), posicao.getColuna());
    }

    public boolean eUmaPeca(Posicao posicao) {
        if (!posicaoExistente(posicao)) {
            throw new TabuleiroException("Posição fora do tabuleiro!");
        }
        return peca(posicao) != null;
    }

}
