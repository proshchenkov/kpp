package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class MyException extends Exception {
    private int detail;

    MyException(int а) {
        detail = а;
    }

    public String toString() {
        return "В массиве уже содержится цифра: " + detail;
    }
}

public class Pr4 {
    private static char[] enterArray(char[] chars) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            chars[i] = scanner.next().charAt(0);
        }
        return chars;
    }

    private static ArrayList enterIntArray(Scanner scanner) throws MyException {
        ArrayList intArray = new ArrayList();
        for (int i = 0; i < 5; i++) {
            int next = scanner.nextInt();
            if (intArray.contains(next)) {
                throw new MyException(next);
            }
            intArray.add(next);
        }
        return intArray;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Задание 1

        int y, x;
        x = scanner.nextInt();
        try {
            y = 26 / x;
            System.out.println(y);
        } catch (ArithmeticException zero) {
            System.out.println("Division by zero");
        }

        //Задание 2

        char[] chars = new char[2];
        try {
            enterArray(chars);
        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println(exc);
        }

        //Задание 3
        try {
            System.out.println(enterIntArray(scanner));
        } catch (MyException exc) {
            System.out.println(exc);
        }
    }
}