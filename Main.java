 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;

public class Main {

    private List<Livro> catalogo = new ArrayList<>(); // Catalogo da biblioteca
    private List<User> users = new ArrayList<>(); //Lista de users

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("BEM VINDO! \n=== ESCOLHA UMA OPÇÃO ABAIXO ===");
        System.out.println("1. Adicionar Livro\n2. Remover Livro\n3. Listar Livros\n4. Registrar usuário\n5. Gerenciar Empréstimo");
        int opcao = s.nextInt();
        if (opcao == 1) {

        }

    }
}
