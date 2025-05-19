// Pacote da aplicação
package br.com.ifba.atividade10.view;

// Importa as classes necessárias
import br.com.ifba.atividade10.classes.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    // Scanner para entrada do usuário
    private static final Scanner scanner = new Scanner(System.in);

    // Listas para armazenar perfis, usuários, sessões e logs
    private static final List<PerfilUsuario> perfis = new ArrayList<>();
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Sessao> sessoes = new ArrayList<>();
    private static final List<LogAuditoria> logs = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        do {
            // Exibe o menu de opções
            System.out.println("\n=== MENU ===");
            System.out.println("1. Criar Perfil de Usuário");
            System.out.println("2. Criar Usuário");
            System.out.println("3. Logar (Criar Sessão)");
            System.out.println("4. Deslogar (Encerrar Sessão)");
            System.out.println("5. Ver Perfis");
            System.out.println("6. Ver Usuários");
            System.out.println("7. Ver Sessões");
            System.out.println("8. Ver Logs de Auditoria");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o buffer de entrada

            // Executa a ação com base na opção escolhida
            switch (opcao) {
                case 1 -> criarPerfil();
                case 2 -> criarUsuario();
                case 3 -> logarUsuario();
                case 4 -> deslogarUsuario();
                case 5 -> listarPerfis();
                case 6 -> listarUsuarios();
                case 7 -> listarSessoes();
                case 8 -> listarLogs();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // Cria um novo perfil com permissões
    private static void criarPerfil() {
        System.out.print("ID do perfil: ");
        long id = scanner.nextLong(); scanner.nextLine();
        System.out.print("Descrição do perfil: ");
        String descricao = scanner.nextLine();

        System.out.print("Quantas permissões? ");
        int qtd = scanner.nextInt(); scanner.nextLine();

        // Adiciona as permissões digitadas pelo usuário
        List<String> permissoes = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            System.out.print("Permissão " + (i + 1) + ": ");
            permissoes.add(scanner.nextLine());
        }

        // Cria e armazena o novo perfil
        PerfilUsuario perfil = new PerfilUsuario(id, descricao, permissoes);
        perfis.add(perfil);
        System.out.println("Perfil criado com sucesso!");
    }

    // Cria um novo usuário e associa a um perfil existente
    private static void criarUsuario() {
        if (perfis.isEmpty()) {
            System.out.println("Crie um perfil primeiro!");
            return;
        }

        System.out.print("ID do usuário: ");
        long id = scanner.nextLong(); scanner.nextLine();

        System.out.print("Nome de usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Exibe os perfis para o usuário escolher
        System.out.println("Escolha um perfil:");
        for (int i = 0; i < perfis.size(); i++) {
            System.out.println((i + 1) + ". " + perfis.get(i).getDescricao());
        }
        int perfilIndex = scanner.nextInt() - 1;
        PerfilUsuario perfilSelecionado = perfis.get(perfilIndex);

        // Cria o novo usuário
        Usuario usuario = new Usuario(id, perfilSelecionado, nome, email, senha, null, true);
        usuarios.add(usuario);
        System.out.println("Usuário criado com sucesso!");
    }

    // Realiza login do usuário, cria sessão e gera log de auditoria
    private static void logarUsuario() {
        System.out.print("Nome de usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Verifica se o nome e senha batem com algum usuário existente
        for (Usuario u : usuarios) {
            if (u.getNomeUsuario().equals(nome) && u.getSenha().equals(senha)) {
                // Cria um token de sessão único
                String token = UUID.randomUUID().toString();
                Sessao sessao = new Sessao((long) (sessoes.size() + 1), u, token);
                sessoes.add(sessao);

                // Atualiza o último login do usuário
                u.setUltimoLogin(LocalDateTime.now());

                // Cria log de login
                LogAuditoria log = new LogAuditoria((long) (logs.size() + 1), u, "Login", LocalDateTime.now(), "127.0.0.1");
                logs.add(log);

                System.out.println("Login realizado com sucesso!");
                return;
            }
        }
        System.out.println("Usuário ou senha incorretos.");
    }

    // Encerra a sessão de um usuário (logout)
    private static void deslogarUsuario() {
        System.out.print("Nome de usuário para deslogar: ");
        String nome = scanner.nextLine();

        Iterator<Sessao> it = sessoes.iterator();
        while (it.hasNext()) {
            Sessao s = it.next();
            if (s.getUsuario().getNomeUsuario().equals(nome)) {
                it.remove(); // Remove a sessão

                // Cria log de logout
                LogAuditoria log = new LogAuditoria((long) (logs.size() + 1), s.getUsuario(), "Logout", LocalDateTime.now(), "127.0.0.1");
                logs.add(log);

                System.out.println("Sessão encerrada com sucesso!.");
                return;
            }
        }
        System.out.println("Sessão não encontrada.");
    
    }

    // Lista todos os perfis cadastrados
    private static void listarPerfis() {
        if (perfis.isEmpty()) {
            System.out.println("Nenhum perfil cadastrado.");
        } else {
            perfis.forEach(System.out::println);
        }
    }

    // Lista todos os usuários cadastrados
    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            usuarios.forEach(System.out::println);
        }
    }

    // Lista todas as sessões ativas
    private static void listarSessoes() {
        if (sessoes.isEmpty()) {
            System.out.println("Nenhuma sessão ativa.");
        } else {
            sessoes.forEach(System.out::println);
        }
    }

    // Lista todos os logs de auditoria (login/logout)
    private static void listarLogs() {
        if (logs.isEmpty()) {
            System.out.println("Nenhum log registrado!!.  ");
        } else {
            logs.forEach(System.out::println);
        }
    } //github safadoassasdasdada
}
