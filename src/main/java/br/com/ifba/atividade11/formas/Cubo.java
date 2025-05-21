package br.com.ifba.atividade11.formas;

public class Cubo extends Forma3d {
    private double lado;

    public Cubo(double lado) {
        this.lado = lado;
    }

    @Override
    public double obterArea() {
        return 6 * lado * lado;
    }

    @Override
    public double obterVolume() {
        return lado * lado * lado;
    }

    @Override
    public String toString() {
        return "Cubo com lado " + lado;
    }
}