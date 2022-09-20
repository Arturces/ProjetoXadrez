package PecasXadrez;

import Xadrez.Cor;
import Xadrez.PecaXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public class Peao extends PecaXadrez {

    public Peao(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        if (getCor().equals(Cor.BRANCO)) {
            p.setValor(posicao.getFileira() - 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
                mat[p.getFileira()][p.getColuna()] = true;
            }
            p.setValor(posicao.getFileira() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getFileira() - 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePeca(p2) && getContarMovimentos() == 0) {
                mat[p.getFileira()][p.getColuna()] = true;
            }
            p.setValor(posicao.getFileira() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
                mat[p.getFileira()][p.getColuna()] = true;
            }
            p.setValor(posicao.getFileira() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
                mat[p.getFileira()][p.getColuna()] = true;
            }

        } else {

            p.setValor(posicao.getFileira() + 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
                mat[p.getFileira()][p.getColuna()] = true;
            }
            p.setValor(posicao.getFileira() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getFileira() - 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePeca(p2) && getContarMovimentos() == 0) {
                mat[p.getFileira()][p.getColuna()] = true;
            }
            p.setValor(posicao.getFileira() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
                mat[p.getFileira()][p.getColuna()] = true;
            }
            p.setValor(posicao.getFileira() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
                mat[p.getFileira()][p.getColuna()] = true;
            }

        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}






