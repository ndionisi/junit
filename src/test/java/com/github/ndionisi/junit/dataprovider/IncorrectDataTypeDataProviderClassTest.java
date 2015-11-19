package com.github.ndionisi.junit.dataprovider;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

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
