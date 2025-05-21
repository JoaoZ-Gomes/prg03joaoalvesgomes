package br.com.ifba.atividade11.formas;

public class Esfera extends Forma3d{
    private double raio;

    public Esfera(double raio) {
        this.raio = raio;
    }

    @Override
    public double obterArea() {
        return 4 * Math.PI * raio * raio;
    }

    @Override
    public double obterVolume() {
        return (4.0/3.0) * Math.PI * raio * raio * raio;
    }

    @Override
    public String toString() {
        return "Esfera com raio " + raio;
    }
}