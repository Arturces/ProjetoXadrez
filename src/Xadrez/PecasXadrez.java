package Xadrez;

import jogotabuleiro.Pecas;
import jogotabuleiro.Tabuleiro;

public class PecasXadrez extends Pecas {
    private Cor cor;

    public PecasXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

}

