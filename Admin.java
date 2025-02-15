import java.util.List;

public class Admin extends Pessoa{

    public Admin(String nome, String email, String senha){
        super(nome, email, senha);
    }

    //Métodos do ADM

    public void DeletarUser(List<User> users, String nome){
        users.removeIf(user -> user.getNome().equalsIgnoreCase(nome));
    }
}