package jogotabuleiro;

public abstract class Peca {

    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        posicao = null;
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public abstract boolean[][] possiveisMovimentos();

    public boolean possivelMover(Posicao posicao) { //metodo q faz gancho com o metodo subclasse
        return possiveisMovimentos()[posicao.getFileira()][posicao.getFileira()];
    }

    public boolean existeAlgumMovimentoPossivel() {
        boolean[][] mat = possiveisMovimentos();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}