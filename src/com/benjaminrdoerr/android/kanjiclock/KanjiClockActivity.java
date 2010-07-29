package com.benjaminrdoerr.android.kanjiclock;

import android.app.Activity;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class KanjiClockActivity extends Activity {

    private final static String J_AM = "\u5348\u524D";
    private final static String J_PM = "\u5348\u5F8C";
    private final static String J_YEAR = "\u5E74";
    private final static String J_MONTH = "\u6708";
    private final static String J_DAY = "\u6708";
    private final static String J_HOUR = "\u6642";
    private final static String J_MINUTE = "\u5206";
    private final static String J_SECOND = "\u79D2";

    private TextView dateText;
    private TextView monthText;
    private TextView yearText;
    private TextView hourText;
    private TextView minuteText;
    private TextView secondText;
    private TextView decasecondsText;

    private Calendar calendar;
    private Boolean tickerStopped = false;
    private Handler handler;
    private Runnable ticker;
    private KanjiNumber kanjiNumber;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initClock();
    }

    private void initClock() {
        initCalendar();
        initKanjiNumber();
        dateText = (TextView) findViewById(R.id.dateText);
        monthText = (TextView) findViewById(R.id.monthText);
        yearText = (TextView) findViewById(R.id.yearText);
        hourText = (TextView) findViewById(R.id.hourText);
        minuteText = (TextView) findViewById(R.id.minuteText);
        secondText = (TextView) findViewById(R.id.secondText);
        decasecondsText = (TextView) findViewById(R.id.decasecondsText);
    }

    private void initCalendar() {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
    }

    private void initKanjiNumber() {
        if (kanjiNumber == null) {
            kanjiNumber = new KanjiNumber();
        }
    }

    private void setDateText() {
        dateText.setText(getKanjiDay(calendar.get(Calendar.DAY_OF_MONTH)));
        monthText.setText(getKanjiMonth(calendar.get(Calendar.MONTH)));
        yearText.setText(getKanjiYear(calendar.get(Calendar.YEAR)));
        hourText.setText(getKanjiHour(calendar.get(Calendar.HOUR), calendar.get(Calendar.AM_PM)));
        minuteText.setText(getKanjiMinute(calendar.get(Calendar.MINUTE)));
        secondText.setText(getKanjiSecond(calendar.get(Calendar.SECOND)));
        decasecondsText.setText(getKanjiDecasecond(calendar.get(Calendar.MILLISECOND)));
    }

    private String getKanjiYear(Integer calendarYear) {
        return kanjiNumber.getKanji(calendarYear.toString()) + J_YEAR;
    }

    private String getKanjiMonth(Integer calendarMonth) {
        Integer unZeroIndex = 1;
        String monthString = ((Integer) (calendarMonth + unZeroIndex)).toString();
        return kanjiNumber.getKanji(monthString) + J_MONTH;
    }

    private String getKanjiDay(Integer calendarDay) {
        return kanjiNumber.getKanji(calendarDay.toString()) + J_DAY;
    }

    private String getKanjiHour(Integer calendarHour, Integer amPm) {
        return kanjiNumber.getKanji(calendarHour.toString()) + J_HOUR + (amPm == Calendar.AM ? J_AM : J_PM);
    }

    private String getKanjiMinute(Integer calendarMinute) {
        return kanjiNumber.getKanji(calendarMinute.toString()) + J_MINUTE;
    }

    private String getKanjiSecond(Integer calendarSecond) {
        return kanjiNumber.getKanji(calendarSecond.toString()) + J_SECOND;
    }

    private String getKanjiDecasecond(Integer calendarMilisecond) {
        Integer decaSecond = (calendarMilisecond / 100) + 1;
        return kanjiNumber.getKanji(decaSecond.toString());
    }

    /**
     * Called when the activity comes to the foreground.
     */
    @Override
    public void onResume() {
        tickerStopped = false;
        super.onResume();

        handler = new Handler();

        /**
         * requests a tick on the next hard-second boundary
         */
        ticker = new Runnable() {
            public void run() {
                if (tickerStopped) {
                    return;
                }
                calendar.setTimeInMillis(System.currentTimeMillis());
                setDateText();
                long now = SystemClock.uptimeMillis();
                long next = now + (100 - now % 100);
                handler.postAtTime(ticker, next);
            }
        };
        ticker.run();
    }

    /**
     * Called when the activity leaves the foreground.
     */
    @Override
    protected void onPause() {
        tickerStopped = true;
        super.onPause();
    }
}
