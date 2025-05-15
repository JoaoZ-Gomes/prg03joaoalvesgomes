/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade09.Implementacoes;

import br.com.ifba.atividade09.intefaces.InterfacePagamento;
import javax.swing.JOptionPane;

/**
 *
 * @author jotin
 */
public class PagamentoPix implements InterfacePagamento {
     private double valor;
    public double calcularTotal(double valor){
        double cashback = valor * 0.02;
        return valor - cashback;
         }
    
     public void imprimirRecibo(String nome, String data, double valor, double valorFinal) {
        String recibo = """
            ----- Recibo de Pagamento (Pix) -----
            Nome do Cliente: %s
            Data da Compra: %s
            Valor do Produto: R$ %.2f
            Cash: 2%%
            Valor Final: R$ %.2f
            --------------------------------------
            """.formatted(nome, data, valor, valorFinal);

        JOptionPane.showMessageDialog(null, recibo, "Recibo", JOptionPane.INFORMATION_MESSAGE);
    }
    }
    

