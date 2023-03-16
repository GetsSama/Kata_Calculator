package edu.zhuravlev.katacalculator;

import java.util.Set;

interface Converter {
    int convertRomeToArabic(String rome);
    String convertArabicToRome(int arabic);

    Set<Character> getAvailableRomeCharacters();
}
