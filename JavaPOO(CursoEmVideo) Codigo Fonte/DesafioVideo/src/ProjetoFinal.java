public class ProjetoFinal {
    public static void main(String[] args) {

        Video v[] = new Video[3];
        v[0] = new Video("Aula 1 de Poo");
        v[1] = new Video("Aula 10 de Java");
        v[2] = new Video("Aula 3 de Spring");

        Gafanhoto g[] = new Gafanhoto[2];
        g[0] = new Gafanhoto("Artur","M",22,"artces");
        g[1] = new Gafanhoto("Cesar","M",25,"ces");
        //System.out.println(v[0].toString());
       // System.out.println(g[0].toString());
       //Pessoa p = new Pessoa("Artur","M", 22); ele solicita a criação de pessoa

        Visualizacao vis[] = new Visualizacao[5];
        vis[0] = new Visualizacao(g[0], v[1]); //Artur assistindo Poo
        vis[0].avaliar();
        System.out.println(vis[0].toString());

        vis[1] = new Visualizacao(g[0], v[2]); // Artur assistindo Java
        vis[0].avaliar(87.0F);
        System.out.println(vis[1].toString());
    }
}
