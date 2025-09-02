// Main.java (Ponto de Entrada)
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioController controller = new UsuarioController();
        UsuarioView view = new UsuarioView();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            view.mostrarMenu();
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": // Criar
                    String[] dadosCriacao = view.solicitarDadosCriacao();
                    controller.criarUsuario(dadosCriacao[0], dadosCriacao[1], dadosCriacao[2]);
                    break;

                case "2": // Listar
                    List<Usuario> lista = controller.listarUsuarios();
                    view.exibirListaUsuarios(lista);
                    break;

                case "3": // Atualizar
                    int idAtualizar = view.solicitarIdUsuario("atualizar");
                    if (idAtualizar != -1) {
                        String[] dadosAtualizar = view.solicitarDadosAtualizacao();
                        controller.atualizarUsuario(idAtualizar, dadosAtualizar[0], dadosAtualizar[1], dadosAtualizar[2]);
                    }
                    break;

                case "4": // Remover
                    int idRemover = view.solicitarIdUsuario("remover");
                    if (idRemover != -1) {
                        controller.removerUsuario(idRemover);
                    }
                    break;

                case "5": // Sair
                    System.out.println("Encerrando o sistema...");
                    scanner.close(); // Boa prática fechar o scanner
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }java Main
}