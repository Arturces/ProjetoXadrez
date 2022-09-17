public class ProjetoLivro {
    public static void main(String[] args) {

        Pessoa[] p = new Pessoa[2];
        Livro [] l = new Livro[3];
        Leitor[] l1 = new Leitor[2];


        p[0] = new Pessoa("Artur","M",28);
        p[1] = new Pessoa("Gabi","F",22);

        l[0] = new Livro("Java","Silva, Da Conceição",300,p[0]);
        l[1] = new Livro("TikTok","Chines, Do Insta",900, p[1]);
        l[2] = new Livro("Whatsapp", "Facebook", 500, p[0]);

        p[1].fazerAniver();
        l[0].abrir();
        l[0].folhear(30);
        l[0].avancarPag();
        p[1].fazerAniver();

        System.out.println(l[0].detalhes());
        System.out.println(l[1].detalhes());
        System.out.println(l[2].detalhes());



    }
}
