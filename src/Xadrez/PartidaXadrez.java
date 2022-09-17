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
                mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j); // downcast para relacionar que a classe utilizada das Pecas é a classe PecasXadrez
            }
        }
        return mat; //retono da matriz de peças da matriz de xadrez.
    }
        private void colocarPeca(char coluna, int linha, PecaXadrez peca){
        tabuleiro.colocarPeca(peca,new PosicaoXadrez(coluna, linha).posicionar());
        }


    private void configuracaoInicia(){
        colocarPeca('b', 6, new Torre(tabuleiro,Cor.BRANCO));
        colocarPeca('e', 8, new Rei(tabuleiro,Cor.BRANCO));
        colocarPeca('e', 1, new Rei(tabuleiro,Cor.PRETO));

       // tabuleiro.colocarPeca(new Rei(tabuleiro,Cor.PRETO), new Posicao(0, 2));
    }
}
