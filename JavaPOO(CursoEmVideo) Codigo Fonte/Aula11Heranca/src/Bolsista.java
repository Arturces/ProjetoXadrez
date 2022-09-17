public class Bolsista extends Aluno{
    private double bolsa;

    public void renovarBolsa(){
        System.out.println("Renovando a bolsa de " + this.nome);
    }

    @Override
    public void pagarMensalidade(){
        System.out.println(this.nome + " É bolsista, desconto habilitado");

    }

    public double getBolsa() {
        return bolsa;
    }

    public void setBolsa(double bolsa) {
        this.bolsa = bolsa;
    }
}
