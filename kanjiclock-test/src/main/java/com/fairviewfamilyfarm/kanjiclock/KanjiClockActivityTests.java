package com.fairviewfamilyfarm.kanjiclock;

import android.app.Activity;
import android.test.ActivityTestCase;
import android.test.ActivityUnitTestCase;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

public class KanjiClockActivityTests extends ActivityUnitTestCase<KanjiClockActivity> {

    Activity activity;

    public KanjiClockActivityTests() {
        super(KanjiClockActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    /**
     * Test basic startup/shutdown of Application
     */
    @MediumTest
    public void testSomething() {

    }

}

