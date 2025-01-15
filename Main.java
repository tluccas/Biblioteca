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
            System.out.println("1. Adicionar Livro\n2. Remover Livro\n3. Listar Livros\n4. Registrar usuário\n5. Criar Empréstimo\n6. Sair");
            int opcao = 0;
            while(true){ // Tratamento de erro
                String entrada = s.nextLine();
                try{
                    opcao = Integer.parseInt(entrada);
                    if (opcao <= 0) {
                        System.out.println("ERRO! Insira um número válido!");
                    }
                    else{
                        break;
                    }
                }catch (Exception e){
                    System.out.println("ERRO! Insira apenas números!");
                }
            }
            if (opcao == 1) {
                String titulo;
                String autor;
                int ano_pub;
                while(true) {
                    try{
                        System.out.println("Titulo do livro: ");
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
                    ano_pub = 0;
                    System.out.println("Ano de Publicação: ");
                    String num = s.nextLine();

                    try {
                        ano_pub = Integer.parseInt(num); //Tenta converter para int

                        if(ano_pub > 0){
                        break;
                        }
                        else{
                            System.out.println("ERRO! Entrada inválida!");
                        }

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
            else if (opcao == 3) {
                int num;
                while(true) {
                    System.out.println("\n1. Exibição Detalhada\n2. Exibição Resumida ");
                    String exibir = s.nextLine();
                    try {
                        num = Integer.parseInt(exibir); //Se o usuario inserir uma string mas forem apenas numeros converte para int
                        if (num == 1 || num == 2){
                        break;
                        }
                        else{
                            System.out.println("Entrada inválida!");
                        }
                    } catch (NumberFormatException e) {
                        // Entra em uma exceção se a conversão der erro
                        System.out.println("Entrada inválida! Insira um número inteiro.");
                    }
                }
                if (num == 1){
                    System.out.println("LIVROS NO CATALOGO:");
                    for (int i = 0; i < catalogo.size(); i++) {
                        System.out.printf("\nLivro N.%d:\n", i+1);
                        System.out.println(catalogo.get(i));
                    }
                }
                else if (num == 2){
                    System.out.println("LIVROS NO CATALOGO:");
                    for (int i = 0; i < catalogo.size(); i++){
                        System.out.printf("\nLivro N.%d:\n", i+1);
                        System.out.println(catalogo.get(i).getTitulo());
                    }
                }
            }
            else if (opcao == 4) {
                String email = "";
                System.out.println("\nRESGISTRANDO USUÁRIO: ");
                System.out.println("Digite o nome do usuário: ");
                String nome = s.nextLine();
                while(true) {
                    System.out.println("Digite o email do usuario: ");
                    email = s.nextLine();
                    boolean disponivel = true;
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getEmail().equalsIgnoreCase(email)) {
                            System.out.println("\nERRO: E-mail já utilizado!\n");
                            disponivel = false;
                            break;
                        }
                    }
                    if (disponivel == true){
                        break;
                    }
                }
                User user = new User(nome, email);
                users.add(user);
                System.out.printf("\nUsuário %s criado com sucesso!", user.getNome());

            }
            else if (opcao == 5) {
                String buscarLivro;
                String buscarUser;
                User user = null;
                Livro livro = null;
                System.out.println("\nNome do usuario: ");
                while(user == null) {
                    buscarUser = s.nextLine();
                    boolean encontrado = false;

                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getNome().equalsIgnoreCase(buscarUser)) {
                            user = users.get(i);
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Usuário não encontrado! Tente novamente: ");
                    }
                }

                while(livro == null) {
                    System.out.println("Nome do livro: ");
                    buscarLivro = s.nextLine();
                    boolean encontrado = false;

                    for (int i = 0; i < catalogo.size(); i++){
                        if (catalogo.get(i).getTitulo().equalsIgnoreCase(buscarLivro)) {
                            livro = catalogo.get(i);
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Livro não encontrado! Tente novamente: ");
                    }
                }

                user.solicitarEmprestimo(livro);
            }
            else if (opcao == 6) {
                System.out.println("SAINDO DO PROGRAMA...");
                break;
            }
        }
    }
}

