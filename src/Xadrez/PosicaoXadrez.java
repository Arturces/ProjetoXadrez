package Xadrez;

import jogotabuleiro.Posicao;

public class PosicaoXadrez {

    private char coluna;
    private int linha;

    public PosicaoXadrez(char coluna, int linha) {
        if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
            throw new ExcecaoXadrez("Erro na posição de xadrez. Valores validos a1 até h8");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    protected Posicao posicionar() {
        return new Posicao(8 - linha, coluna - 'a'); //posicionar as pecas dentro do tabuleiro em relacao a matriz das linhas e das colunas.
    }

    protected static PosicaoXadrez partirPosicao(Posicao posicao) {
        return new PosicaoXadrez((char) ('a' - posicao.getColuna()), 8 - posicao.getFileira());
    }

    @Override
    public String toString() {
        return "" + coluna + linha; //macete para concatenar string usando as aspas
    }
}