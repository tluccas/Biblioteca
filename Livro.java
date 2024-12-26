public class Livro {

    private String titulo;
    private String autor;
    private int ano_pub;
    private boolean disponivel;

    //Construtor
    public Livro(String titulo, String autor, int ano_pub) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano_pub = ano_pub;
        disponivel = true;
    }

    //GETTERS E SETTERS
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno_pub() {
        return ano_pub;
    }
    public void setAno_pub(int ano_pub) {
        this.ano_pub = ano_pub;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    //Metodos
    public void Emprestar(){
        if (disponivel == true){
            System.out.println("Emprestado");
            disponivel = false;
        }
        else{
            System.out.println("Livro indisponivel!");
        }
    }
    public void Devolver(){
        if (disponivel == false){
            System.out.println("Devolvido");
            disponivel = true;
        }
        else{
            System.out.printf("\nO Livro %s já foi devolvido!", getTitulo());
        }
    }
    public String toString(){ //ToString para exibir as infos do livro
        return "TITULO: " + getTitulo() + " \nAUTOR: " + getAutor() +
                " \nANO DE PUB.: " + getAno_pub();
    }

}
