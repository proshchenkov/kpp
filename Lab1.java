package com.company;

import java.util.Random;

public class Lab1 {
    private static char[][] reverse(char[][] chars) {
        char[][] a = new char[n][l];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                a[i][(l - 1) - j] = chars[i][j];
            }
        }
        return a;
    }

    public static int n = 15, l = 3;

    public static void main(String[] args) {
        char[][] chars = new char[n][l];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                chars[i][j] = (char) (97 + new Random().nextInt(25));
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                System.out.print(chars[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        char[][] charsReverse = reverse(chars);
        char[] a = new char[l];
        char[] b = new char[l];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                a[j] = chars[i][l - j - 1];
            }
            for (int j = 0; j < l; j++) {
                b[j] = charsReverse[i][l - j - 1];
            }
            String sa = new String(a);
            String sb = new String(b);
            if (sa.contentEquals(sb)) {
                System.out.println(sa);
            }
        }
    }
}
