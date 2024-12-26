import java.time.LocalDate;

public class Emprestimo {

    private Livro livro;
    private User user;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    //Construtor
    public Emprestimo(Livro livro, User user, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        if (!livro.isDisponivel()) {
            throw new IllegalStateException("O livro \"" + livro.getTitulo() + "\" está indisponível!");
        }
        this.livro = livro;
        this.user = user;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        livro.setDisponivel(false);
    }

    //GETTERS E SETTERS
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataEmprestimo.plusDays(7);
    }

    //Metodos

    public String toString() {
        return "\nLivro: " + livro.getTitulo()
                + "\nUsuário: " + user.getNome()
                + "\nData de empréstimo: " + dataEmprestimo
                + "\nData de devolucao: " + dataDevolucao;
    }


}
