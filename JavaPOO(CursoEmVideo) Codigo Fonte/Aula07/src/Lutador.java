public class Lutador {
    private String nome, nacionalidade, categoria;
    private int idade, vitorias, derrotas, empates;
    private float altura;
    private float peso;

    public Lutador(String no, String na, int id, int vi, int de, int em, float al, float pe) {
        this.nome = no;
        this.nacionalidade = na;
        this.idade = id;
        this.vitorias = vi;
        this.derrotas = de;
        this.empates = em;
        this.altura = al;
        this.setPeso (pe);
    }

    public void apresentar() {
        System.out.println("------------------------------------------");
        System.out.println("Lutador: " + getNome());
        System.out.println("Origem: " + getNacionalidade());
        System.out.println("Idade: " + getIdade());
        System.out.println("Altura: " + getAltura());
        System.out.println("Pesando: " +getPeso());
        System.out.println("Ganhou: " +getVitorias());
        System.out.println("Perdeu: " +getDerrotas());
        System.out.println("Empatou: " + getEmpates());
    }

    public void status() {
        System.out.println("-------------------------------");
        System.out.println(getNome() + " é um peso " + getCategoria());
        System.out.println(getVitorias() + " vitorias");
        System.out.println(getDerrotas() + " derrotas");
        System.out.println(getEmpates() + " empates");
    }

    public void ganharLuta() {
        setVitorias(getVitorias() + 1);
        //this.vitorias = this.vitorias + 1;
    }

    public void perderLuta() {
        setDerrotas(getDerrotas() + 1);
    }

    public void empatarLuta() {
        setEmpates(getEmpates() + 1);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String no) {
        this.nome = no;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String na) {
        this.nacionalidade = nacionalidade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int id) {
        this.idade = idade;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float al) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
        this.setCategoria();
    }

    private void setCategoria() {
        if (this.peso < 52.2) {
            this.categoria = "Invalido";
           // System.out.println("Categoria: " + this.categoria); //this.categoria = "Invalido";
        } else if (this.peso <= 70.3) {
            this.categoria = "Leve";
           // System.out.println("Categoria: " + this.categoria);
        } else if (this.peso <= 83.9) {
            this.categoria = "Médio";
           // System.out.println("Categoria: " + this.categoria);
        } else if (this.peso <= 120.2) {
            this.categoria = "Pesado";
            //System.out.println("Categoria: " + this.categoria);
        } else this.categoria = "Invalido";
        {
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vi) {
        this.vitorias = vi;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int de) {
        this.derrotas = de;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int em) {
        this.empates = em;
    }
}

