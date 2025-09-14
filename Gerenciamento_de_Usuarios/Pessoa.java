// Pessoa.java

public abstract class Pessoa {
    private int id;
    private String nome;
    private String email;
    private String cpf;

    // Construtor
    public Pessoa(int id, String nome, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    // --- Getters e Setters ---
    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    // Método que pode ser sobrescrito pelas subclasses (Polimorfismo)
    public String exibirDados() {
        return "ID: " + id + " | Nome: " + nome + " | Email: " + email;
    }
}