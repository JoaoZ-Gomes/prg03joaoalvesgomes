
package br.com.ifba.atividade11.formas;

import br.com.ifba.atividade11.formas.*;

public class Main {
    public static void main(String[] args) {
        Forma[] formas = new Forma[6];
        formas[0] = new Circulo(5);
        formas[1] = new Quadrado(4);
        formas[2] = new Triangulo(3, 6);
        formas[3] = new Esfera(7);
        formas[4] = new Cubo(3);
        formas[5] = new Piramede(4, 5);

        for (Forma forma : formas) {
            System.out.println("Objeto: " + forma.toString());
            if (forma instanceof Forma2d) {
                Forma2d forma2d = (Forma2d) forma;
                System.out.println("Área: " + String.format("%.2f", forma2d.obterArea()));
            } else if (forma instanceof Forma3d) {
                Forma3d forma3d = (Forma3d) forma;
                System.out.println("Área da superfície: " + String.format("%.2f", forma3d.obterArea()));
                System.out.println("Volume: " + String.format("%.2f", forma3d.obterVolume()));
            }
            System.out.println("---");
        }
    }
}