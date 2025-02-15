import java.util.List;
import java.util.Scanner;

public class Admin extends Pessoa{

    public Admin(String nome, String email, String senha){
        super(nome, email, senha);
    }

    //Métodos do ADM

    public void DeletarUser(List<User> users, String nome){
        users.removeIf(user -> user.getNome().equalsIgnoreCase(nome));
    }

    // Método para registrar um novo usuário
    public void registrarUsuario(List<User> users, String nome, String email, String senha) {
        // Verificar se o e-mail já está em uso
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new IllegalArgumentException("ERRO: E-mail já utilizado!");
            }
        }

        // Criação do usuario
        User novoUser = new User(nome, email, senha);
        users.add(novoUser);
        System.out.printf("\nUsuário %s criado com sucesso!\n", nome);
    }
}
