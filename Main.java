import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Livro> catalogo = new ArrayList<>(); // Catalogo da biblioteca
        List<User> users = new ArrayList<>(); //Lista de users
        Scanner s = new Scanner(System.in);
        Admin admin = new Admin("admin", "admin", "admin");
        while (true) {
            System.out.print("\n[C]Login\n[S]Sair  ");
            String controle = s.nextLine();
            if (controle.equalsIgnoreCase("S")) {
                System.out.print("Obrigado por usar nosso sistema!");
                break;
            } else if (!controle.equalsIgnoreCase("c")) {
                System.out.print("\nINSIRA UMA ENTRADA VÁLIDA!\n");
                continue;
            }
            int tentativas = 3;
            boolean isAdm = false;
            boolean loginSucesso = false; // Variável de controle

            do { // LOGIN
                System.out.print("\nUsuário: ");
                String username = s.nextLine();
                System.out.print("Senha: ");
                String password = s.nextLine();

                if (username.equals("admin") && password.equals("admin")) {
                    System.out.println("\nLOGIN DO ADMIN EFETUADO COM SUCESSO!\n");
                    isAdm = true;
                    loginSucesso = true; // Login bem-sucedido
                    break; // Sai do loop do-while
                } else {
                    for (int x = 0; x < users.size(); x++) {
                        if (users.get(x).getNome().equalsIgnoreCase(username) && users.get(x).getSenha().equals(password)) {
                            System.out.printf("\n\n=== Bem-vindo de volta, %s ===\n", users.get(x).getNome());
                            loginSucesso = true; // Login bem-sucedido
                            break; // Sai do for
                        }
                    }
                }

                if (!loginSucesso) {
                    tentativas--;
                    System.out.printf("\nUsuário ou senha incorretos. Tentativas restantes: %d\n", tentativas);
                }
            } while (tentativas > 0 && !loginSucesso);
            if (isAdm) {
                while (true) { //MENU ADMIN
                    System.out.println("\n\n=== ESCOLHA UMA OPÇÃO ABAIXO ===");
                    System.out.println("1. Adicionar Livro\n2. Remover Livro\n3. Listar Livros\n4. Registrar usuário\n5. Sair");
                    int opcao = 0;
                    while (true) { // Tratamento de erro
                        String entrada = s.nextLine();
                        try {
                            opcao = Integer.parseInt(entrada);
                            if (opcao <= 0) {
                                System.out.println("ERRO! Insira um número válido!");
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("ERRO! Insira apenas números!");
                        }
                    }
                    if (opcao == 1) {
                        String titulo;
                        String autor;
                        int ano_pub;
                        while (true) {
                            try {
                                System.out.println("Titulo do livro: ");
                                titulo = s.nextLine();
                                break;
                            } catch (Exception e) {
                                System.out.println("ERRO! Insira apenas letras");
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Autor: ");
                                autor = s.nextLine();
                                break;
                            } catch (Exception e) {
                                System.out.println("ERRO! Insira apenas letras");
                            }
                        }
                        while (true) {
                            ano_pub = 0;
                            System.out.println("Ano de Publicação: ");
                            String num = s.nextLine();

                            try {
                                ano_pub = Integer.parseInt(num); //Tenta converter para int

                                if (ano_pub > 0) {
                                    break;
                                } else {
                                    System.out.println("ERRO! Entrada inválida!");
                                }

                            } catch (Exception e) {
                                System.out.println("ERRO! Insira apenas numeros");
                            }
                        }
                        Livro livro = new Livro(titulo, autor, ano_pub);
                        catalogo.add(livro);
                        System.out.printf("\nO Livro %s adicionado com sucesso!", livro.getTitulo());
                    } else if (opcao == 2) {
                        boolean removido = false;
                        String busca;
                        while (true) {
                            try {
                                System.out.println("Insira o titulo do livro: ");
                                s.nextLine();
                                busca = s.nextLine();
                                break;
                            } catch (Exception e) {
                                System.out.println("ERRO! Insira apenas letras!");
                            }
                        }
                        for (int i = 0; i < catalogo.size(); i++) {
                            if (catalogo.get(i).getTitulo().equalsIgnoreCase(busca)) { //Pega o objeto livro de acordo com indice i e compara o titulo com o getTitulo do obj Livro
                                catalogo.remove(i);
                                removido = true;
                                System.out.printf("\nO Livro %s removido com sucesso!", busca);
                                break;

                            }
                        }
                        if (!removido) {
                            System.out.println("O livro não está no catálogo!");
                        }
                    } else if (opcao == 3) {
                        int num;
                        while (true) {
                            System.out.println("\n1. Exibição Detalhada\n2. Exibição Resumida ");
                            String exibir = s.nextLine();
                            try {
                                num = Integer.parseInt(exibir); //Se o usuario inserir uma string mas forem apenas numeros converte para int
                                if (num == 1 || num == 2) {
                                    break;
                                } else {
                                    System.out.println("Entrada inválida!");
                                }
                            } catch (NumberFormatException e) {
                                // Entra em uma exceção se a conversão der erro
                                System.out.println("Entrada inválida! Insira um número inteiro.");
                            }
                        }
                        if (num == 1) {
                            System.out.println("LIVROS NO CATALOGO:");
                            for (int i = 0; i < catalogo.size(); i++) {
                                System.out.printf("\nLivro N.%d:\n", i + 1);
                                System.out.println(catalogo.get(i));
                            }
                        } else if (num == 2) {
                            System.out.println("LIVROS NO CATALOGO:");
                            for (int i = 0; i < catalogo.size(); i++) {
                                System.out.printf("\nLivro N.%d:\n", i + 1);
                                System.out.println(catalogo.get(i).getTitulo());
                            }
                        }
                    } else if (opcao == 4) {
                        String email = "";
                        System.out.println("\nRESGISTRANDO USUÁRIO: ");
                        System.out.println("Digite o nome do usuário: ");
                        String nome = s.nextLine();
                        while (true) {
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
                            if (disponivel == true) {
                                break;
                            }
                        }
                        System.out.println("Senha: ");
                        String senha = s.nextLine();
                        User user = new User(nome, email, senha);
                        users.add(user);
                        System.out.printf("\nUsuário %s criado com sucesso!", user.getNome());

                    } else if (opcao == 5) {
                        System.out.println("SAINDO DO PROGRAMA...");
                        break;
                    }
                }
            } else {
                while (true) { //MENU USUARIO
                    System.out.println("\n\n=== ESCOLHA UMA OPÇÃO ABAIXO ===");
                    System.out.println("1. Criar Empréstimo\n2. Sair");
                    int opcao = 0;
                    while (true) { // Tratamento de erro
                        String entrada = s.nextLine();
                        try {
                            opcao = Integer.parseInt(entrada);
                            if (opcao <= 0) {
                                System.out.println("ERRO! Insira um número válido!");
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("ERRO! Insira apenas números!");
                        }
                    }
                    if (opcao == 1) {
                        String buscarLivro;
                        String buscarUser;
                        User user = null;
                        Livro livro = null;
                        System.out.println("\nNome do usuario: ");
                        while (user == null) {
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

                        while (livro == null) {
                            System.out.println("Nome do livro: ");
                            buscarLivro = s.nextLine();
                            boolean encontrado = false;

                            for (int i = 0; i < catalogo.size(); i++) {
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
                    } else if (opcao == 2) {
                        System.out.println("SAINDO DO PROGRAMA...");
                        break;
                    }
                }
            }
        }
    }
}

