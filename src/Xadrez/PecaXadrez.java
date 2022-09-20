package Xadrez;

import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {
    private Cor cor;
    private int contarMovimentos;

    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public int getContarMovimentos() {
        return contarMovimentos;
    }

    public void aumentarContagemMovimentos(){
        contarMovimentos++;
    }

    public void diminuirContagemMovimentos(){
        contarMovimentos--;
    }

    public PosicaoXadrez getPosicaoXadrez(){
        return PosicaoXadrez.partirPosicao(posicao);
    }

    protected boolean existePecaOponete(Posicao posicao){
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return  p != null && p.getCor() != cor;



    }

}

