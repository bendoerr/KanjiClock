package com.benjaminrdoerr.android.kanjiclock;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;


public class KanjiNumber {

    public final static String J_0 = "\u96F6";
    public final static String J_1 = "\u4E00";
    public final static String J_2 = "\u4E8C";
    public final static String J_3 = "\u4E09";
    public final static String J_4 = "\u56DB";
    public final static String J_5 = "\u4E94";
    public final static String J_6 = "\u516D";
    public final static String J_7 = "\u4E03";
    public final static String J_8 = "\u516B";
    public final static String J_9 = "\u4E5D";
    public final static String J_10 = "\u5341";
    public final static String J_100 = "\u767E";
    public final static String J_1000 = "\u5343";

    public KanjiNumber() {
    }

    public String getKanji(String arabicNumber) {
        try {
            Integer.parseInt(arabicNumber);
        } catch (NumberFormatException ex) {
            return "";
        }

        return parseArabicNumber(arabicNumber);
    }

    private String parseArabicNumber(String arabicNumber) {
        Integer numberLength = arabicNumber.length();
        Integer currentDigit = 1;
        String kanji = "";
        CharacterIterator it = new StringCharacterIterator(arabicNumber);

        for (char digit = it.last(); digit != CharacterIterator.DONE; digit = it.previous()) {

            Boolean isPrintableTens = currentDigit > 1 && digit != '0';
            if (isPrintableTens) {
                switch (currentDigit) {
                    case 2:
                        kanji = J_10 + kanji;
                        break;
                    case 3:
                        kanji = J_100 + kanji;
                        break;
                    case 4:
                        kanji = J_1000 + kanji;
                        break;
                    default:
                        break;
                }
            }

            Boolean isPrintableDigit = true;

            Boolean isFirstDigit = currentDigit == 1;
            Boolean isFirstDigitPrintable = numberLength == 1 || digit != '0';
            isPrintableDigit = !isFirstDigit || isFirstDigitPrintable;

            if (!isFirstDigit) {
                isPrintableDigit = digit != '1' && digit != '0';
            }

            if (isPrintableDigit) {
                kanji = digitToKanji(digit) + kanji;
            }

            currentDigit++;
        }

        return kanji;
    }

    private Character iteratorPeek(CharacterIterator iterator) {
        int currentIndex = iterator.getIndex();
        Character peeked = iterator.next();
        iterator.setIndex(currentIndex);
        return peeked;
    }

    private String digitToKanji(char digit) {
        String digitString;
        switch (digit) {
            case '0':
                digitString = J_0;
                break;
            case '1':
                digitString = J_1;
                break;
            case '2':
                digitString = J_2;
                break;
            case '3':
                digitString = J_3;
                break;
            case '4':
                digitString = J_4;
                break;
            case '5':
                digitString = J_5;
                break;
            case '6':
                digitString = J_6;
                break;
            case '7':
                digitString = J_7;
                break;
            case '8':
                digitString = J_8;
                break;
            case '9':
                digitString = J_9;
                break;
            default:
                digitString = "";
                break;
        }
        return digitString;
    }

}
