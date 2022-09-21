package PecasXadrez;

import Xadrez.Cor;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public class Peao extends PecaXadrez {

    private PartidaXadrez partidaXadrez;

    public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
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

            // Movimento Especial en Passant Branco
            if (posicao.getFileira() == 3) {
                Posicao esquerda = new Posicao(posicao.getFileira(), posicao.getColuna() - 1);

                if (getTabuleiro().existePosicao(esquerda) && existePecaOponete(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassant()) {
                    mat[esquerda.getFileira() - 1][esquerda.getColuna()] = true;
                }
                // Movimento Especial en Passant Branco
                Posicao direita = new Posicao(posicao.getFileira(), posicao.getColuna() - 1);
                if (getTabuleiro().existePosicao(direita) && existePecaOponete(direita) && getTabuleiro().peca(direita) == partidaXadrez.getEnPassant()) {
                    mat[direita.getFileira() - 1][direita.getColuna()] = true;
                }
            }

        } else {

            p.setValor(posicao.getFileira() + 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)) {
                mat[p.getFileira()][p.getColuna()] = true;
            }
            p.setValor(posicao.getFileira() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getFileira() + 1, posicao.getColuna());
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

            // Movimento Especial en Passant Branco
            if (posicao.getFileira() == 4) {
                Posicao esquerda = new Posicao(posicao.getFileira(), posicao.getColuna() - 1);
                if (getTabuleiro().existePosicao(esquerda) && existePecaOponete(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassant()) {
                    mat[esquerda.getFileira() + 1][esquerda.getColuna()] = true;
                }
                // Movimento Especial en Passant Branco
                Posicao direita = new Posicao(posicao.getFileira(), posicao.getColuna() - 1);
                if (getTabuleiro().existePosicao(direita) && existePecaOponete(direita) && getTabuleiro().peca(direita) == partidaXadrez.getEnPassant()) {
                    mat[direita.getFileira() + 1][direita.getColuna()] = true;
                }
            }
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}






