package edu.zhuravlev.katacalculator;

public class Main {
    public static void main(String[] args) {
        Converter conv = DefaultConverter.newConverter();

        String rome = "IXVI";
        System.out.println(conv.convertRomeToArabic(rome));
    }

    public static String calc(String input) {
        return null;
    }
}