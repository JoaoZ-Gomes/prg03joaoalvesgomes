package br.com.ifba.atividade10.view;

import br.com.ifba.atividade10.classes.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<PerfilUsuario> perfis = new ArrayList<>();
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Sessao> sessoes = new ArrayList<>();
    private static final List<LogAuditoria> logs = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        do {
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
            scanner.nextLine(); // limpa o buffer

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

    private static void criarPerfil() {
        System.out.print("ID do perfil: ");
        long id = scanner.nextLong(); scanner.nextLine();
        System.out.print("Descrição do perfil: ");
        String descricao = scanner.nextLine();

        System.out.print("Quantas permissões? ");
        int qtd = scanner.nextInt(); scanner.nextLine();

        List<String> permissoes = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            System.out.print("Permissão " + (i + 1) + ": ");
            permissoes.add(scanner.nextLine());
        }

        PerfilUsuario perfil = new PerfilUsuario(id, descricao, permissoes);
        perfis.add(perfil);
        System.out.println("Perfil criado com sucesso!");
    }

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

        System.out.println("Escolha um perfil:");
        for (int i = 0; i < perfis.size(); i++) {
            System.out.println((i + 1) + ". " + perfis.get(i).getDescricao());
        }
        int perfilIndex = scanner.nextInt() - 1;
        PerfilUsuario perfilSelecionado = perfis.get(perfilIndex);

        Usuario usuario = new Usuario(id, perfilSelecionado, nome, email, senha, null, true);
        usuarios.add(usuario);
        System.out.println("Usuário criado com sucesso!");
    }

    private static void logarUsuario() {
        System.out.print("Nome de usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Usuario u : usuarios) {
            if (u.getNomeUsuario().equals(nome) && u.getSenha().equals(senha)) {
                String token = UUID.randomUUID().toString();
                Sessao sessao = new Sessao((long) (sessoes.size() + 1), u, token);
                sessoes.add(sessao);
                u.setUltimoLogin(LocalDateTime.now());

                LogAuditoria log = new LogAuditoria((long) (logs.size() + 1), u, "Login", LocalDateTime.now(), "127.0.0.1");
                logs.add(log);

                System.out.println("Login realizado com sucesso!");
                return;
            }
        }
        System.out.println("Usuário ou senha incorretos.");
    }

    private static void deslogarUsuario() {
        System.out.print("Nome de usuário para deslogar: ");
        String nome = scanner.nextLine();

        Iterator<Sessao> it = sessoes.iterator();
        while (it.hasNext()) {
            Sessao s = it.next();
            if (s.getUsuario().getNomeUsuario().equals(nome)) {
                it.remove();

                LogAuditoria log = new LogAuditoria((long) (logs.size() + 1), s.getUsuario(), "Logout", LocalDateTime.now(), "127.0.0.1");
                logs.add(log);

                System.out.println("Sessão encerrada com sucesso.");
                return;
            }
        }
        System.out.println("Sessão não encontrada.");
    }

    private static void listarPerfis() {
        if (perfis.isEmpty()) {
            System.out.println("Nenhum perfil cadastrado.");
        } else {
            perfis.forEach(System.out::println);
        }
    }

    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            usuarios.forEach(System.out::println);
        }
    }

    private static void listarSessoes() {
        if (sessoes.isEmpty()) {
            System.out.println("Nenhuma sessão ativa.");
        } else {
            sessoes.forEach(System.out::println);
        }
    }

    private static void listarLogs() {
        if (logs.isEmpty()) {
            System.out.println("Nenhum log registrado.");
        } else {
            logs.forEach(System.out::println);
        }
    }
}
