package com.company;

import java.util.Scanner;

public class Lab2 {
    private static String shift_encrypt(String text, int shift) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (alphabet.indexOf(text.charAt(i)) + shift + 1 > alphabet.length())
                s.append(alphabet.charAt(alphabet.indexOf(text.charAt(i)) - (alphabet.length() - shift)));
            else
                s.append(alphabet.charAt(alphabet.indexOf(text.charAt(i)) + shift));
        }
        return s.toString();
    }

    private static String shift_decrypt(String text, int shift) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (alphabet.indexOf(text.charAt(i)) + shift + 1 > alphabet.length())
                s.append(alphabet.charAt(alphabet.indexOf(text.charAt(i))));
            else
                s.append(alphabet.charAt(alphabet.indexOf(text.charAt(i))));
        }
        return s.toString();
    }

    private static String xor(String text) {
        StringBuilder s = new StringBuilder();
        String pass = "СЕКРЕТ";
        int key = 0;
        for (int i = 0; i < pass.length(); i++) key += pass.charAt(i);
        for (int i = 0; i < text.length(); i++) s.append((char) ((text.charAt(i)) ^ key));
        return s.toString();
    }

    private static String alphabet;

    public static void main(String[] args) {
        int menu, shift;
        Scanner scanner = new Scanner(System.in);
        String text;
        alphabet = " !,.0123456789?";
        for (int i = 1040; i < 1104; i++) alphabet += (char) i;
        while (true) {
            System.out.println("[1] - Циклическая замена");
            System.out.println("[2] - Исключающее или");
            System.out.println("[0] - Выход");
            menu = scanner.nextInt();
            if (menu == 1) {
                System.out.println("Введите текст:");
                text = scanner.next();
                System.out.println("Введите сдвиг:");
                shift = scanner.nextInt();
                shift %= alphabet.length();
                System.out.println("Зашифрованный текст:");
                System.out.println(shift_encrypt(text, shift));
                System.out.println("Расшифрованный текст:");
                System.out.println(shift_decrypt(text, shift));
            } else if (menu == 2) {
                System.out.println("Введите текст:");
                text = scanner.next();
                System.out.println("Зашифрованный текст:");
                System.out.println(xor(text));
                System.out.println("Расшифрованный текст:");
                System.out.println(xor(xor(text)));
            } else if (menu == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Вы ввели не существующий вариант");
            }
            System.out.println();
        }
    }
}
