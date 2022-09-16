package aplicacao;


import Xadrez.PartidaXadrez;
import jogotabuleiro.Tabuleiro;

public class Programa {

    public static void main(String[] args) {

        PartidaXadrez partidaXadrez = new PartidaXadrez();
        UI.printTabuleiro(partidaXadrez.getPecas());

    }
}
