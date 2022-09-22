package Xadrez;

import PecasXadrez.*;
import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaXadrez {

    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;
    private boolean check;
    private boolean checkMate;
    private PecaXadrez enPassant;
    private PecaXadrez promoted;

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

    public PecaXadrez getEnPassant() {
        return enPassant;
    }

    public PecaXadrez getPromoted(){
        return promoted;
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

        PecaXadrez pecaMovida = (PecaXadrez)tabuleiro.peca(destino);

        // Movimento especial promotion
        promoted = null;
        if(pecaMovida instanceof Peca) {
            if ((pecaMovida.getCor() == Cor.BRANCO && destino.getFileira() == 0 || pecaMovida.getCor() == Cor.PRETO && destino.getFileira() == 7)) {
                promoted = (PecaXadrez) tabuleiro.peca(destino);
                promoted = substituirPromotedPeca("Q");
            }
        }

        check = (testeCheck(oponente(jogadorAtual))) ? true : false;

        if (testeCheckMate(oponente(jogadorAtual))) {
            checkMate = true;
        } else {
            proximoTurno();
        }

        // Movimento Especial En pssant
        if (pecaMovida instanceof Peao && (destino.getFileira() == origem.getFileira() - 2 || destino.getFileira() == origem.getFileira() + 2)) {
            enPassant = pecaMovida;
        } else {
            enPassant = null;
        }
        return (PecaXadrez) capturarPeca;
    }

        public PecaXadrez substituirPromotedPeca (String type){
        if(promoted == null){
            throw new IllegalStateException("Não a peca para ser promovida");
        }
        if (!type.equals("B") && !type.equals("C") && !type.equals("Q") & !type.equals("T")){
            throw new InvalidParameterException("Tipo de promocao invalida");
        }
        Posicao pos = promoted.getPosicaoXadrez().posicionar();
        Peca p = tabuleiro.removerPeca(pos);
        pecasNoTabuleiro.remove(p);

        PecaXadrez novaPeca = novaPeca(type, promoted.getCor());
        tabuleiro.colocarPeca(novaPeca, pos);
        pecasNoTabuleiro.add(novaPeca);

        return novaPeca;
        }

        private PecaXadrez novaPeca(String type, Cor cor){
        if (type.equals("B")) return new Bispo(tabuleiro, cor);
        if (type.equals("C")) return new Cavalo(tabuleiro, cor);
        if (type.equals("Q")) return new Rainha(tabuleiro, cor);
        return new Torre(tabuleiro, cor);
        }

    private Peca fazerMovimento(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
        p.aumentarContagemMovimentos();
        Peca capturarPeca = tabuleiro.removerPeca(destino);
        tabuleiro.colocarPeca(p, destino);
        if (capturarPeca != null) {
            pecasNoTabuleiro.remove(capturarPeca);
            pecasCapturadas.add(capturarPeca);
        }
        // Movimento Especial Castlig Rei rook Pequeno
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getFileira(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getFileira(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemT);
            tabuleiro.colocarPeca(torre, destinoT);
            torre.aumentarContagemMovimentos();
        }

        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemT = new Posicao(origem.getFileira(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getFileira(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemT);
            tabuleiro.colocarPeca(torre, destinoT);
            torre.aumentarContagemMovimentos();
        }

        //Movimento especial En Passant
        if (p instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && capturarPeca == null) {
                Posicao peaoPosicao;
                if (p.getCor() == Cor.BRANCO) {
                    peaoPosicao = new Posicao(destino.getFileira() + 1, destino.getColuna());
                } else {
                    peaoPosicao = new Posicao(destino.getFileira() - 1, destino.getColuna());
                }
                capturarPeca = tabuleiro.removerPeca(peaoPosicao);
                pecasCapturadas.add(capturarPeca);
                pecasNoTabuleiro.remove(capturarPeca);
            }

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

        // Movimento Especial Castlig Rei rook Pequeno
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getFileira(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getFileira(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoT);
            tabuleiro.colocarPeca(torre, origemT);
            torre.diminuirContagemMovimentos();
        }

        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemT = new Posicao(origem.getFileira(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getFileira(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoT);
            tabuleiro.colocarPeca(torre, origemT);
            torre.diminuirContagemMovimentos();
        }

        //Movimento especial En Passant
        if (p instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && pecasCapturadas == enPassant) {
                PecaXadrez peao = (PecaXadrez)tabuleiro.removerPeca(destino);
                Posicao peaoPosicao;
                if (p.getCor() == Cor.BRANCO) {
                    peaoPosicao = new Posicao(3, destino.getColuna());
                } else {
                    peaoPosicao = new Posicao(4, destino.getColuna());
                }
                tabuleiro.colocarPeca(peao, peaoPosicao);
            }

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
        colocarPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        colocarPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
        colocarPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
        colocarPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
        colocarPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
        colocarPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        colocarPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));

        colocarPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
        colocarPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocarPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
        colocarPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
        colocarPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocarPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
        colocarPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
    }
    // tabuleiro.colocarPeca(new Rei(tabuleiro,Cor.PRETO), ew Posicao(0, 2));
}
