package TabuleiroJogo;

public class Posicao {

    private int linha;
    private int coluna;

    public Posicao(Integer linha, Integer coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(Integer coluna) {
        this.coluna = coluna;
    }

    public void setValores (int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return linha + ", " + coluna;
    }
}
