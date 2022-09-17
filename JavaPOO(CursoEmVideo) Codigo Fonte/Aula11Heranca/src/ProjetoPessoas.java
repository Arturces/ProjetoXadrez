public class ProjetoPessoas {
    public static void main(String[] args) {
       // Pessoa p1 = new Pessoa();
            Visitante v1 = new Visitante();
            v1.setNome("Artur");
            v1.setIdade(25);
            v1.setSexo("M");
        System.out.println(v1.toString());

        Aluno a1 = new Aluno();
            a1.setNome("César");
            a1.setMatricula(123);
            a1.setCurso("T.I");
            a1.setIdade(24);
            a1.setSexo("M");
            a1.pagarMensalidade();

        Bolsista b1 = new Bolsista();
            b1.setMatricula(1234);
            b1.setNome("Gonçalves");
            b1.setIdade(15);
            b1.setSexo("F");
            b1.setBolsa(12.5F);
            b1.pagarMensalidade();

    }
}
