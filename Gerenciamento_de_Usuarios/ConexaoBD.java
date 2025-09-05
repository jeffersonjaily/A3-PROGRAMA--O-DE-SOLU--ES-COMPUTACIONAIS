import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBD {
    private static ConexaoBD instance;
    private Properties props = new Properties();
    private final String configFilePath = "config.properties";

    private String url;
    private String usuario;
    private String senha;

    private ConexaoBD() {
        try {
            loadProperties();
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar configuração ou driver JDBC!", e);
        }
    }

    public static ConexaoBD getInstance() {
        if (instance == null) {
            instance = new ConexaoBD();
        }
        return instance;
    }

    private void loadProperties() throws IOException {
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            props.load(fis);
            this.url = props.getProperty("db.url");
            this.usuario = props.getProperty("db.user");
            this.senha = props.getProperty("db.password");
        }
    }

    public String getUrl() { return url; }
    public String getUsuario() { return usuario; }
    public String getSenha() { return senha; }

    public Connection getConexao() throws SQLException {
        if (url == null || usuario == null) {
            throw new SQLException("Configuração do banco de dados não carregada.");
        }
        return DriverManager.getConnection(url, usuario, senha);
    }

    public void testarConexao(String url, String user, String password) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Apenas abre e fecha a conexão para teste
        }
    }

    public void salvarConfiguracoes(String url, String user, String password) throws IOException {
        props.setProperty("db.url", url);
        props.setProperty("db.user", user);
        props.setProperty("db.password", password);
        try (FileOutputStream fos = new FileOutputStream(configFilePath)) {
            props.store(fos, "Configuração do Banco de Dados");
        }
        loadProperties();
    }

    // --- Métodos estáticos para fechar recursos de forma segura ---
    public static void fecharConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void fecharConexao(Connection conn, PreparedStatement stmt) {
        try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void fecharConexao(Connection conn) {
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
}
