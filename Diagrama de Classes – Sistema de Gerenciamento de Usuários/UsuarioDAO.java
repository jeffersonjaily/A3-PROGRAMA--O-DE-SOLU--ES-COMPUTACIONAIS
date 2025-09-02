// UsuarioDAO.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    /**
     * Cria um novo usuário no banco de dados.
     * @param usuario O objeto Usuario a ser salvo.
     */
    public void criarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoBD.getConexao();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
            
            System.out.println("Usuário criado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao criar usuário: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conn, stmt);
        }
    }

    /**
     * Retorna uma lista com todos os usuários do banco de dados.
     * @return Uma lista de objetos Usuario.
     */
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoBD.getConexao();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conn, stmt, rs);
        }
        return usuarios;
    }

    /**
     * Atualiza os dados de um usuário no banco de dados.
     * @param usuario O objeto Usuario com os dados atualizados.
     */
    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id_usuario = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoBD.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId());
            
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                 System.out.println("Usuário atualizado com sucesso!");
            } else {
                 System.out.println("Nenhum usuário encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conn, stmt);
        }
    }

    /**
     * Remove um usuário do banco de dados pelo seu ID.
     * @param id O ID do usuário a ser removido.
     */
    public void removerUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexaoBD.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                 System.out.println("Usuário removido com sucesso!");
            } else {
                 System.out.println("Nenhum usuário encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao remover usuário: " + e.getMessage());
        } finally {
            ConexaoBD.fecharConexao(conn, stmt);
        }
    }
}