// Usuario.java

public class Usuario extends Pessoa {
    private String login;
    private String senha;
    private String cargo;
    private String perfil;

    // Construtor atualizado para incluir todos os campos e chamar o construtor da superclasse
    public Usuario(int id, String nome, String email, String cpf, String login, String senha, String cargo, String perfil) {
        // Chama o construtor da classe mãe (Pessoa)
        super(id, nome, email, cpf);
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
        this.perfil = perfil;
    }

    // --- Getters e Setters para campos específicos de Usuario ---
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
    
    // Sobrescrevendo o método da classe Pessoa para um comportamento específico (Polimorfismo)
    @Override
    public String exibirDados() {
        // Reutiliza o método da classe mãe e adiciona informações específicas
        return super.exibirDados() + " | Cargo: " + this.cargo;
    }
}