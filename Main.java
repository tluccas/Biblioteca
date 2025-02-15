import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Livro> catalogo = new ArrayList<>(); // Catalogo da biblioteca
        List<User> users = new ArrayList<>(); //Lista de users
        Scanner s = new Scanner(System.in);
        Admin admin = new Admin("admin", "admin", "admin"); //Instanciando a classe ADMIN internamente


        while (true) {
            System.out.println("\n[C]Login\n[S]Sair: ");
            String controle = s.nextLine();
            if (controle.equalsIgnoreCase("S")) {
                System.out.print("Obrigado por usar nosso sistema!");
                break;
            } else if (!controle.equalsIgnoreCase("c")) {
                System.out.print("\nINSIRA UMA ENTRADA VÁLIDA!\n");
                continue;
            }

            boolean isAdm = fazerLogin(s, users);
            if (isAdm) {
                menuAdmin(s, catalogo, users, admin);
            } else {
                menuUsuario(s, catalogo, users);
            }
        }
    }

    // Método para realizar o login
    private static boolean fazerLogin(Scanner s, List<User> users) {
        int tentativas = 3; //Quantidade de tentativas
        boolean isAdm = false; //Controle para direcionar para menu de ADM ou USER
        boolean loginSucesso = false;

        do {
            System.out.print("\nUsuário: ");
            String username = s.nextLine();
            System.out.print("Senha: ");
            String password = s.nextLine();

            if (username.equals("admin") && password.equals("admin")) { //Validação do usuario e senha
                System.out.println("\nLOGIN DO ADMIN EFETUADO COM SUCESSO!\n");
                isAdm = true;
                loginSucesso = true;
                break;
            } else { //Continuação da validação
                for (User user : users) {
                    if (user.getNome().equalsIgnoreCase(username) && user.getSenha().equals(password)) {
                        System.out.printf("\n\n=== Bem-vindo de volta, %s ===\n", user.getNome());
                        loginSucesso = true;
                        break;
                    }
                }
            }

            if (!loginSucesso) {
                tentativas--;
                System.out.printf("\nUsuário ou senha incorretos. Tentativas restantes: %d\n", tentativas);
            }
        } while (tentativas > 0 && !loginSucesso);

        return isAdm;
    }

    // Método para exibir o menu do administrador
    private static void menuAdmin(Scanner s, List<Livro> catalogo, List<User> users,Admin admin) {
        while (true) {
            System.out.println("\n\n=== ESCOLHA UMA OPÇÃO ABAIXO ===");
            System.out.println("1. Adicionar Livro\n2. Remover Livro\n3. Listar Livros\n4. Registrar usuário\n5. Sair");
            int opcao = lerOpcao(s);

            switch (opcao) {
                case 1:
                    adicionarLivro(s, catalogo);
                    break;
                case 2:
                    removerLivro(s, catalogo);
                    break;
                case 3:
                    listarLivros(s, catalogo);
                    break;
                case 4:
                    while (true) {
                        System.out.println("\nREGISTRANDO USUÁRIO: ");
                        System.out.println("Digite o nome do usuário: ");
                        String nome = s.nextLine();

                        String email;
                        boolean emailDisponivel;
                        do {
                            System.out.println("Digite o email do usuário: ");
                            email = s.nextLine();
                            emailDisponivel = true;

                            // Verifica se o e-mail já está em uso
                            for (User user : users) {
                                if (user.getEmail().equalsIgnoreCase(email)) {
                                    System.out.println("\nERRO: E-mail já utilizado! Tente outro e-mail.\n");
                                    emailDisponivel = false;
                                    break;
                                }
                            }
                        } while (!emailDisponivel); // Repete até que um e-mail disponível seja inserido

                        System.out.println("Senha: ");
                        String senha = s.nextLine();

                        // Chama o método da classe Admin para registrar o usuário
                        try {
                            admin.registrarUsuario(users, nome, email, senha);
                            break; // Sai do loop após o registro bem-sucedido
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }

                        // Pergunta ao administrador se deseja tentar novamente
                        System.out.println("\nDeseja tentar registrar outro usuário? (S/N)");
                        String resposta = s.nextLine();
                        if (!resposta.equalsIgnoreCase("S")) {
                            break; // Sai do loop se a resposta não for "S"
                        }
                    }
                    break;
                case 5:
                    System.out.println("SAINDO DO PROGRAMA...");
                    return; // Retorna ao menu de login
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Método para exibir o menu do usuário
    private static void menuUsuario(Scanner s, List<Livro> catalogo, List<User> users) {
        boolean sairMenuUsuario = false;
        while (!sairMenuUsuario) {
            System.out.println("\n\n=== ESCOLHA UMA OPÇÃO ABAIXO ===");
            System.out.println("1. Criar Empréstimo\n2. Listar livros\n3. Sair");
            int opcao = lerOpcao(s);

            switch (opcao) {
                case 1:
                    criarEmprestimo(s, catalogo, users);
                    break;
                case 2:
                    listarLivros(s, catalogo);
                    break;
                case 3:
                    System.out.println("SAINDO DO MENU DO USUÁRIO...");
                    sairMenuUsuario = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Método para ler uma opção com tratamento de erros
    private static int lerOpcao(Scanner s) {
        while (true) {
            String entrada = s.nextLine();
            try {
                int opcao = Integer.parseInt(entrada);
                if (opcao > 0) {
                    return opcao;
                } else {
                    System.out.println("ERRO! Insira um número válido!");
                }
            } catch (Exception e) {
                System.out.println("ERRO! Insira apenas números!");
            }
        }
    }

    // Método para adicionar um livro ao catálogo
    private static void adicionarLivro(Scanner s, List<Livro> catalogo) {
        System.out.println("Titulo do livro: ");
        String titulo = s.nextLine();

        System.out.println("Autor: ");
        String autor = s.nextLine();

        int ano_pub = 0;
        while (true) { //Tratamento de erro para o ano de publicação (inteiro)
            System.out.println("Ano de Publicação: ");
            String num = s.nextLine();

            try {
                ano_pub = Integer.parseInt(num);
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
        System.out.printf("\nO Livro %s adicionado com sucesso!\n", livro.getTitulo());
    }

    // Método para remover um livro do catálogo
    private static void removerLivro(Scanner s, List<Livro> catalogo) {
        boolean removido = false;
        System.out.println("Insira o titulo do livro: ");
        String busca = s.nextLine();

        for (int i = 0; i < catalogo.size(); i++) {
            if (catalogo.get(i).getTitulo().equalsIgnoreCase(busca)) {
                catalogo.remove(i);
                removido = true;
                System.out.printf("\nO Livro %s removido com sucesso!\n", busca);
                break;
            }
        }

        if (!removido) {
            System.out.println("O livro não está no catálogo!");
        }
    }

    // Método para listar os livros do catálogo
    private static void listarLivros(Scanner s, List<Livro> catalogo) {
        int num;
        while (true) {
            System.out.println("\n1. Exibição Detalhada\n2. Exibição Resumida ");
            String exibir = s.nextLine();
            try {
                num = Integer.parseInt(exibir);
                if (num == 1 || num == 2) {
                    break;
                } else {
                    System.out.println("Entrada inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Insira um número inteiro.");
            }
        }

        System.out.println("LIVROS NO CATALOGO:");
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.printf("\nLivro N.%d:\n", i + 1);
            if (num == 1) {
                System.out.println(catalogo.get(i));
            } else if (num == 2) {
                System.out.println(catalogo.get(i).getTitulo());
            }
        }
    }

    // Método para criar um empréstimo
    private static void criarEmprestimo(Scanner s, List<Livro> catalogo, List<User> users) {
        System.out.println("\nSeu nome: ");
        User user = buscarUsuario(s, users);

        System.out.println("Nome do livro: ");
        Livro livro = buscarLivro(s, catalogo);

        user.solicitarEmprestimo(livro);
    }

    // Método para buscar um usuário na lista
    private static User buscarUsuario(Scanner s, List<User> users) {
        User user = null;
        while (user == null) {
            String buscarUser = s.nextLine();
            for (User u : users) {
                if (u.getNome().equalsIgnoreCase(buscarUser)) {
                    user = u;
                    break;
                }
            }
            if (user == null) {
                System.out.println("Usuário não encontrado! Tente novamente: ");
            }
        }
        return user;
    }

    // Método para buscar um livro no catálogo
    private static Livro buscarLivro(Scanner s, List<Livro> catalogo) {
        Livro livro = null;
        while (livro == null) {
            String buscarLivro = s.nextLine();
            for (Livro l : catalogo) {
                if (l.getTitulo().equalsIgnoreCase(buscarLivro)) {
                    livro = l;
                    break;
                }
            }
            if (livro == null) {
                System.out.println("Livro não encontrado! Tente novamente: ");
            }
        }
        return livro;
    }
}

