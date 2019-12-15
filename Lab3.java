package com.company;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class Lab3 {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        Scanner scanner = new Scanner(System.in);
        String name;
        fileManager.getHelp();
        int menu;
        while (true) {
            System.out.println(fileManager.getPath() + ">");
            menu = scanner.nextInt();
            if (menu == 1) {
                System.out.println("Введите директорию:");
                name = scanner.next();
                fileManager.changeDirectory(name);
            } else if (menu == 2) {
                System.out.println("Список файлов и директорий:");
                String[] list = fileManager.getList();
                for (String item : list) {
                    System.out.println(item);
                }
            } else if (menu == 3) {
                System.out.println("Введите имя файла:");
                name = scanner.next();
                fileManager.getProperties(name);
            } else if (menu == 4) {
                System.out.println("Введите имя файла:");
                name = scanner.next();
                fileManager.deleteFile(name);
            } else if (menu == 5) {
                System.out.println("Введите имя директории:");
                name = scanner.next();
                try {
                    fileManager.openFile(name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (menu == 6) {
                System.out.println("Введите имя файла:");
                name = scanner.next();
                try {
                    fileManager.createFile(name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (menu == 7) {
                System.out.println("Введите директорию:");
                name = scanner.next();
                scanner.next();
                fileManager.makeDirectory(name);
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

class FileManager {
    private String path = "C://";

    void getHelp() {
        System.out.println("[1] - Смена текущей директории");
        System.out.println("[2] - Список файлов и директорий");
        System.out.println("[3] - Свойства файла или директории");
        System.out.println("[4] - Удалить файл или директорию");
        System.out.println("[5] - Открыть файл");
        System.out.println("[6] - Создать файл");
        System.out.println("[7] - Создать директорию");
        System.out.println("[0] - Выход");
    }

    void deleteFile(String name) {
        File file = new File(path + "//" + name);
        file.delete();
    }

    String[] getList() {
        File file = new File(path);
        return file.list();
    }

    void getProperties(String name) {
        File file = new File(path + "//" + name);
        if (file.exists()) {
            if (file.isFile()) {
                System.out.println("Тип: Файл");
                System.out.println("Размер: " + file.length());
                System.out.println("Изменен: " + new Date(file.lastModified()));
            }
            if (file.isDirectory()) {
                System.out.println("Тип: Директория");
                System.out.println("Размер: " + file.length());
                System.out.println("Содержит: " + file.list().length + " объектов");
            }
        } else System.out.println("Такого файла или директории не существует");
    }

    void createFile(String name) throws IOException {
        File file = new File(path + "//" + name);
        if (!file.exists()) {
            file.createNewFile();
            Runtime.getRuntime().exec("cmd /c notepad " + file);
        } else System.out.println("Файл уже существует");
    }

    void makeDirectory(String name) {
        File file = (new File(path + "//" + name));
        if (file.mkdir()) {
            System.out.println("Директория создана");
        } else {
            System.out.println("Ошибка создания директории");
        }
    }

    void changeDirectory(String name) {
        if (name.equals("..")) {
            String[] trim = path.split("//");
            path = "";
            for (int i = (trim.length - 2); i > -1; i--) {
                if (path.equals("")) {
                    path = trim[i] + path;
                    continue;
                }
                path = trim[i] + "//" + path;
            }
        } else {
            File file = (new File(path + "//" + name));
            if ((file.isDirectory()) && (file.exists())) {
                path += ("//" + name);
            } else System.out.println("Ошибка смены директории");
        }
    }

    String getPath() {
        return new File(path).toPath().toString();
    }

    void openFile(String name) throws IOException {
        File file = new File(path + "//" + name);
        if (file.exists()) {
            if (file.isFile()) {
                Runtime.getRuntime().exec("cmd /c notepad " + file);
            } else System.out.println("Это не файл");
        } else System.out.println("Такого файла не существует");
    }
}
