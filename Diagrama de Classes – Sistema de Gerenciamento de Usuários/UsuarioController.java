// UsuarioController.java (Versão Final com Validação de Formato e Unicidade)
import java.util.List;

public class UsuarioController {
    
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void criarUsuario(String nome, String email, String senha) {
        // 1. Validação de Formato
        if (!ValidaEmail.isValido(email)) {
            System.out.println("\n[ERRO] O formato do email '" + email + "' é inválido. Usuário não foi criado.");
            return;
        }

        // 2. Validação de Unicidade
        if (usuarioDAO.emailJaExiste(email)) {
            System.out.println("\n[ERRO] O email '" + email + "' já está em uso. Usuário não foi criado.");
            return;
        }

        // Padroniza o email para minúsculas antes de salvar
        String emailMinusculo = email.toLowerCase();
        
        Usuario novoUsuario = new Usuario(0, nome, emailMinusculo, senha);
        boolean sucesso = usuarioDAO.criarUsuario(novoUsuario);

        if (sucesso) {
            System.out.println("Usuário criado com sucesso!");
        } else {
            System.out.println("Falha ao criar usuário. Erro inesperado no banco de dados.");
        }
    }

    public void atualizarUsuario(int id, String nome, String email, String senha) {
        // 1. Validação de Formato
        if (!ValidaEmail.isValido(email)) {
            System.out.println("\n[ERRO] O formato do email '" + email + "' é inválido. Usuário não foi atualizado.");
            return;
        }
        
        // Padroniza o email para minúsculas antes de salvar
        String emailMinusculo = email.toLowerCase();
        
        Usuario usuarioAtualizado = new Usuario(id, nome, emailMinusculo, senha);
        boolean sucesso = usuarioDAO.atualizarUsuario(usuarioAtualizado);

        if (sucesso) {
            System.out.println("Usuário atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar usuário. Verifique se o ID está correto ou se o e-mail já está em uso por outro usuário.");
        }
    }

    public void removerUsuario(int id) {
        boolean sucesso = usuarioDAO.removerUsuario(id);
        
        if (sucesso) {
            System.out.println("Usuário removido com sucesso!");
        } else {
            System.out.println("Falha ao remover usuário. Verifique se o ID está correto.");
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        }
        return usuarios;
    }
}