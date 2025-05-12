package br.com.ifba.atividade08;

import java.util.Scanner; // Importa a classe Scanner para entrada de dados pelo teclado

public class SistemaBancario {

    // Objeto conta começa como nulo (nenhuma conta criada ainda)
    private static ContaBanco conta = null;

    // Scanner para ler entradas do usuário
    private static Scanner scanner = new Scanner(System.in);

    // Método principal do sistema bancário
    public static void main(String[] args) {
        int opcao = -1; // Variável para armazenar a opção escolhida pelo usuário

        System.out.println("Bem-vindo ao Sistema Bancário!");

        // Loop principal do menu até o usuário escolher sair (opção 0)
        do {
            System.out.println("\n===== MENU BANCÁRIO =====");

            // Mostra opções com base no estado da conta
            if (conta == null || !conta.isStatus()) {
                System.out.println("1 - Abrir Conta");
            } else {
                System.out.println("2 - Depositar");
                System.out.println("3 - Sacar");
                System.out.println("4 - Pagar Mensalidade");
                System.out.println("5 - Exibir Extrato");
                System.out.println("6 - Fechar Conta");
            }

            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt(); // Lê a opção escolhida
            scanner.nextLine(); // Limpa o buffer do scanner

            // Verifica a opção e executa a ação correspondente
            switch (opcao) {
                case 1:
                    // Abrir conta (caso ainda não exista ou esteja fechada)
                    if (conta == null || !conta.isStatus()) {
                        System.out.println("\n=== ABERTURA DE CONTA ===");
                        System.out.print("Digite o número da conta: ");
                        int numConta = scanner.nextInt();
                        scanner.nextLine(); // Limpa buffer
                        System.out.print("Digite o nome do titular: ");
                        String dono = scanner.nextLine();
                        System.out.print("Digite o tipo da conta (cc para corrente, cp para poupança): ");
                        String tipo = scanner.nextLine();

                        conta = new ContaBanco();       // Cria nova conta
                        conta.setNumConta(numConta);    // Define número
                        conta.setDono(dono);            // Define titular
                        conta.abrirConta(tipo);         // Abre a conta
                    } else {
                        System.out.println("Conta já está aberta!");
                    }
                    break;

                case 2:
                    // Depositar em conta ativa
                    if (conta != null && conta.isStatus()) {
                        System.out.println("\n=== DEPOSITAR ===");
                        System.out.print("Digite o valor a ser depositado: R$ ");
                        double valorDeposito = scanner.nextDouble();
                        scanner.nextLine(); // Limpa buffer
                        conta.depositar(valorDeposito);
                    } else {
                        System.out.println("Nenhuma conta aberta para depósito.");
                    }
                    break;

                case 3:
                    // Sacar de conta ativa
                    if (conta != null && conta.isStatus()) {
                        System.out.println("\n=== SACAR ===");
                        System.out.print("Digite o valor a ser sacado: R$ ");
                        double valorSaque = scanner.nextDouble();
                        scanner.nextLine(); // Limpa buffer
                        conta.sacar(valorSaque);
                    } else {
                        System.out.println("Nenhuma conta aberta para saque.");
                    }
                    break;

                case 4:
                    // Pagar mensalidade da conta
                    if (conta != null && conta.isStatus()) {
                        System.out.println("\n=== PAGAR MENSALIDADE ===");
                        conta.pagarMensal();
                    } else {
                        System.out.println("Nenhuma conta aberta para pagar mensalidade.");
                    }
                    break;

                case 5:
                    // Mostrar extrato bancário
                    if (conta != null && conta.isStatus()) {
                        System.out.println("\n=== EXTRATO ===");
                        conta.extrato();
                    } else {
                        System.out.println("Nenhuma conta aberta para exibir extrato.");
                    }
                    break;

                case 6:
                    // Fechar a conta (se saldo estiver zerado)
                    if (conta != null && conta.isStatus()) {
                        System.out.println("\n=== FECHAR CONTA ===");
                        conta.fecharConta(); // Tenta fechar a conta
                        if (!conta.isStatus()) {
                            conta = null; // Se conseguiu fechar, apaga a referência
                        }
                    } else {
                        System.out.println("Nenhuma conta aberta para fechar.");
                    }
                    break;

                case 0:
                    // Sair do sistema
                    System.out.println("Saindo do sistema bancário. Até logo!");
                    break;

                default:
                    // Opção inválida
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0); // Repete até o usuário escolher sair

        scanner.close(); // Encerra o scanner
    }
}
