package PecasXadrez;

import Xadrez.Cor;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public class Rei extends PecaXadrez {

    private PartidaXadrez partidaXadrez;

    public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
    }

    private boolean testTorreCastling(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContarMovimentos() == 0;
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //Pra cima
        p.setValor(posicao.getFileira() - 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Pra baixo
        p.setValor(posicao.getFileira() + 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Pra esquerda
        p.setValor(posicao.getFileira(), posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }
        //Pra direito
        p.setValor(posicao.getFileira(), posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }
        //Noroeste
        p.setValor(posicao.getFileira() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Nordeste
        p.setValor(posicao.getFileira() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        //Pra Sudoeste
        p.setValor(posicao.getFileira() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }
        //Pra Sudeste
        p.setValor(posicao.getFileira() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
            mat[p.getFileira()][p.getColuna()] = true;
        }

        // Movimento especial - Castling
        if (getContarMovimentos() == 0 && !partidaXadrez.getCheck()) {
            // Movimento especial - Castling Rei Torre Pequena
            Posicao posT1 = new Posicao((posicao.getFileira()), posicao.getColuna() + 3);
            if (testTorreCastling(posT1)) {
                Posicao p1 = new Posicao((posicao.getFileira()), posicao.getColuna() + 1);
                Posicao p2 = new Posicao((posicao.getFileira()), posicao.getColuna() + 2);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
                    mat[posicao.getFileira()][posicao.getColuna() + 2] = true;
                }
            }

            // Movimento especial - Castling Rainha Torre Pequena
            Posicao posT2 = new Posicao((posicao.getFileira()), posicao.getColuna() - 4);
            if (testTorreCastling(posT2)) {
                Posicao p1 = new Posicao((posicao.getFileira()), posicao.getColuna() - 1);
                Posicao p2 = new Posicao((posicao.getFileira()), posicao.getColuna() - 2);
                Posicao p3 = new Posicao((posicao.getFileira()), posicao.getColuna() - 3);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
                    mat[posicao.getFileira()][posicao.getColuna() - 2] = true;
                }
            }
        }

        return mat;
    }
}
