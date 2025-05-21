package br.com.ifba.atividade11.formas;

public class Triangulo extends Forma2d {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double obterArea() {
        return 0.5 * base * altura;
    }

    @Override
    public String toString() {
        return "Triângulo com base " + base + " e altura " + altura;
    }
}