import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaDados {

    private static final String LIVROS_FILE = "livros.txt";
    private static final String USUARIOS_FILE = "usuarios.txt";

    // Método Salvando livros em livros.txt

    public static void salvarLivros(List<Livro> catalogo) {
        System.out.println("Salvando catalogo:" + catalogo.size() + " LIVROS");
        System.out.println("\n...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LIVROS_FILE))) {
            for (Livro livro : catalogo) {
                writer.write(livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.getAno_pub());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar livros" + e.getMessage());
        }
    }

    // Método para salvar users em usuarios.txt
    public static void salvarUsuarios(List<User> users){
        System.out.println("Salvando usuarios:" + users.size() + " USUARIOS");
        System.out.println("\n...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USUARIOS_FILE))){
            for (User user : users){
                writer.write(user.getNome() + ";" + user.getEmail() + ";" + user.getSenha());
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println("Erro ao salvar usuarios" + e.getMessage());
        }
    }

    //Método para carregar o catalógo
    public static List<Livro> carregarCatalogo(){
        System.out.println("Carregando livros...");
        List<Livro> catalogo = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(LIVROS_FILE))){
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] dados = linha.split(";"); //Vai criar uma "quebra" quando tiver ";"
                if (dados.length == 3){ //Verifica se as quebras sao iguais a qnt de atributos de cada livro
                    String titulo = dados[0];
                    String autor = dados[1];
                    int ano_pub = Integer.parseInt(dados[2]);
                    catalogo.add(new Livro(titulo, autor, ano_pub)); //recuperando os dados dos livros no catalogo
                }
            }
            System.out.println("\nLivros carregados com sucesso!");
        }catch (IOException e){
            System.out.println("Erro ao carregar livros" + e.getMessage());
        }
        return catalogo; //Retornamos o catalogo
    }

    //Método para carregar os usuários (mesma lógica do método anterior)
    public static List<User> carregarUsuarios(){
        System.out.println("\nCarregando usuarios...");
        List<User> users = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_FILE))){
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                if (dados.length == 3){
                    String nome = dados[0];
                    String email = dados[1];
                    String senha = dados[2];
                    users.add(new User(nome, email, senha));
                }
            }
            System.out.println("\nUsuarios carregados com sucesso!");
        }catch (IOException e){
            System.out.println("Erro ao carregar usuarios" + e.getMessage());
        }
        return users;
    }

}
