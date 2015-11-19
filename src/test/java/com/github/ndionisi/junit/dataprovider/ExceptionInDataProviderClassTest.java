package com.github.ndionisi.junit.dataprovider;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

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
