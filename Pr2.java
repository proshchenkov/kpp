package com.company;

public class Pr2 {

    public static void main(String[] args) {
        //Задание 1

        int i = 10;
        double j = 2.5;
        if (i > j) {
            i = i >> 1;
        } else {
            i = i << 2;
        }
        System.out.println(i);

        //Задание 2

        char [] string = {'q','w','e','r','t','y','u','i','o','p'};
        char c=string[0];
        string[0]=string[9];
        string[9]=c;
        string[4]=string[1];
        string[1]=string[6];
        System.out.println(string);

        //Задание 3
        String string2 = "Thinking in Java, Bruce Eckel";
        System.out.println(string2);
        string2=string2.replace('a', ' ');
        System.out.println(string2);
    }
}
