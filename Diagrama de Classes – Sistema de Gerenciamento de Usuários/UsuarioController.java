// UsuarioController.java (Versão Final com DAO)

import java.util.List;

/**
 * A classe Controller é responsável por receber as requisições da View,
 * processar a lógica de negócio e coordenar as ações, delegando a
 * persistência de dados para a camada DAO.
 */
public class UsuarioController {
    
    private UsuarioDAO usuarioDAO;

    /**
     * O construtor inicializa uma instância do UsuarioDAO,
     * que será usada por todos os métodos do controller.
     */
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Recebe os dados da View, cria um objeto Usuario e o envia
     * para a camada DAO para ser salvo no banco de dados.
     * @param nome O nome do usuário.
     * @param email O email do usuário.
     * @param senha A senha do usuário.
     */
    public void criarUsuario(String nome, String email, String senha) {
        // O ID é gerenciado pelo banco de dados (AUTO_INCREMENT),
        // então podemos passar 0 ou qualquer valor temporário.
        Usuario novoUsuario = new Usuario(0, nome, email, senha);
        usuarioDAO.criarUsuario(novoUsuario);
    }

    /**
     * Recebe os dados de atualização da View, cria um objeto Usuario
     * e o envia para a camada DAO para ser atualizado no banco.
     * @param id O ID do usuário a ser atualizado.
     * @param nome O novo nome do usuário.
     * @param email O novo email do usuário.
     * @param senha A nova senha do usuário.
     */
    public void atualizarUsuario(int id, String nome, String email, String senha) {
        Usuario usuarioAtualizado = new Usuario(id, nome, email, senha);
        usuarioDAO.atualizarUsuario(usuarioAtualizado);
    }

    /**
     * Recebe um ID da View e solicita à camada DAO que remova
     * o usuário correspondente do banco de dados.
     * @param id O ID do usuário a ser removido.
     */
    public void removerUsuario(int id) {
        usuarioDAO.removerUsuario(id);
    }

    /**
     * Solicita à camada DAO a lista completa de usuários cadastrados
     * no banco de dados e a retorna para a View.
     * @return Uma lista de objetos Usuario.
     */
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        }
        return usuarios;
    }
}