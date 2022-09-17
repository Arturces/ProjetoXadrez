package jogotabuleiro;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas < 1) { //excecao para nao criar tabuleiro com linhas e colunas menos de 0
            throw new ExcecaoTabuleiro("Erro ao criar tabuleiro: É necessário que haja ao menos 1 linha e 1 coluna ");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca peca(int linhas, int colunas) {
        if(!existePosicao(linhas, colunas)){
            throw new ExcecaoTabuleiro("Posição não existe no tabuleiro ");
        }
        return pecas[linhas][colunas];
    }

    public Peca peca(Posicao posicao) {
        if(!existePosicao(posicao)){
            throw new ExcecaoTabuleiro("Posição não existe no tabuleiro ");
        }
        return pecas[posicao.getFileira()][posicao.getColuna()];
    }

    public void colocarPeca(Peca peca, Posicao posicao) {
        if (existePeca(posicao)){
            throw new ExcecaoTabuleiro("Já exise uma peça nesta posição ");
        }
        pecas[posicao.getFileira()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }

    public Peca removerPeca(Posicao posicao){ // metodo para remover Peca, com retorno da peca
        if(!existePosicao(posicao)){
            throw new ExcecaoTabuleiro("Posição não existe no tabuleiro");
        }
        if (peca(posicao) == null){
            return null;
        }
        Peca aux = peca(posicao);
        aux.posicao = null;
        pecas[posicao.getFileira()][posicao.getColuna()] = null;
        return aux;
    }

    private boolean existePosicao(int linha, int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean existePosicao(Posicao posicao) {
        return existePosicao(posicao.getFileira(), posicao.getColuna());
    }

    public boolean existePeca(Posicao posicao) {
        if(!existePosicao(posicao)){
            throw new ExcecaoTabuleiro("Posição não existe no tabuleiro ");
        }
        return peca(posicao) != null;
    }
}

