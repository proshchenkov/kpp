package com.company;

import java.util.Scanner;

public class Pr1 {

    public static void main(String[] args) {
        //Задание 1
        String name = "James Gosling";
        System.out.println(name + " is author of Java ");

        //Задание 2
        Scanner scanner = new Scanner(System.in);
        float x = scanner.nextFloat();
        float b = scanner.nextFloat();
        System.out.println(12 * x + b);

        //Задание 3
        int m = 1, n = 2, k = 3;
        if (m < b) {
            if (m < k) {
                System.out.println(m);
            } else {
                System.out.println(k);
            }
        } else {
            if (n < k) {
                System.out.println(n);
            } else {
                System.out.println(k);
            }
        }

    }
}
