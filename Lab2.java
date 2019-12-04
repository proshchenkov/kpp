package com.company;

import java.util.Scanner;

public class Lab2 {
    private static String shift_encrypt(String text, int shift) {
        String str = "";
        for (int i = 0; i < text.length(); i++) {
            if (alphabet.indexOf(text.charAt(i)) + shift + 1 > alphabet.length())
                str += alphabet.charAt(alphabet.indexOf(text.charAt(i)) - (alphabet.length() - shift));
            else
                str += alphabet.charAt(alphabet.indexOf(text.charAt(i)) + shift);
        }
        return str;
    }

    private static String shift_decrypt(String text, int shift) {
        String str = "";
        for (int i = 0; i < text.length(); i++) {
            if (alphabet.indexOf(text.charAt(i)) + shift + 1 > alphabet.length())
                str += alphabet.charAt(alphabet.indexOf(text.charAt(i)));
            else
                str += alphabet.charAt(alphabet.indexOf(text.charAt(i)));
        }
        return str;
    }

    private static String xor(String text) {
        String str = "";
        String pass = "СЕКРЕТ";
        for (int i = 0; i < text.length(); i++) str += (char) ((text.charAt(i)) ^ pass.charAt(i % 6));
        return str;
    }

    private static String alphabet;

    public static void main(String[] args) {
        int menu, shift;
        String text;
        alphabet = " !,-.0123456789?";
        Scanner scanner = new Scanner(System.in);
        for (int i = 1040; i < 1104; i++) alphabet += (char) i;
        for (int i = 65; i < 91; i++) alphabet += (char) i;
        for (int i = 97; i < 123; i++) alphabet += (char) i;
        while (true) {
            System.out.println("[1] - Циклическая замена");
            System.out.println("[2] - Исключающее или");
            System.out.println("[0] - Выход");
            menu = scanner.nextInt();
            if (menu == 1) {
                System.out.println("Введите текст:");
                scanner = new Scanner(System.in);
                text = scanner.nextLine();
                System.out.println("Введите сдвиг:");
                scanner = new Scanner(System.in);
                shift = scanner.nextInt();
                shift %= alphabet.length();
                System.out.println("Зашифрованный текст:");
                System.out.println(shift_encrypt(text, shift));
                System.out.println("Расшифрованный текст:");
                System.out.println(shift_decrypt(text, shift));
            } else if (menu == 2) {
                System.out.println("Введите текст:");
                scanner = new Scanner(System.in);
                text = scanner.nextLine();
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
