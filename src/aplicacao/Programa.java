package aplicacao;


import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true) {
            UI.printTabuleiro(partidaXadrez.getPecas());
            System.out.println();
            System.out.print("Origem: ");
            PosicaoXadrez origem = UI.lerPosicaoXadrez(entrada);

            System.out.println();
            System.out.print("Destino :");
            PosicaoXadrez destino = UI.lerPosicaoXadrez(entrada);

            PecaXadrez capturarPeca = partidaXadrez.moverPeca(origem, destino);
        }
    }
}
