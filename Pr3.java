package com.company;

import java.util.Scanner;

public class Pr3 {

    private static String reverse(String s) {
        char[] a = s.toCharArray();
        char[] b = new char[a.length];
        for (int i = 0; i < a.length; i++) {
            b[(a.length - 1) - i] = a[i];
        }
        return new String(b);
    }

    public static void main(String[] args) {
        //Задание 1
        Scanner scanner = new Scanner(System.in);

        int[] intArray = new int[10];
        for (int i = 0; i < 10; i++) {
            intArray[i] = scanner.nextInt();
        }
        int max = 0, min = intArray[0];
        float avg = 0;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] > max) max = intArray[i];
        }
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] < min) min = intArray[i];
        }
        for (int i = 0; i < intArray.length; i++) {
            avg += intArray[i];
        }
        System.out.println("max= " + max);
        System.out.println("min= " + min);
        System.out.println("avg= " + avg / 10);

        //Задание 2

        int s = 8;
        String[] l = new String[s];
        String[] e = new String[s];
        int[] c = new int[s];
        int k = 1;
        for (int i = 0; i < s; i++) l[i] = scanner.nextLine();
        for (int i = 0; i < s; i++) e[i] = "*";
        for (int i = 0; i < s; i++) {
            for (int j = i + 1; j < s; j++) {
                if (l[i].equalsIgnoreCase(l[j])) {
                    e[j] = l[i];
                    for (int n = 0; n < j; n++)
                        if (e[j].equalsIgnoreCase(e[n])) e[n] = "*";
                }
            }
            for (int j = 0; j < s; j++)
                if (e[i].equalsIgnoreCase(l[j])) c[i]++;
        }
        for (int i = 0; i < s; i++) {
            if (!e[i].equals("*")) {
                System.out.printf("%d. %s (%d)\n", k, e[i], c[i]);
                k++;
            }
        }
        for (int i = 0; i < s; i++) System.out.printf("%s\t", reverse(l[i]));

        //Задание 3

        int length = 6;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if ((j == 3 && i == 1) || (j == 4 && i == 1) || (j == 2 && i == 5)) {
                    System.out.print('"');
                } else {
                    if ((j == 0 && i < length - 2 && i > 2) || (j == 1 && i < length - 1 && i > 1) || (j == 2 && i < length && i > 0) || (j == 3 && i < length - 1) || (j == 4 && i < length - 2 && i > 0) || (j == 5 && i < length - 3 && i > 1)) {
                        System.out.print(i + 1);
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
