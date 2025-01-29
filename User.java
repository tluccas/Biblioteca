import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String nome;
    private String email;
    private String senha;
    private List<Emprestimo> historico;
    // Construtor
    public User(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.historico = new ArrayList<>();
    }
    //GETTERS E SETTERS
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    //Metodos

    public List<Emprestimo> getHistorico() {
        return historico;
    }

    public void solicitarEmprestimo(Livro livro) {
            try{
                Emprestimo novo = new Emprestimo(livro, this, LocalDate.now(), LocalDate.now().plusDays(7) );

                this.historico.add(novo); //Adiciona o emprestimo ao historico do user
                System.out.printf("Emprestimo do livro %s para o usuário %s concluido com sucesso!\n ", livro.getTitulo(), getNome());

            }catch(IllegalStateException e){
                System.out.println(e.getMessage());
            }

    }

    public String toString() {
        return "Nome: " + nome + "\nEmail: " + email + "\nHistorico: " + historico;
    }
}
