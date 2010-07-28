package com.benjaminrdoerr.android.kanjiclock;

import android.os.Handler;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;

import java.util.Calendar;

public class KanjiClock extends TextView {

    private final static String j1 = "\u4E00";
    private final static String j2 = "\u4E8C";
    private final static String j3 = "\u4E09";
    private final static String j4 = "\u56DB";
    private final static String j5 = "\u4E94";
    private final static String j6 = "\u516D";
    private final static String j7 = "\u4E03";
    private final static String j8 = "\u516B";
    private final static String j9 = "\u4E5D";
    private final static String j0 = "\u3007";
    private final static String jAM = "\u5348\u524D";
    private final static String jPM = "\u5348\u5F8C";


    Calendar mCalendar;
    private final static String m12 = "h:mm:ss aa";
    private final static String m24 = "k:mm:ss";
    private FormatChangeObserver mFormatChangeObserver;

    private Runnable mTicker;
    private Handler mHandler;

    private boolean mTickerStopped = false;

    String mFormat;

    public KanjiClock(Context context) {
        super(context);
        initClock(context);
    }

    public KanjiClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClock(context);
    }

    private void initClock(Context context) {
        Resources r = context.getResources();

        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }

        mFormatChangeObserver = new FormatChangeObserver();
        getContext().getContentResolver().registerContentObserver(
                Settings.System.CONTENT_URI, true, mFormatChangeObserver);

        setFormat();
    }

    @Override
    protected void onAttachedToWindow() {
        mTickerStopped = false;
        super.onAttachedToWindow();
        mHandler = new Handler();

        /**
         * requests a tick on the next hard-second boundary
         */
        mTicker = new Runnable() {
                public void run() {
                    if (mTickerStopped) return;
                    mCalendar.setTimeInMillis(System.currentTimeMillis());
                    setText(repaceWithJapanese(DateFormat.format(mFormat, mCalendar)));
                    invalidate();
                    long now = SystemClock.uptimeMillis();
                    long next = now + (1000 - now % 1000);
                    mHandler.postAtTime(mTicker, next);
                }
            };
        mTicker.run();
    }

    CharSequence repaceWithJapanese(CharSequence formattedTime) {
        String time = formattedTime.toString();
        time = time.replace("1", j1);
        time = time.replace("2", j2);
        time = time.replace("3", j3);
        time = time.replace("4", j4);
        time = time.replace("5", j5);
        time = time.replace("6", j6);
        time = time.replace("7", j7);
        time = time.replace("8", j8);
        time = time.replace("9", j9);
        time = time.replace("0", j0);
        time = time.replace("AM", jAM);
        time = time.replace("PM", jPM);
        return time;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTickerStopped = true;
    }

    /**
     * Pulls 12/24 mode from system settings
     */
    private boolean get24HourMode() {
        String value = Settings.System.getString(
                getContext().getContentResolver(),
                Settings.System.TIME_12_24);

        if (value == null || value.equals("12"))
            return false;
        return true;
    }

    private void setFormat() {
        if (get24HourMode()) {
            mFormat = m24;
        } else {
            mFormat = m12;
        }
    }

    private class FormatChangeObserver extends ContentObserver {
        public FormatChangeObserver() {
            super(new Handler());
        }

        @Override
        public void onChange(boolean selfChange) {
            setFormat();
        }
    }
}
