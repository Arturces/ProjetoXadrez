public class Principal {
    public static void main(String[] args) {

        //Lutador l = new Lutador("Pretty Boy", "Franca", 31, 11, 2, 2, 1.75F, 68.9F);

        Lutador[] l = new Lutador[6];
        l[0] = new Lutador ("Pretty Boy", "Franca", 31, 11, 2, 1, 1.75F, 68.9F);
        l[1] = new Lutador ("PutScript", "Brasil", 29 , 12, 2, 3, 1.68F, 57.8F);
        l[2] = new Lutador ("SnapShadow", "EUA", 35 , 14, 2, 1, 1.65F, 80.9F);
        l[3] = new Lutador ("Dead Code", "Australia", 28 , 13, 0, 2, 1.93F, 81.6F);
        l[4] = new Lutador ("UFOcobol", "Brasil", 37 , 5, 4, 2, 1.73F, 119.3F);
        l[5] = new Lutador ("Nerdaart", "EUA", 30 , 12, 2, 4, 1.81F, 105.3F);

        Luta UEC01 = new Luta();
        UEC01.marcarLuta(l[4], l[5]);
        UEC01.lutar();
       // l[0].status();
       // l[1].status();
    }
}
