// UsuarioDAO.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Cria um novo usuário com os novos campos
    public boolean criarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, cpf, login, senha, cargo, perfil) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenha()); // Lembrete: Implementar hashing aqui!
            stmt.setString(6, usuario.getCargo());
            stmt.setString(7, usuario.getPerfil() != null ? usuario.getPerfil() : "user");
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao criar usuário: " + e.getMessage());
            return false;
        }
    }

    // Atualiza um usuário existente com os novos campos
    public boolean atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, cpf = ?, login = ?, senha = ?, cargo = ?, perfil = ? WHERE id_usuario = ?";
        try (Connection conn = ConexaoBD.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, usuario.getCargo());
            stmt.setString(7, usuario.getPerfil());
            stmt.setInt(8, usuario.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }

    // Remove um usuário pelo ID (não precisa de alteração)
    public boolean removerUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = ConexaoBD.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao remover usuário: " + e.getMessage());
            return false;
        }
    }

    // Lista todos os usuários, incluindo os novos campos
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = ConexaoBD.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("cargo"),
                    rs.getString("perfil")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // Verifica se o email já existe (não precisa de alteração)
    public boolean emailJaExiste(String email) {
        // ... (o código original aqui está correto e não precisa mudar)
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        try (Connection conn = ConexaoBD.getInstance().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email.toLowerCase());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar email: " + e.getMessage());
        }
        return false;
    }
}