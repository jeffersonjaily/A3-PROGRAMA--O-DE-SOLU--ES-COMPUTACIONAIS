import java.util.List;
import java.util.Scanner;

public class UsuarioView {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Criar usuário");
        System.out.println("2 - Listar usuários");
        System.out.println("3 - Atualizar usuário");
        System.out.println("4 - Remover usuário");
        System.out.println("5 - Sair");
        System.out.println("------------");
    }

    public String[] solicitarDadosCriacao() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        return new String[]{nome, email, senha};
    }
    
    public String[] solicitarDadosAtualizacao() {
        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Email: ");
        String email = scanner.nextLine();
        System.out.print("Nova Senha: ");
        String senha = scanner.nextLine();
        return new String[]{nome, email, senha};
    }

    public int solicitarIdUsuario(String acao) {
        System.out.print("Digite o ID do usuário para " + acao + ": ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID inválido. Por favor, insira um número.");
            return -1;
        }
    }

    public void exibirListaUsuarios(List<Usuario> usuarios) {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        if (!usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.exibirDados());
            }
        }
        System.out.println("-------------------------");
    }
}