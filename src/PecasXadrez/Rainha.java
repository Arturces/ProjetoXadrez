package PecasXadrez;

import Xadrez.Cor;
import Xadrez.PecaXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public class Rainha extends PecaXadrez {

    public Rainha(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);

        //Pra cima
        p.setValor(posicao.getFileira() - 1, posicao.getColuna());
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
            p.setFileira(p.getFileira() - 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Pra Esquerda
        p.setValor(posicao.getFileira(), posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Pra Direita
        p.setValor(posicao.getFileira(), posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Pra baixo
        p.setValor(posicao.getFileira() + 1, posicao.getColuna());
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
            p.setFileira(p.getFileira() + 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //NOROESTE
        p.setValor(posicao.getFileira() - 1, posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
            p.setValor(p.getFileira() - 1, p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Nordeste
        p.setValor(posicao.getFileira() - 1, posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
            p.setValor(p.getFileira() - 1, p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Sudeste
        p.setValor(posicao.getFileira() + 1, posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
            p.setValor(p.getFileira() + 1, p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }
        //SUDOESTE
        p.setValor(posicao.getFileira() + 1, posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
            p.setValor(p.getFileira() + 1, p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }
        return mat;
    }

}
