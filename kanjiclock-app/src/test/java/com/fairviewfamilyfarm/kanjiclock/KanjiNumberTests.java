package com.fairviewfamilyfarm.kanjiclock;

import junit.framework.TestCase;
import org.junit.Test;

public class KanjiNumberTests extends TestCase {
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {    
        super.tearDown();
    }

    @Test
    public void testGetKanji() {
        KanjiNumber kanjiNumber = new KanjiNumber();
        assertEquals(KanjiNumber.J_1, kanjiNumber.getKanji("1"));
        assertEquals(KanjiNumber.J_2, kanjiNumber.getKanji("2"));
        assertEquals(KanjiNumber.J_3, kanjiNumber.getKanji("3"));
        assertEquals(KanjiNumber.J_4, kanjiNumber.getKanji("4"));
        assertEquals(KanjiNumber.J_5, kanjiNumber.getKanji("5"));
        assertEquals(KanjiNumber.J_6, kanjiNumber.getKanji("6"));
        assertEquals(KanjiNumber.J_7, kanjiNumber.getKanji("7"));
        assertEquals(KanjiNumber.J_8, kanjiNumber.getKanji("8"));
        assertEquals(KanjiNumber.J_9, kanjiNumber.getKanji("9"));
        assertEquals(KanjiNumber.J_10, kanjiNumber.getKanji("10"));
        assertEquals(KanjiNumber.J_10 + KanjiNumber.J_1, kanjiNumber.getKanji("11"));
        assertEquals(KanjiNumber.J_10 + KanjiNumber.J_9, kanjiNumber.getKanji("19"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_10, kanjiNumber.getKanji("20"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_10 + KanjiNumber.J_1, kanjiNumber.getKanji("21"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_10 + KanjiNumber.J_9, kanjiNumber.getKanji("29"));
        assertEquals(KanjiNumber.J_9 + KanjiNumber.J_10 + KanjiNumber.J_9, kanjiNumber.getKanji("99"));
        assertEquals(KanjiNumber.J_100, kanjiNumber.getKanji("100"));
        assertEquals(KanjiNumber.J_100 + KanjiNumber.J_1, kanjiNumber.getKanji("101"));
        assertEquals(KanjiNumber.J_100 + KanjiNumber.J_9, kanjiNumber.getKanji("109"));
        assertEquals(KanjiNumber.J_100 + KanjiNumber.J_10, kanjiNumber.getKanji("110"));
        assertEquals(KanjiNumber.J_100 + KanjiNumber.J_10 + KanjiNumber.J_1, kanjiNumber.getKanji("111"));
        assertEquals(KanjiNumber.J_100 + KanjiNumber.J_10 + KanjiNumber.J_9, kanjiNumber.getKanji("119"));
        assertEquals(KanjiNumber.J_100 + KanjiNumber.J_9 + KanjiNumber.J_10, kanjiNumber.getKanji("190"));
        assertEquals(KanjiNumber.J_100 + KanjiNumber.J_9 + KanjiNumber.J_10 + KanjiNumber.J_1, kanjiNumber.getKanji("191"));
        assertEquals(KanjiNumber.J_100 + KanjiNumber.J_9 + KanjiNumber.J_10 + KanjiNumber.J_9, kanjiNumber.getKanji("199"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_100, kanjiNumber.getKanji("200"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_100 + KanjiNumber.J_1, kanjiNumber.getKanji("201"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_100 + KanjiNumber.J_9, kanjiNumber.getKanji("209"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_100 + KanjiNumber.J_10, kanjiNumber.getKanji("210"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_100 + KanjiNumber.J_10 + KanjiNumber.J_1, kanjiNumber.getKanji("211"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_100 + KanjiNumber.J_10 + KanjiNumber.J_9, kanjiNumber.getKanji("219"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_100 + KanjiNumber.J_9 + KanjiNumber.J_10, kanjiNumber.getKanji("290"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_100 + KanjiNumber.J_9 + KanjiNumber.J_10 + KanjiNumber.J_1, kanjiNumber.getKanji("291"));
        assertEquals(KanjiNumber.J_2 + KanjiNumber.J_100 + KanjiNumber.J_9 + KanjiNumber.J_10 + KanjiNumber.J_9, kanjiNumber.getKanji("299"));
        assertEquals(KanjiNumber.J_9 + KanjiNumber.J_100 + KanjiNumber.J_9 + KanjiNumber.J_10 + KanjiNumber.J_9, kanjiNumber.getKanji("999"));
        assertEquals(KanjiNumber.J_1000, kanjiNumber.getKanji("1000"));
        assertEquals(KanjiNumber.J_1000 + KanjiNumber.J_1, kanjiNumber.getKanji("1001"));
        assertEquals(KanjiNumber.J_1000 + KanjiNumber.J_10, kanjiNumber.getKanji("1010"));
        assertEquals(KanjiNumber.J_1000 + KanjiNumber.J_100, kanjiNumber.getKanji("1100"));
    }
}

