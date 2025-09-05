import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigDbController {

    @FXML private TextField urlField;
    @FXML private TextField userField;
    @FXML private PasswordField passwordField;

    private ConexaoBD conexaoBD;

    @FXML
    public void initialize() {
        conexaoBD = ConexaoBD.getInstance();
        carregarConfiguracoes();
    }

    private void carregarConfiguracoes() {
        urlField.setText(conexaoBD.getUrl());
        userField.setText(conexaoBD.getUsuario());
        passwordField.setText(conexaoBD.getSenha());
    }

    @FXML
    private void handleTestarConexao() {
        try {
            conexaoBD.testarConexao(urlField.getText(), userField.getText(), passwordField.getText());
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Conexão com o banco de dados bem-sucedida!");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Falha na Conexão", "Não foi possível conectar ao banco: " + e.getMessage());
        }
    }

    @FXML
    private void handleSalvar() {
        try {
            conexaoBD.salvarConfiguracoes(urlField.getText(), userField.getText(), passwordField.getText());
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Configurações salvas. Reinicie a aplicação para usá-las.");
            
            Stage stage = (Stage) urlField.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao Salvar", "Não foi possível salvar o arquivo de configuração: " + e.getMessage());
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}