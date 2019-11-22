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
        int sec = 15, overall = 0, overall2 = 0, x = 5, y = 10;
        int[] direction = new int[sec], direction2 = new int[sec];
        String[][] pos = new String[sec][sec];
        for (int i = 0; i < sec; i++) {
            for (int j = 0; j < sec; j++) {
                pos[i][j] = "\u00B7";
            }
        }
        for (int i = 0; i < sec; i++) {
            direction[i] = random.nextInt(characteristics.direction.length);
            direction2[i] = random.nextInt(characteristics.direction.length);
            if (direction[i] == 1) {
                pos[i][x] = "/";
                x--;
            } else if (direction[i] == 2) {
                pos[i][x] = "\\";
                x++;
            } else if (direction[i] == 0) {
                pos[i][x] = "|";
            }
            if (pos[i][y] == "\u00B7") {
                if (direction2[i] == 1) {
                    pos[i][y] = "//";
                    y--;
                } else if (direction2[i] == 2) {
                    pos[i][y] = "\\\\";
                    y++;
                } else if (direction2[i] == 0) {
                    pos[i][y] = "||";
                }
            } else {
                pos[i][y] = "X";
                break;
            }
            overall += Integer.parseInt(parameters.speed[direction[i]]);
            overall2 += Integer.parseInt(parameters.speed[direction2[i]]);
        }
        for (int i = 0; i < sec; i++) {
            for (int j = 0; j < sec; j++) {
                System.out.print(pos[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Overall: " + overall);
        System.out.println("Overall 2: " + overall2);
    }
}
