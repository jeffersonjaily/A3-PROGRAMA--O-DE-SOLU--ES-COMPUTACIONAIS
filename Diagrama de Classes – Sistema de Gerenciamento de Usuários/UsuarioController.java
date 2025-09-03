import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Optional;
import javafx.scene.control.ButtonType;

public class UsuarioController {

    // --- Componentes da Interface Gráfica (injetados pelo FXML) ---
    @FXML private TextField idTextField;
    @FXML private TextField nomeTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField senhaField;
    @FXML private Button salvarButton;
    @FXML private Button novoButton;
    @FXML private Button removerButton;
    @FXML private TableView<Usuario> tabelaUsuarios;
    @FXML private TableColumn<Usuario, Integer> colunaId;
    @FXML private TableColumn<Usuario, String> colunaNome;
    @FXML private TableColumn<Usuario, String> colunaEmail;
    
    private UsuarioDAO usuarioDAO;
    private Usuario usuarioSelecionado;

    // O método initialize é chamado automaticamente pelo JavaFX após o FXML ser carregado
    @FXML
    public void initialize() {
        this.usuarioDAO = new UsuarioDAO();
        
        // Configura as colunas da tabela para saber de onde pegar os dados da classe Usuario
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Adiciona um "listener" para saber quando um usuário é selecionado na tabela
        tabelaUsuarios.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> selecionarUsuario(newValue)
        );

        atualizarTabela();
    }

    // --- Métodos de Ação dos Botões ---

    @FXML
    private void handleNovo() {
        limparCampos();
    }
    
    @FXML
    private void handleSalvar() {
        // Se não há usuário selecionado, é um novo cadastro
        if (usuarioSelecionado == null) {
            criarUsuario();
        } else { // Se há um usuário selecionado, é uma atualização
            atualizarUsuario();
        }
    }

    @FXML
    private void handleRemover() {
        if (usuarioSelecionado != null) {
            // Pede confirmação antes de remover
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Remoção");
            alert.setHeaderText("Tem certeza que deseja remover o usuário?");
            alert.setContentText(usuarioSelecionado.getNome());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                removerUsuario();
            }
        }
    }
    
    // --- Lógica de Negócio ---

    private void criarUsuario() {
        if (!validarCampos()) return;
        
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        String senha = senhaField.getText();

        if (usuarioDAO.emailJaExiste(email)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "O email '" + email + "' já está em uso.");
            return;
        }

        Usuario novoUsuario = new Usuario(0, nome, email.toLowerCase(), senha);
        if (usuarioDAO.criarUsuario(novoUsuario)) {
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Usuário criado com sucesso!");
            atualizarTabela();
            limparCampos();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao criar usuário.");
        }
    }

    private void atualizarUsuario() {
        if (!validarCampos()) return;

        usuarioSelecionado.setNome(nomeTextField.getText());
        usuarioSelecionado.setEmail(emailTextField.getText().toLowerCase());
        usuarioSelecionado.setSenha(senhaField.getText());

        if (usuarioDAO.atualizarUsuario(usuarioSelecionado)) {
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Usuário atualizado com sucesso!");
            atualizarTabela();
            limparCampos();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao atualizar usuário.");
        }
    }
    
    private void removerUsuario() {
         if (usuarioDAO.removerUsuario(usuarioSelecionado.getId())) {
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Usuário removido com sucesso!");
            atualizarTabela();
            limparCampos();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao remover usuário.");
        }
    }

    // --- Métodos Auxiliares ---

    private void atualizarTabela() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        tabelaUsuarios.getItems().setAll(usuarios);
    }

    private void selecionarUsuario(Usuario usuario) {
        this.usuarioSelecionado = usuario;
        if (usuario != null) {
            idTextField.setText(String.valueOf(usuario.getId()));
            nomeTextField.setText(usuario.getNome());
            emailTextField.setText(usuario.getEmail());
            senhaField.setText(""); // Senha não é preenchida por segurança
            removerButton.setDisable(false);
        } else {
            limparCampos();
        }
    }

    private void limparCampos() {
        usuarioSelecionado = null;
        idTextField.clear();
        nomeTextField.clear();
        emailTextField.clear();
        senhaField.clear();
        tabelaUsuarios.getSelectionModel().clearSelection();
        removerButton.setDisable(true);
    }
    
    private boolean validarCampos() {
        String email = emailTextField.getText();
        if (nomeTextField.getText().isEmpty() || email.isEmpty() || senhaField.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Atenção", "Todos os campos devem ser preenchidos.");
            return false;
        }
        if (!ValidaEmail.isValido(email)) {
            showAlert(Alert.AlertType.WARNING, "Atenção", "O formato do e-mail é inválido.");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}