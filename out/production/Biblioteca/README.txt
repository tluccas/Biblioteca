Sistema de Gerenciamento de Biblioteca
Descrição:
Um sistema para gerenciar livros, usuários e empréstimos em uma biblioteca. O sistema pode permitir que os administradores adicionem livros ao acervo, cadastrem usuários e gerenciem empréstimos, enquanto os usuários podem consultar o catálogo e verificar status de empréstimos.

Classes Principais:

Livro

Atributos:
titulo (string)
autor (string)
ano_publicacao (int)
disponivel (bool)
Métodos:
emprestar()
devolver()
exibir_informacoes()

Usuario

Atributos:
nome (string)
email (string)
historico_emprestimos (lista de objetos Emprestimo)
Métodos:
visualizar_historico()
solicitar_emprestimo()

Emprestimo

Atributos:
livro (objeto Livro)
usuario (objeto Usuario)
data_emprestimo (data)
data_devolucao (data)
Métodos:
calcular_prazo()
exibir_detalhes()

Biblioteca

Atributos:
catalogo (lista de objetos Livro)
usuarios (lista de objetos Usuario)
Métodos:
adicionar_livro()
remover_livro()
listar_livros()
registrar_usuario()
gerenciar_emprestimo()
Funcionalidades do Sistema:

Para o administrador:

Cadastro e exclusão de livros e usuários.
Controle de empréstimos e devoluções.
Listagem de livros disponíveis e usuários registrados.
Para o usuário:

Consulta de livros disponíveis.
Solicitação de empréstimos.
Visualização de histórico de empréstimos.