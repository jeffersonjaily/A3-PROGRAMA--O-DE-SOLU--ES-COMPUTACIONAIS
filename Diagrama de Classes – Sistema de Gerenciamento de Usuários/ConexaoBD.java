// ConexaoBD.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBD {
    // É uma boa prática mover esses dados para um arquivo de configuração (.properties)
    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco";
    private static final String USUARIO = "root";
    private static final String SENHA = "senha";

    // Carregar o driver JDBC uma única vez
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC do MySQL não encontrado!", e);
        }
    }

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public static void fecharConexao(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    public static void fecharConexao(Connection conn, PreparedStatement stmt) {
        fecharConexao(conn);
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar o PreparedStatement: " + e.getMessage());
            }
        }
    }

    public static void fecharConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {
        fecharConexao(conn, stmt);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar o ResultSet: " + e.getMessage());
            }
        }
    }
}