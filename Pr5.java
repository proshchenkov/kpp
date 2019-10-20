package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Pr5 {

    public static void main(String[] args) throws IOException {
        //Задание 1

        PrintWriter fileIn = new PrintWriter(new File("bonch.txt"));
        Date date = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
        fileIn.println(formatDate.format(date));
        fileIn.close();

        //Задание 2

        Scanner fileOut = new Scanner(new File("my.txt"));
        System.out.println(fileOut.nextLine());
        fileOut.close();

        //Задание 3

        Scanner scanner = new Scanner(System.in);
        int z = 10;
        while (z != 0) {
            System.out.println();
            System.out.println("[1] - Заполнить массив из восьми символьных строк и записать его в file1.txt");
            System.out.println("[2] - Прочитать содержимое file1.txt, извлекая из него слова, содержащие хотя бы\n" +
                    "одну букву 'a' и формируя из них отдельный массив, который записать в file2.txt.");
            System.out.println("[3] - Удалить оба файла");
            System.out.println("[0] - Выход");
            z = scanner.nextInt();
            if (z == 1) {
                System.out.println("Введите 8 строк");
                String[] strArray = new String[8];
                PrintWriter fileIn1 = new PrintWriter(new File("file1.txt"));
                for (int i = 0; i < strArray.length; i++) {
                    strArray[i] = scanner.next();
                }
                for (int i = 0; i < strArray.length; i++) {
                    fileIn1.println(strArray[i]);
                }
                fileIn1.close();
                System.out.println("Готово");
            } else if (z == 2) {
                Scanner fileOut1 = new Scanner(new File("file1.txt"));
                PrintWriter fileIn2 = new PrintWriter(new File("file2.txt"));
                ArrayList<String> strList = new ArrayList<>();
                String str;
                for (int i = 0; i < 8; i++) {
                    str = fileOut1.nextLine();
                    if (str.contains("a")) {
                        strList.add(str);
                    }
                }
                for (int i = 0; i < strList.size(); i++) {
                    fileIn2.println(strList.get(i));
                }
                fileOut1.close();
                fileIn2.close();
                System.out.println("Готово");
            } else if (z == 3) {
                File file1 = new File("file1.txt");
                File file2 = new File("file2.txt");
                file1.delete();
                file2.delete();
                System.out.println("Готово");
            } else if (z == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Вы ввели не существующий вариант");
            }
        }
    }
}
