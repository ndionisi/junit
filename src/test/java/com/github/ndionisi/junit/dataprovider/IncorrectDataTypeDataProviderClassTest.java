package com.github.ndionisi.junit.dataprovider;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests the case of an incorrect data-type given by the {@link @DataProvider} method.
 * Enables to see how the failure looks like and how it is integrated in the IDE.
 */
@RunWith(DataProviderRunner.class)
public class IncorrectDataTypeDataProviderClassTest {

    @DataProvider
    public static Object[][] dataWithIncorrectDataType() {
        return new Object[][] {
                {1},
                {"foo"}
        };
    }

    @Test
    @UseDataProvider("dataWithIncorrectDataType")
    public void incorrectDataTypeInDataProvider(int executionCount, String string) {
        // Should never go there since initialization should fail
    }

}
