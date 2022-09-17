package Xadrez;

import PecasXadrez.Rei;
import PecasXadrez.Torre;
import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

import java.awt.*;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
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

    public PecaXadrez moverPeca(PosicaoXadrez origemPosicao, PosicaoXadrez destinoPosicao) {
        Posicao origem = origemPosicao.posicionar();
        Posicao destino = destinoPosicao.posicionar();
        validarOrigemPosicao(origem);
        Peca capturarPeca = fazerMovimento(origem, destino);
        return (PecaXadrez) capturarPeca;
    }

    private Peca fazerMovimento(Posicao origem, Posicao destino) {
        Peca p = tabuleiro.removerPeca(origem);
        Peca capturarPeca = tabuleiro.removerPeca(destino);
        tabuleiro.colocarPeca(p, destino);
        return capturarPeca;
    }

    private void validarOrigemPosicao(Posicao posicao) {
        if (!tabuleiro.existePeca(posicao)) {
            throw new ExcecaoXadrez("Não existe peca na posição de origem");
        }
    }

    private void colocarPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());
    }

    private void configuracaoInicia() {
        colocarPeca('b', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
        colocarPeca('a', 3, new Rei(tabuleiro, Cor.PRETO));
        colocarPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));
        colocarPeca('f', 5, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('g', 4, new Rei(tabuleiro, Cor.BRANCO));
        colocarPeca('a', 2, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('b', 7, new Rei(tabuleiro, Cor.BRANCO));
        colocarPeca('c', 8, new Torre(tabuleiro, Cor.BRANCO));
        colocarPeca('d', 2, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('d', 4, new Rei(tabuleiro, Cor.PRETO));

    }
    // tabuleiro.colocarPeca(new Rei(tabuleiro,Cor.PRETO), new Posicao(0, 2));
}
