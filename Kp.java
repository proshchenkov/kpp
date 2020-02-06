package com.company;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Kp {
    private static Scanner scanner;
    private static long startTime;

    public static void main(String[] args) {
        int time;
        String surname, name, gr;
        scanner = new Scanner(System.in);
        System.out.println("Введите фамилию:");
        surname = scanner.next();
        System.out.println("Введите  имя:");
        name = scanner.next();
        System.out.println("Введите номер группы:");
        gr = scanner.next();
        do {
            System.out.println("Задайте время, отведенное на тестирование(в минутах)");
            while (!scanner.hasNextInt()) {
                System.out.println("Задайте время, отведенное на тестирование(в минутах)");
                scanner.next();
            }
            time = scanner.nextInt();
        } while (time < 0);
        System.out.println("Если Вы хотите досрочно прервать тестирование, введите '0'");
        startTime = System.currentTimeMillis();
        new Thread(new Time(time), "Time").start();
        Test questions = new Test();
        Result results = new Result(surname, name, gr);
        for (int i = 0; i < questions.countQ; i++) {
            int input = 0;
            do {
                questions.printQ(i);
                while (!scanner.hasNextInt()) {
                    questions.printQ(i);
                    scanner.next();
                }
                input = scanner.nextInt();
            } while (input < 0 || input > 4);
            questions.proverka(i, String.valueOf(input));
            if (input == 0) break;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Result.prResults(simpleDateFormat.format(System.currentTimeMillis() - startTime));
        Result.FileResults(simpleDateFormat.format(System.currentTimeMillis() - startTime));
        System.exit(0);
    }
}

class Time implements Runnable {
    private int time;
    private static long nTime;
    private static SimpleDateFormat simpleDateFormat;

    Time(int time) {
        this.time = time;
        simpleDateFormat = new SimpleDateFormat("mm:ss");
        nTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        for (; time > 0; time--) {// отсчёт времени
            try {
                Thread.sleep(60000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("\n\nК сожаление, время выполнения работы вышло!");
        Result.prResults(String.valueOf(simpleDateFormat.format(System.currentTimeMillis() - nTime)));
        Result.FileResults(simpleDateFormat.format(System.currentTimeMillis() - nTime));
        System.exit(0);
    }
}

class Test {
    public static int countA = 0;
    public static final int countQ = 20;
    //public boolean prav = true;
    private String[][] QArr = new String[20][6];
    private static final String URL = "Questions.txt";

    Test() {
        String line = "";
        int lineNumber = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.URL), "UTF-8"))) {
            while ((line = br.readLine()) != null) {
                String[] arrayForCopy = line.split("&");
                System.arraycopy(arrayForCopy, 0, QArr[lineNumber], 0, arrayForCopy.length);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printQ(int lineNumber) {
        System.out.println("\nВопрос №" + (lineNumber + 1));
        System.out.println(QArr[lineNumber][0]);
        System.out.println("1. " + QArr[lineNumber][1]);
        System.out.println("2. " + QArr[lineNumber][2]);
        System.out.println("3. " + QArr[lineNumber][3]);
        System.out.println("4. " + QArr[lineNumber][4]);
        System.out.print("\n> ");
    }

    public void proverka(int NumberQ, String answer) {
        if (answer.equals(QArr[NumberQ][5])) {
            countA++;
        }
    }

    public static int Mark() {
        if (((float) countA / countQ) >= 0.85) {
            return 5;
        } else if (((float) countA / countQ) >= 0.75) {
            return 4;
        } else if (((float) countA / countQ) >= 0.5) {
            return 3;
        } else {
            return 2;
        }
    }
}

class Result {
    private static String surname, name, gr;
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private static Calendar cal = Calendar.getInstance();
    private static final String URL = "Results.txt";

    Result(String surname, String name, String gr) {
        this.surname = surname;
        this.name = name;
        this.gr = gr;
    }

    public static void prResults(String time) {
        System.out.printf("%-12s%-12s%-12s%-17s%-19s%-17s%-17s%n", "Дата", "Фамилия", "Имя", "Группа",
                "Правильные ответы", "Оценка", "Время выполнения\n");
        System.out.printf("%-12s%-12s%-12s%-17s%-19s%-17s%-17s%n", dateFormat.format(cal.getTime()), surname,
                name, gr, Test.countA + " из " + Test.countQ, Test.Mark(), time + " min");
    }

    public static void FileResults(String time) {
        int lineNumber = 0;
        try {
            File proverka = new File("Results.txt");
            FileReader fileReader = new FileReader(proverka);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            while (lineNumberReader.readLine() != null) {
                lineNumber++;
            }
            lineNumberReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter fileWriter = new FileWriter("Results.txt", true);) {
            PrintWriter pw = new PrintWriter(fileWriter);
            if (lineNumber == 0) { //проверка, есть ли строки в файле с результатом
                pw.printf("%-6s%-12s%-12s%-12s%-17s%-19s%-17s%-17s%n", "№ п/п", "Дата", "Фамилия", "Имя", "Группа",
                        "Правильные ответы", "Оценка", "Время выполнения\n");
                pw.printf("%-6s%-12s%-12s%-12s%-17s%-19s%-17s%-17s%n", String.valueOf(lineNumber + 1),
                        dateFormat.format(cal.getTime()), surname, name, gr, Test.countA + " из " + Test.countQ,
                        Test.Mark(), time + " min");

            } else {
                pw.printf("%-6s%-12s%-12s%-12s%-17s%-19s%-17s%-17s%n", String.valueOf(lineNumber - 1),
                        dateFormat.format(cal.getTime()), surname, name, gr, Test.countA + " из " + Test.countQ,
                        Test.Mark(), time + " min");
            }
        } catch (IOException ex) {
        }
    }
}