package edu.zhuravlev.katacalculator;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.*;

class DefaultConverter implements Converter{
    private static final DefaultConverter instance = new DefaultConverter();
    private final BiMap<Character, Integer> romeArabicMap = HashBiMap.create();
    private final int[] values;

    private DefaultConverter() {
        this.romeArabicMap.putAll(Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100
        ));
        this.values = romeArabicMap.values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .mapToInt(x -> x)
                .toArray();
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

    /*
        Worked correct under 399 in this realization
     */
    @Override
    public String convertArabicToRome(int arabic) {
        if (arabic == 1)
            return romeArabicMap.inverse().get(1).toString();
        else if (arabic == 0)
            return "";
        for (int i = 0; i < values.length; i++) {
            int onDiv = arabic / values[i];
            if (onDiv >= 1) {
                if(onDiv >= 2) {
                    if (onDiv >= 3) {
                        if (onDiv >= 4) {
                            return getSymbolByNumberMulti(values[i], 1) + getSymbolByNumberMulti(values[i - 1], 1) + convertArabicToRome(arabic - (values[i - 1] - values[i]));
                        }
                        return getSymbolByNumberMulti(values[i], 3)  + convertArabicToRome(arabic - 3 * values[i]);
                    }
                    return getSymbolByNumberMulti(values[i], 2) + convertArabicToRome(arabic - 2 * values[i]);
                }
                return getSymbolByNumberMulti(values[i], 1) + convertArabicToRome(arabic - values[i]);
            }
        }
        return "";
    }

    public Set<Character> getAvailableRomeCharacters() {
        return romeArabicMap.keySet();
    }

    private String getSymbolByNumberMulti(int num, int count) {
        var str = romeArabicMap.inverse().get(num).toString();
        var result = new StringBuilder();
        for (int i = 0; i < count; i++)
            result.append(str);
        return result.toString();
    }
}
