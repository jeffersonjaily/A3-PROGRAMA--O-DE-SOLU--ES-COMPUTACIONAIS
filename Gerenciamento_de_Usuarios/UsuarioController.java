// UsuarioController.java (Versão Final Refatorada)
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UsuarioController {

    @FXML private TextField idTextField, nomeTextField, emailTextField, cpfTextField, loginTextField, cargoTextField;
    @FXML private PasswordField senhaField;
    @FXML private Button removerButton;
    @FXML private TableView<Usuario> tabelaUsuarios;
    @FXML private TableColumn<Usuario, Integer> colunaId;
    @FXML private TableColumn<Usuario, String> colunaNome, colunaEmail, colunaCargo;

    private UsuarioDAO usuarioDAO;
    private Usuario usuarioSelecionado;

    @FXML
    public void initialize() {
        this.usuarioDAO = new UsuarioDAO();
        configurarTabela();
        tabelaUsuarios.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, newValue) -> selecionarUsuario(newValue));
        atualizarTabela();
    }

    private void configurarTabela() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
    }

    @FXML
    private void handleSalvar() {
        if (!validarCampos()) return;

        if (usuarioSelecionado == null) {
            // Criar novo usuário
            Usuario novoUsuario = new Usuario(0, nomeTextField.getText(), emailTextField.getText().toLowerCase(),
                    cpfTextField.getText(), loginTextField.getText(), senhaField.getText(),
                    cargoTextField.getText(), "user"); // Perfil padrão
            if (usuarioDAO.criarUsuario(novoUsuario)) {
                showAlert(AlertType.INFORMATION, "Sucesso", "Usuário criado com sucesso!");
                finalizarAcao();
            } else {
                showAlert(AlertType.ERROR, "Erro", "Falha ao criar usuário. Verifique se o login ou email já existem.");
            }
        } else {
            // Atualizar usuário existente
            usuarioSelecionado.setNome(nomeTextField.getText());
            usuarioSelecionado.setEmail(emailTextField.getText().toLowerCase());
            usuarioSelecionado.setCpf(cpfTextField.getText());
            usuarioSelecionado.setLogin(loginTextField.getText());
            usuarioSelecionado.setCargo(cargoTextField.getText());
            // A senha não é atualizada aqui para evitar salvá-la em branco.
            // Uma lógica para alterar senha deveria ser separada.
            if (usuarioDAO.atualizarUsuario(usuarioSelecionado)) {
                showAlert(AlertType.INFORMATION, "Sucesso", "Usuário atualizado com sucesso!");
                finalizarAcao();
            } else {
                showAlert(AlertType.ERROR, "Erro", "Falha ao atualizar usuário.");
            }
        }
    }
    
    @FXML
    private void handleRemover() {
        if (usuarioSelecionado != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Tem certeza que deseja remover o usuário " + usuarioSelecionado.getNome() + "?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Confirmação de Remoção");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    if (usuarioDAO.removerUsuario(usuarioSelecionado.getId())) {
                        showAlert(AlertType.INFORMATION, "Sucesso", "Usuário removido com sucesso!");
                        finalizarAcao();
                    } else {
                        showAlert(AlertType.ERROR, "Erro", "Falha ao remover usuário.");
                    }
                }
            });
        }
    }

    private void selecionarUsuario(Usuario usuario) {
        this.usuarioSelecionado = usuario;
        if (usuario != null) {
            idTextField.setText(String.valueOf(usuario.getId()));
            nomeTextField.setText(usuario.getNome());
            emailTextField.setText(usuario.getEmail());
            cpfTextField.setText(usuario.getCpf());
            loginTextField.setText(usuario.getLogin());
            cargoTextField.setText(usuario.getCargo());
            senhaField.setPromptText("Deixe em branco para não alterar");
            senhaField.clear();
            removerButton.setDisable(false);
        } else {
            limparCampos();
        }
    }
    
    private void finalizarAcao() {
        atualizarTabela();
        limparCampos();
    }
    
    @FXML
    private void handleNovo() {
        limparCampos();
    }

    private void limparCampos() {
        tabelaUsuarios.getSelectionModel().clearSelection();
        usuarioSelecionado = null;
        idTextField.clear();
        nomeTextField.clear();
        emailTextField.clear();
        cpfTextField.clear();
        loginTextField.clear();
        cargoTextField.clear();
        senhaField.clear();
        senhaField.setPromptText("Digite a senha");
        removerButton.setDisable(true);
        nomeTextField.requestFocus();
    }
    
    private boolean validarCampos() {
        String email = emailTextField.getText();
        if (nomeTextField.getText().isBlank() || email.isBlank() || loginTextField.getText().isBlank()) {
            showAlert(AlertType.WARNING, "Atenção", "Os campos Nome, Email e Login são obrigatórios.");
            return false;
        }
        if (usuarioSelecionado == null && senhaField.getText().isBlank()) {
            showAlert(AlertType.WARNING, "Atenção", "O campo Senha é obrigatório para novos usuários.");
            return false;
        }
        if (!ValidaEmail.isValido(email)) {
            showAlert(AlertType.WARNING, "Atenção", "O formato do e-mail é inválido.");
            return false;
        }
        // A checagem de email só deve ocorrer para novos usuários
        if (usuarioSelecionado == null && usuarioDAO.emailJaExiste(email)) {
            showAlert(AlertType.ERROR, "Erro", "O email '" + email + "' já está em uso.");
            return false;
        }
        return true;
    }

    private void atualizarTabela() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        tabelaUsuarios.getItems().setAll(usuarios);
    }

    @FXML
    private void handleConfigurarDb() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ConfigDbView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Configurar Conexão");
        stage.setScene(new Scene(loader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void handleSair() {
        Platform.exit();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}