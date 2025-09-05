// Usuario.java (versão atualizada com perfil)
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String perfil; // Novo campo para "admin" ou "user"

    // Construtor atualizado para incluir o perfil
    public Usuario(int id, String nome, String email, String senha, String perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    // --- Getters ---
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getPerfil() { return perfil; } // Getter para o novo campo

    // --- Setters ---
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setPerfil(String perfil) { this.perfil = perfil; } // Setter para o novo campo
    
    public String exibirDados() {
        return "ID: " + id + " | Nome: " + nome + " | Email: " + email;
    }
}