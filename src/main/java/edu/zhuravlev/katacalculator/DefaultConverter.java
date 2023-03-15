package edu.zhuravlev.katacalculator;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.*;

class DefaultConverter implements Converter{
    private static final DefaultConverter instance = new DefaultConverter();
    private final BiMap<Character, Integer> romeArabicMap = HashBiMap.create();

    private DefaultConverter() {
        this.romeArabicMap.putAll(Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100
        ));
    }

    public static Converter newConverter() {
        return instance;
    }

    @Override
    public int convertRomeToArabic(String rome) {
        char[] romeChars = rome.toCharArray();
        var charArray = new ArrayList<Character>(romeChars.length);
        for(var simb : romeChars)
            charArray.add(simb);
        var romeValues = charArray.stream()
                                        .map(romeArabicMap::get)
                                        .mapToInt(x -> x)
                                        .toArray();

        if (romeValues.length == 1)
            return romeValues[0];

        if (romeValues.length < 3)
            if (romeValues[0] == romeValues[1])
                return romeValues[0] * 2;
            else
                if (romeValues[1] > romeValues[0])
                    return romeValues[1] - romeValues[0];
                else
                    return romeValues[0] + romeValues[1];
        else {
            int sum = 0;
            for (int i = 0; i < romeValues.length; i++) {
                if (i <= romeValues.length - 2) {
                    if (romeValues[i] == romeValues[i + 1])
                        if (i <= romeValues.length - 3) {
                            if (romeValues[i] == romeValues[i + 2]) {
                                sum += romeValues[i] * 2;
                                i++;
                            } else
                                sum += romeValues[i];
                            continue;
                        }
                    if (romeValues[i + 1] > romeValues[i])
                        sum += romeValues[i + 1] - romeValues[i];
                    else
                        sum += romeValues[i] + romeValues[i + 1];
                    i++;
                } else
                    sum += romeValues[i];
            }
            return sum;
        }
    }

    @Override
    public String convertArabicToRome(int arabic) {
        return null;
    }

    public Set<Character> getAvailableRomeCharacters() {
        return romeArabicMap.keySet();
    }
}
