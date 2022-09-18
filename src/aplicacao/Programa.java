package aplicacao;


import Xadrez.ExcecaoXadrez;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        List<PecaXadrez> capturada = new ArrayList<>();

        while (true) {
           try {
                UI.clearScreen();
                UI.printPartida(partidaXadrez, capturada);
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(entrada);

                boolean[][] possiveisMovimentos = partidaXadrez.possiveisMovimentos(origem);
                UI.clearScreen();
                UI.printTabuleiro(partidaXadrez.getPecas(), possiveisMovimentos);
                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(entrada);

                PecaXadrez capturarPeca = partidaXadrez.moverPeca(origem, destino);

                if(capturarPeca != null){
                    capturada.add(capturarPeca);
                }
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
