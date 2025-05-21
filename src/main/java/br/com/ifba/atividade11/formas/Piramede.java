package br.com.ifba.atividade11.formas;

public class Piramede extends Forma3d {
    private double base;
    private double altura;
    private double ladoBase; // Para calcular a área da superfície lateral

    public Piramede(double ladoBase, double altura) {
        this.ladoBase = ladoBase;
        this.altura = altura;
        this.base = ladoBase * ladoBase; // Área da base quadrada
    }

    @Override
    public double obterArea() {
        // Área da base + área das 4 faces laterais (triângulos)
        double apotemaLateral = Math.sqrt(Math.pow(altura, 2) + Math.pow(ladoBase / 2, 2));
        double areaLateral = 4 * (0.5 * ladoBase * apotemaLateral);
        return base + areaLateral;
    }

    @Override
    public double obterVolume() {
        return (1.0/3.0) * base * altura;
    }

    @Override
    public String toString() {
        return "Pirâmide de base quadrada com lado da base " + ladoBase + " e altura " + altura;
    }
}