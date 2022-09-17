public class Tartaruga extends Reptil{
    private String corCasco;

    @Override
    public void locomover() {
        System.out.println("Andando Lentamente");
    }
    @Override
    public void alimentar() {
        System.out.println("Comendo Folhas da Arvore");
    }

    @Override
    public void emitirSom() {
        System.out.println("Som de passos lentos");
    }

    public String getCorCasco() {
        return corCasco;
    }

    public void setCorCasco(String corCasco) {
        this.corCasco = corCasco;
    }
}
