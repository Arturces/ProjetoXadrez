public class ProjetoAnimal {
    public static void main(String[] args) {

        //Animal n = new Animal(); não pode criar pois estamos utilizando a classe abstrata
        Mamifero m = new Mamifero();
        Reptil r = new Reptil();
        Peixe p = new Peixe();
        Ave a = new Ave();

        Canguru c = new Canguru();
        Cachorro k = new Cachorro();
        Cobra j = new Cobra();
        Tartaruga t = new Tartaruga();
        GoldFish g = new GoldFish();
        Arara e = new Arara();

        c.locomover();
        k.locomover();
        k.emitirSom();
        c.emitirSom();
        p.emitirSom();

        t.alimentar();
        t.emitirSom();
        t.locomover();
        t.setCorCasco("Preto");
        System.out.println("A cor do casco é" + t.getCorCasco());


        // m.setPeso(35.3F);
        // m.setCorPelo("Marrom");
        // m.setIdade(15);
        //System.out.println(m.toString().toUpperCase());

    }

}
