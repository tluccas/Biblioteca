 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
         List<Livro> catalogo = new ArrayList<>(); // Catalogo da biblioteca
         List<User> users = new ArrayList<>(); //Lista de users
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("\nBEM VINDO! \n=== ESCOLHA UMA OPÇÃO ABAIXO ===");
            System.out.println("1. Adicionar Livro\n2. Remover Livro\n3. Listar Livros\n4. Registrar usuário\n5. Gerenciar Empréstimo");
            int opcao = s.nextInt();
            if (opcao == 1) {
                String titulo;
                String autor;
                int ano_pub;
                while(true) {
                    try{
                        System.out.println("Titulo do livro: ");
                        s.nextLine(); //Limpa o buffer para evitar bug
                        titulo = s.nextLine();
                        break;
                    }catch(Exception e){
                        System.out.println("ERRO! Insira apenas letras");}
                }
                while(true) {
                    try{
                        System.out.println("Autor: ");
                        autor = s.nextLine();
                        break;
                    }catch(Exception e){
                        System.out.println("ERRO! Insira apenas letras");}
                }
                while(true) {
                    try {
                        System.out.println("Ano de Publicação: ");
                        ano_pub = s.nextInt();
                        s.nextLine(); // Correção de um bug, limpa o buffer
                        break;
                }catch(Exception e){
                    System.out.println("ERRO! Insira apenas numeros");}
                }
                Livro livro = new Livro(titulo, autor, ano_pub);
                catalogo.add(livro);
                System.out.printf("\nO Livro %s adicionado com sucesso!", livro.getTitulo());
            }
            else if (opcao == 2) {
                boolean removido = false;
                String busca;
                while(true) {
                    try{
                System.out.println("Insira o titulo do livro: ");
                s.nextLine();
                busca = s.nextLine();
                break;
                    }
                    catch (Exception e){
                    System.out.println("ERRO! Insira apenas letras!");
                    }
                }
                for(int i = 0; i < catalogo.size(); i++) {
                    if (catalogo.get(i).getTitulo().equalsIgnoreCase(busca)) { //Pega o objeto livro de acordo com indice i e compara o titulo com o getTitulo do obj Livro
                        catalogo.remove(i);
                        removido = true;
                        System.out.printf("\nO Livro %s removido com sucesso!", busca);
                        break;

                    }
                }
                if(!removido){
                    System.out.println("O livro não está no catálogo!");
                }
            }
        }
    }
}
