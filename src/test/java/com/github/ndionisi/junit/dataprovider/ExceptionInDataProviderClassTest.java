package com.github.ndionisi.junit.dataprovider;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests the case of an exception thrown in the {@link @DataProvider} method.
 * Enables to see how the failure looks like and how it is integrated in the IDE.
 */
@RunWith(DataProviderRunner.class)
public class ExceptionInDataProviderClassTest {

    @DataProvider
    public static Object[][] dataThrowingException() {
        throw new UnsupportedOperationException();
    }

    @Test
    @UseDataProvider("dataThrowingException")
    public void exceptionThrownInDataProvider(int executionCount) {
        // Should never go there since exception is thrown in method data
    }
}
