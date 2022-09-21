package PecasXadrez;

import Xadrez.Cor;
import Xadrez.PecaXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public class Cavalo extends PecaXadrez {

    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "C";
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //Pra cima
        p.setValor(posicao.getFileira() - 1, posicao.getColuna( ) -2);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Pra baixo
        p.setValor(posicao.getFileira() - 2, posicao.getColuna()-1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Pra esquerda
        p.setValor(posicao.getFileira() -2, posicao.getColuna() +1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }
        //Pra direito
        p.setValor(posicao.getFileira()-1, posicao.getColuna() + 2);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }
        //Noroeste
        p.setValor(posicao.getFileira() +1, posicao.getColuna() +2);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Nordeste
        p.setValor(posicao.getFileira() +2, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Pra Sudoeste
        p.setValor(posicao.getFileira() + 2, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }
        //Pra Sudeste
        p.setValor(posicao.getFileira() + 1, posicao.getColuna() - 2);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        return mat;
    }
}
