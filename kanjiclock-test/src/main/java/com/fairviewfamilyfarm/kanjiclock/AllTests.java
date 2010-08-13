package com.fairviewfamilyfarm.kanjiclock;

import android.test.suitebuilder.TestSuiteBuilder;
import junit.framework.Test;
import junit.framework.TestSuite;
import com.synaptik.athena.AthenaTestCase;
import java.util.*;

public class AllTests extends AthenaTestCase {

    public static Test suite() {
        return new TestSuiteBuilder(AllTests.class)
                .includeAllPackagesUnderHere()
                .build();
    }

    public List<Class> getTestClasses() {
	List<Class> classes = new ArrayList();
        classes.add(KanjiClockActivityTests.class);
        return classes;
    }
}
