package Xadrez;

import PecasXadrez.Rei;
import PecasXadrez.Torre;
import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class PartidaXadrez {

    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro  tabuleiro;

    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cor.BRANCO;
        configuracaoInicia();
    }

    public int getTurno(){
        return turno;
    }
    public Cor getJogadorAtual(){
        return jogadorAtual;
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

    public boolean[][] possiveisMovimentos(PosicaoXadrez origemPosicao){
        Posicao posicao = origemPosicao.posicionar();
        validarOrigemPosicao(posicao);
        return tabuleiro.peca(posicao).possiveisMovimentos();
    }

    public PecaXadrez moverPeca(PosicaoXadrez origemPosicao, PosicaoXadrez destinoPosicao) {
        Posicao origem = origemPosicao.posicionar();
        Posicao destino = destinoPosicao.posicionar();
        validarOrigemPosicao(origem);
        validarDestinoPosicao(origem, destino);
        Peca capturarPeca = fazerMovimento(origem, destino);
        proximoTurno();
        return (PecaXadrez) capturarPeca;
    }

    private Peca fazerMovimento(Posicao origem, Posicao destino) {
        Peca p = tabuleiro.removerPeca(origem);
        Peca capturarPeca = tabuleiro.removerPeca(destino);
        tabuleiro.colocarPeca(p, destino);

        if(capturarPeca != null){
            pecasNoTabuleiro.remove(capturarPeca);
            pecasCapturadas.add(capturarPeca);
        }
        return capturarPeca;
    }

    private void validarOrigemPosicao(Posicao posicao) {
        if (!tabuleiro.existePeca(posicao)) {
            throw new ExcecaoXadrez("Nao existe peca na posicao de origem");
        }
        if(jogadorAtual !=((PecaXadrez) tabuleiro.peca(posicao)).getCor()){
            throw new ExcecaoXadrez("A peca escolhida nao e sua");
        }
        if (!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
            throw new ExcecaoXadrez("Nao existe movimentos possiveis para a peca");
        }
    }

    private void  validarDestinoPosicao(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).possivelMover(destino)) {
            throw new ExcecaoXadrez("A peca escolhida nao pode mover para o local de destino");
        }
    }
    private void proximoTurno(){
        turno++;
        jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO; //condicional ternaria, se o jogador atual for igual a cor branca entao agora ele vai ser a cor preta caso contrario sera a cor branca.

    }

    private void colocarPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());
        pecasNoTabuleiro.add(peca);
    }

    private void configuracaoInicia() {
        colocarPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('d', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));

        colocarPeca('c', 1, new Rei(tabuleiro, Cor.BRANCO));
        colocarPeca('c', 2, new Rei(tabuleiro, Cor.BRANCO));
        colocarPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));
        colocarPeca('d', 2, new Rei(tabuleiro, Cor.BRANCO));
        colocarPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
        colocarPeca('e', 2, new Rei(tabuleiro, Cor.BRANCO));

    }
    // tabuleiro.colocarPeca(new Rei(tabuleiro,Cor.PRETO), new Posicao(0, 2));
}
