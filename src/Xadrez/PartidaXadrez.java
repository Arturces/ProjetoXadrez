package Xadrez;

import PecasXadrez.Bispo;
import PecasXadrez.Peao;
import PecasXadrez.Rei;
import PecasXadrez.Torre;
import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaXadrez {

    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;
    private boolean check;
    private boolean checkMate;

    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cor.BRANCO;
        configuracaoInicia();
    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
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

    public boolean[][] possiveisMovimentos(PosicaoXadrez origemPosicao) {
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

        if (testeCheck(jogadorAtual)) {
            desfazerMovimento(origem, destino, capturarPeca);
            throw new ExcecaoXadrez("Voce nao pode se colocar em check");
        }
        check = (testeCheck(oponente(jogadorAtual))) ? true : false;

        if (testeCheckMate(oponente(jogadorAtual))) {
            checkMate = true;
        } else {
            proximoTurno();
        }
        return (PecaXadrez) capturarPeca;
    }

    private Peca fazerMovimento(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(origem);
        p.aumentarContagemMovimentos();
        Peca capturarPeca = tabuleiro.removerPeca(destino);
        tabuleiro.colocarPeca(p, destino);
        if (capturarPeca != null) {
            pecasNoTabuleiro.remove(capturarPeca);
            pecasCapturadas.add(capturarPeca);
        }
        return capturarPeca;
    }

    private void desfazerMovimento(Posicao origem, Posicao destino, Peca capturarPeca) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
        p.diminuirContagemMovimentos();
        tabuleiro.colocarPeca(p, origem);
        if (capturarPeca != null) {
            tabuleiro.colocarPeca(capturarPeca, destino);
            pecasCapturadas.remove(capturarPeca);
            pecasNoTabuleiro.remove(capturarPeca);
        }
    }

    private void validarOrigemPosicao(Posicao posicao) {
        if (!tabuleiro.existePeca(posicao)) {
            throw new ExcecaoXadrez("Nao existe peca na posicao de origem");
        }
        if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
            throw new ExcecaoXadrez("A peca escolhida nao e sua");
        }
        if (!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
            throw new ExcecaoXadrez("Nao existe movimentos possiveis para a peca");
        }
    }

    private void validarDestinoPosicao(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).possivelMover(destino)) {
            throw new ExcecaoXadrez("A peca escolhida nao pode mover para o local de destino");
        }
    }

    private void proximoTurno() {
        turno++;
        jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO; //condicional ternaria, se o jogador atual for igual a cor branca entao agora ele vai ser a cor preta caso contrario sera a cor branca.
    }

    private Cor oponente(Cor cor) {
        return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    private PecaXadrez rei(Cor cor) {
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : list) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("Não existe rei da " + cor + " no tabuleiro");
    }

    private boolean testeCheck(Cor cor) {
        Posicao reiPosicao = rei(cor).getPosicaoXadrez().posicionar();
        List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Peca p : pecasOponente) {
            boolean[][] mat = p.possiveisMovimentos();
            if (mat[reiPosicao.getFileira()][reiPosicao.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testeCheckMate(Cor cor) {
        if (!testeCheck(cor)) {
            return false;
        }
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == (cor)).collect(Collectors.toList());
        for (Peca p : list) {
            boolean[][] mat = p.possiveisMovimentos();
            for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {
                    if (mat[i][j]) {
                        Posicao origem = ((PecaXadrez) p).getPosicaoXadrez().posicionar();
                        Posicao destino = new Posicao(i, j);
                        Peca pecaCapturada = fazerMovimento(origem, destino);
                        boolean testCheck = testeCheck(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);
                        if (!testCheck) {
                            return false;
                        }

                    }
                }
            }
        }
        return true;
    }

    private void colocarPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());
        pecasNoTabuleiro.add(peca);
    }

    private void configuracaoInicia() {
        colocarPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
        colocarPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
        colocarPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
        colocarPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocarPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));

        colocarPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocarPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
        colocarPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocarPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
        colocarPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));
    }
    // tabuleiro.colocarPeca(new Rei(tabuleiro,Cor.PRETO), ew Posicao(0, 2));
}
