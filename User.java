import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends Pessoa {

    private List<Emprestimo> historico;
    // Construtor
    public User(String nome, String email, String senha) {
        super(nome, email, senha);
        this.historico = new ArrayList<>();
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
        return "Nome: " + super.getNome() + "\nEmail: " + super.getEmail() + "\nHistorico: " + historico;
    }
}
