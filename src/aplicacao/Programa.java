package aplicacao;


import Xadrez.ExcecaoXadrez;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true) {
            try {
                UI.clearScreen();
                UI.printTabuleiro(partidaXadrez.getPecas());
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(entrada);

                System.out.println();
                System.out.print("Destino :");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(entrada);

                PecaXadrez capturarPeca = partidaXadrez.moverPeca(origem, destino);
            } catch (ExcecaoXadrez e) {
                System.out.println(e.getMessage());
                entrada.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                entrada.nextLine();
            }
        }
    }
}
