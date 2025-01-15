import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfaceGraficaTest {

    public static void main(String[] args) {
        List<Livro> catalogo = new ArrayList<>(); // Catalogo da biblioteca
        List<User> users = new ArrayList<>(); // Lista de users

        // Criar a interface gráfica principal
        JFrame frame = new JFrame("Sistema de Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JLabel titulo = new JLabel("Bem-vindo ao Sistema de Biblioteca");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton addLivroButton = new JButton("Adicionar Livro");
        JButton removeLivroButton = new JButton("Remover Livro");
        JButton listarLivrosButton = new JButton("Listar Livros");
        JButton registrarUsuarioButton = new JButton("Registrar Usuário");
        JButton criarEmprestimoButton = new JButton("Criar Empréstimo");
        JButton sairButton = new JButton("Sair");

        panel.add(titulo);
        panel.add(addLivroButton);
        panel.add(removeLivroButton);
        panel.add(listarLivrosButton);
        panel.add(registrarUsuarioButton);
        panel.add(criarEmprestimoButton);
        panel.add(sairButton);

        frame.add(panel);
        frame.setVisible(true);

        // Ações dos botões
        addLivroButton.addActionListener(e -> {
            JTextField tituloField = new JTextField();
            JTextField autorField = new JTextField();
            JTextField anoField = new JTextField();

            Object[] message = {
                    "Título:", tituloField,
                    "Autor:", autorField,
                    "Ano de Publicação:", anoField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Adicionar Livro", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    String tituloLivro = tituloField.getText();
                    String autor = autorField.getText();
                    int ano = Integer.parseInt(anoField.getText());

                    Livro livro = new Livro(tituloLivro, autor, ano);
                    catalogo.add(livro);
                    JOptionPane.showMessageDialog(null, "Livro adicionado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar o livro. Verifique os dados inseridos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeLivroButton.addActionListener(e -> {
            String tituloLivro = JOptionPane.showInputDialog("Insira o título do livro a ser removido:");
            if (tituloLivro != null && !tituloLivro.isEmpty()) {
                boolean encontrado = false;
                for (int i = 0; i < catalogo.size(); i++) {
                    if (catalogo.get(i).getTitulo().equalsIgnoreCase(tituloLivro)) {
                        catalogo.remove(i);
                        encontrado = true;
                        JOptionPane.showMessageDialog(null, "Livro removido com sucesso!");
                        break;
                    }
                }
                if (!encontrado) {
                    JOptionPane.showMessageDialog(null, "Livro não encontrado no catálogo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        listarLivrosButton.addActionListener(e -> {
            if (catalogo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "O catálogo está vazio.");
                return;
            }

            StringBuilder detalhes = new StringBuilder();
            for (int i = 0; i < catalogo.size(); i++) {
                detalhes.append("Livro ").append(i + 1).append(":\n").append(catalogo.get(i).toString()).append("\n\n");
            }

            JTextArea textArea = new JTextArea(detalhes.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 200));

            JOptionPane.showMessageDialog(null, scrollPane, "Lista de Livros", JOptionPane.INFORMATION_MESSAGE);
        });

        registrarUsuarioButton.addActionListener(e -> {
            JTextField nomeField = new JTextField();
            JTextField emailField = new JTextField();

            Object[] message = {
                    "Nome:", nomeField,
                    "Email:", emailField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Registrar Usuário", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String nome = nomeField.getText();
                String email = emailField.getText();

                boolean emailDisponivel = true;
                for (User user : users) {
                    if (user.getEmail().equalsIgnoreCase(email)) {
                        emailDisponivel = false;
                        break;
                    }
                }

                if (emailDisponivel) {
                    User user = new User(nome, email);
                    users.add(user);
                    JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Email já registrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        criarEmprestimoButton.addActionListener(e -> {
            String nomeUsuario = JOptionPane.showInputDialog("Digite o nome do usuário:");
            if (nomeUsuario == null || nomeUsuario.isEmpty()) return;

            User user = null;
            for (User u : users) {
                if (u.getNome().equalsIgnoreCase(nomeUsuario)) {
                    user = u;
                    break;
                }
            }

            if (user == null) {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String tituloLivro = JOptionPane.showInputDialog("Digite o título do livro:");
            if (tituloLivro == null || tituloLivro.isEmpty()) return;

            Livro livro = null;
            for (Livro l : catalogo) {
                if (l.getTitulo().equalsIgnoreCase(tituloLivro)) {
                    livro = l;
                    break;
                }
            }

            if (livro == null) {
                JOptionPane.showMessageDialog(null, "Livro não encontrado no catálogo.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                user.solicitarEmprestimo(livro);
                JOptionPane.showMessageDialog(null, "Empréstimo criado com sucesso!");
            }
        });

        sairButton.addActionListener(e -> System.exit(0));
    }
}