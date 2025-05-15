/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade09.intefaces;

/**
 *
 * @author jotin
 */
public interface InterfacePagamento {
     
        double calcularTotal( double valor);
        void imprimirRecibo(String nome,String data,double valor,double valorFinal);   
    }

