package br.com.ifba.atividade11.formas;

public class Quadrado extends Forma2d {
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double obterArea() {
        return lado * lado;
    }

    @Override
    public String toString() {
        return "Quadrado com lado " + lado;
    }
}