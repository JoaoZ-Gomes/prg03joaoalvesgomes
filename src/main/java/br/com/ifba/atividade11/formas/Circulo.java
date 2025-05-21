package br.com.ifba.atividade11.formas;

public class Circulo extends Forma2d {
    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    @Override
    public double obterArea() {
        return Math.PI * raio * raio;
    }

    @Override
    public String toString() {
        return "Círculo com raio " + raio;
    }
}