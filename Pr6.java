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
        int overall2 = 0;
        int[] direction = new int[sec];
        int[] direction2 = new int[sec];
        for (int i = 0; i < sec; i++) {
            direction[i] = random.nextInt(characteristics.direction.length);
            direction2[i] = random.nextInt(characteristics.direction.length);
            overall += Integer.parseInt(parameters.speed[direction[i]]);
            overall2 += Integer.parseInt(parameters.speed[direction[i]]);
            /*
            System.out.printf("[%d-%ds]: \t", i, i + 1);
            if (direction[i] == 0) {
                System.out.print("||\t");
            } else if (direction[i] == 1) {
                System.out.print("//\t");
            } else {
                System.out.print("\\\\\t");
            }
            System.out.printf("Направление: %s \t Скорость: %s m/s\n", characteristics.direction[direction[i]], parameters.speed[direction[i]]);
            */
        }
        String[][] pos = new String[sec][sec];
        int x = 5, y = 10;
        for (int i = 0; i < sec; i++) {
            for (int j = 0; j < sec; j++) {
                pos[i][j] = "\u00B7";
            }
        }
        for (int i = 0; i < sec; i++) {
            if (direction[i] == 1) {
                pos[i][x] = "/";
                x--;
            } else if (direction[i] == 2) {
                pos[i][x] = "\\";
                x++;
            } else if (direction[i] == 0) {
                pos[i][x] = "|";
            }
        }
        for (int i = 0; i < sec; i++) {
            if (direction2[i] == 1) {
                pos[i][y] = "/";
                y--;
            } else if (direction2[i] == 2) {
                pos[i][y] = "\\";
                y++;
            } else if (direction2[i] == 0) {
                pos[i][y] = "|";
            }
        }
        for (int i = 0; i < sec; i++) {
            for (int j = 0; j < sec; j++) {
                System.out.printf("%s ", pos[i][j]);
            }
            System.out.println();
        }
        System.out.println("Overall: " + overall);
        System.out.println("Overall 2: " + overall2);
    }
}
