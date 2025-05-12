package br.com.ifba.atividade08;

// Classe que representa uma conta bancária simples
public class ContaBanco {
    // Atributos da conta
    private int numConta;        
    private String tipo;        
    private String dono;        
    private double saldo;         
    private boolean status;       

    // Construtor: inicializa uma conta fechada com saldo zero
    public ContaBanco() {
        this.saldo = 0;
        this.status = false; // Conta começa fechada
    }

    // Getters e Setters

    // Retorna o número da conta
    public int getNumConta() {
        return numConta;
    }

    // Define o número da conta (apenas se for positivo)
    public void setNumConta(int numConta) {
        if (numConta > 0) {
            this.numConta = numConta;
        } else {
            System.out.println("Número da conta inválido!");
        }
    }

    // Retorna o tipo da conta ("cc" ou "cp")
    public String getTipo() {
        return tipo;
    }

    // Define o tipo da conta, apenas se for "cc" ou "cp"
    public void setTipo(String tipo) {
        if (tipo != null && (tipo.equals("cc") || tipo.equals("cp"))) {
            this.tipo = tipo;
        } else {
            System.out.println("Tipo inválido! Use 'cc' ou 'cp'.");
            this.tipo = null; // Anula valor inválido
        }
    }

    // Retorna o nome do titular
    public String getDono() {
        return dono;
    }

    // Define o nome do titular (não pode ser nulo ou vazio)
    public void setDono(String dono) {
        if (dono != null && !dono.isEmpty()) {
            this.dono = dono;
        } else {
            System.out.println("Nome do titular inválido!");
        }
    }

    // Retorna o saldo da conta
    public double getSaldo() {
        return saldo;
    }

    // Retorna o status da conta (true = aberta, false = fechada)
    public boolean isStatus() {
        return status;
    }

    // Método para abrir a conta
    public boolean abrirConta(String tipo) {
        if (this.status) {
            System.out.println("Conta já está aberta!");
            return false;
        }

        setTipo(tipo); // Define o tipo com validação
        if (this.tipo == null) {
            System.out.println("Tipo de conta inválido! Não foi possível abrir.");
            return false;
        }

        this.status = true; // Marca conta como aberta

        // Define saldo inicial conforme o tipo de conta
        if (tipo.equals("cc")) {
            this.saldo = 50.0;
            System.out.println("Conta Corrente aberta com sucesso! Saldo inicial: R$ 50,00");
        } else if (tipo.equals("cp")) {
            this.saldo = 150.0;
            System.out.println("Conta Poupança aberta com sucesso! Saldo inicial: R$ 150,00");
        }

        return true;
    }

    // Método para fechar a conta (só se o saldo for zero)
    public void fecharConta() {
        if (!this.status) {
            System.out.println("Conta já está fechada!");
            return;
        }

        if (this.saldo > 0) {
            System.out.println("Não é possível fechar conta com saldo positivo. Saque R$ " + this.saldo + " primeiro.");
        } else if (this.saldo < 0) {
            System.out.println("Não é possível fechar conta em débito. Regularize seu saldo de R$ " + this.saldo);
        } else {
            this.status = false; // Fecha a conta
            System.out.println("Conta fechada com sucesso!");
        }
    }

    // Método para realizar depósito na conta
    public void depositar(double valor) {
        if (!this.status) {
            System.out.println("Operação não permitida: conta fechada!");
            return;
        }

        if (valor <= 0) {
            System.out.println("Valor de depósito inválido!");
            return;
        }

        this.saldo += valor; // Adiciona ao saldo
        System.out.printf("Depósito de R$ %.2f realizado com sucesso! Novo saldo: R$ %.2f\n", valor, this.saldo);
    }

    // Método para realizar saque da conta
    public boolean sacar(double valor) {
        if (!this.status) {
            System.out.println("Operação não permitida: conta fechada!");
            return false;
        }

        if (valor <= 0) {
            System.out.println("Valor de saque inválido!");
            return false;
        }

        if (valor > this.saldo) {
            System.out.println("Saldo insuficiente para saque!");
            return false;
        }

        this.saldo -= valor; // Subtrai do saldo
        System.out.printf("Saque de R$ %.2f realizado com sucesso! Novo saldo: R$ %.2f\n", valor, this.saldo);
        return true;
    }

    // Método para cobrar a taxa mensal da conta
    public void pagarMensal() {
        if (!this.status) {
            System.out.println("Operação não permitida: conta fechada!");
            return;
        }

        double taxa = 0;

        // Define a taxa conforme o tipo de conta
        if (this.tipo != null) {
            if (this.tipo.equals("cc")) {
                taxa = 12.0;
            } else if (this.tipo.equals("cp")) {
                taxa = 20.0;
            }
        }

        // Verifica se há saldo suficiente para pagar a taxa
        if (this.saldo >= taxa) {
            this.saldo -= taxa;
            System.out.printf("Taxa mensal de R$ %.2f debitada. Novo saldo: R$ %.2f\n", taxa, this.saldo);
        } else {
            System.out.println("Saldo insuficiente para pagar taxa mensal!");
        }
    }

    // Método para exibir o extrato da conta
    public void extrato() {
        System.out.println("\n=== EXTRATO BANCÁRIO ===");
        System.out.println("Conta: " + this.numConta);
        System.out.println("Tipo: " + (this.tipo != null && this.tipo.equals("cc") ? "Conta Corrente" :
                            (this.tipo != null && this.tipo.equals("cp") ? "Conta Poupança" : "Não definido")));
        System.out.println("Titular: " + this.dono);
        System.out.printf("Saldo atual: R$ %.2f\n", this.saldo);
        System.out.println("Status: " + (this.status ? "Ativa" : "Fechada"));
        System.out.println("========================");
    }
}
