// Usuario.java (Model)

public class Usuario {
    // Atributos da classe, representando as colunas da tabela no banco de dados.
    private int id;
    private String nome;
    private String email;
    private String senha;

    // Construtor para inicializar um objeto Usuario com todos os dados.
    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // --- Getters (Métodos para obter os valores dos atributos) ---
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
    
    // O getter da senha geralmente não é exposto por segurança,
    // mas mantido aqui para fins didáticos.
    public String getSenha() {
        return senha;
    }

    // --- Setters (Métodos para alterar os valores dos atributos) ---
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    /**
     * Retorna uma representação em String do objeto Usuario,
     * ideal para exibição de dados.
     * @return Dados formatados do usuário.
     */
    public String exibirDados() {
        return "ID: " + id + " | Nome: " + nome + " | Email: " + email;
    }
}