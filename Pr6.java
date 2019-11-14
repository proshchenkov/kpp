package com.company;

import java.util.Random;

class Characteristics {
    String[] direction = {"South", "West", "East"};
}

class Parameters {
    String[] speed = {"5", "1", "1"};
}

public class Pr6 {
    public static void main(String[] args) {
        Characteristics characteristics = new Characteristics();
        Parameters parameters = new Parameters();
        Random random = new Random();
        int sec = 15;
        int overall = 0;
        int[] direction = new int[sec];
        for (int i = 0; i < sec; i++) {
            direction[i] = random.nextInt(characteristics.direction.length);
            System.out.printf("[%d-%ds]: \t", i, i + 1);
            if (direction[i] == 0) {
                System.out.print("||\t");
            } else if (direction[i] == 1) {
                System.out.print("//\t");
            } else {
                System.out.print("\\\\\t");
            }
            System.out.printf("Направление: %s \t Скорость: %s m/s\n", characteristics.direction[direction[i]], parameters.speed[direction[i]]);
            overall += Integer.parseInt(parameters.speed[direction[i]]);
        }
        int x = 50;
        char dot = '\u00B7';
        for (int value : direction) {
            for (int j = 0; j < x; j++) {
                System.out.print(dot);
            }
            if (value == 0) {
                System.out.print("|");
            } else if (value == 1) {
                System.out.print("/");
                x--;
            } else {
                System.out.print("\\");
                x++;
            }
            for (int j = 0; j < 100 - x; j++) {
                System.out.print(dot);
            }
            System.out.println();
        }
        System.out.println("Overall: " + overall);
    }
}