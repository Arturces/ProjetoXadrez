public class Video implements AcoesVideo{
    private String titulo;
    private int avaliacao, views, curtidas;
    private boolean reproduzindo;


    public Video(String titulo) {
        this.titulo = titulo;
        this.avaliacao = 1;
        this.views = 0;
        this.reproduzindo = false;
    }

    @Override
    public void play() {
    this.reproduzindo = true;
    }

    @Override
    public void pause() {
    this.reproduzindo = false;
    }

    @Override
    public void like() {
        this.curtidas++;
    }
      public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) { //calcular a media de acordo com a quantidade de avaialçoes
        int nova;
        nova = (this.avaliacao + avaliacao) / this.views;
        this.avaliacao = nova;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public boolean getReproduzindo() {
        return reproduzindo;
    }

    public void setReproduzindo(boolean reproduzindo) {
        this.reproduzindo = reproduzindo;
    }

    @Override
    public String toString() {
        return "Video{" +
                "titulo='" + titulo + '\'' +
                ", avaliacao=" + avaliacao +
                ", views=" + views +
                ", curtidas=" + curtidas +
                ", reproduzindo=" + reproduzindo +
                '}';
    }
}
