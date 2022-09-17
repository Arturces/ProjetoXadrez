public class ProjetoAnimal {
    public static void main(String[] args) {

        //Animal x = new Animal();
        Mamifero m = new Mamifero();
        m.emitirSom();
        Lobo l = new Lobo();
        l.emitirSom();
        Cachorro c = new Cachorro();
        c.emitirSom();

        c.reagir("Olá");
        c.reagir("Vai Apanhar");
        c.reagir(11,45);
        c.reagir(21,00);
        c.reagir(true);
        c.reagir(false);
        c.reagir(2, 12.5F);
        c.reagir(19, 5.5F);


    }
}
