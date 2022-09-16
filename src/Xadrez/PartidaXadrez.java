package Xadrez;

import PecasXadrez.Rei;
import PecasXadrez.Torre;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    public PartidaXadrez(){
        tabuleiro = new Tabuleiro(8, 8);
        configuracaoInicia();
    }

    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                mat[i][j] = (PecaXadrez) tabuleiro.pecas(i, j); // downcast para relacionar que a classe utilizada das Pecas é a classe PecasXadrez
            }
        }
        return mat; //retono da matriz de peças da matriz de xadrez.
    }

    private void configuracaoInicia(){
        tabuleiro.colocarPeca(new Torre(tabuleiro,Cor.BRANCO), new Posicao(2, 1));
        tabuleiro.colocarPeca(new Rei(tabuleiro,Cor.PRETO), new Posicao(0, 2));
    }
}
