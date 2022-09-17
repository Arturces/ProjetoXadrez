package PecasXadrez;

import Xadrez.Cor;
import Xadrez.PecaXadrez;
import jogotabuleiro.Tabuleiro;

public class Rei extends PecaXadrez {

    public Rei(Tabuleiro tabuleiro, Cor cor) {

        super(tabuleiro, cor);
    }
    @Override
    public String toString(){
        return "T";
    }


    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        return mat;
    }
}
