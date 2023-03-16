package edu.zhuravlev.katacalculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Converter conv = DefaultConverter.newConverter();

        String rome = "CCCLLLXXXVVVIII";
        int arabic = 0;
        try (var scn = new Scanner(System.in)) {
            while (scn.hasNext()) {
                arabic = scn.nextInt();
                System.out.println(conv.convertArabicToRome(arabic));
            }
        }
    }

    public static String calc(String input) {
        return null;
    }
}