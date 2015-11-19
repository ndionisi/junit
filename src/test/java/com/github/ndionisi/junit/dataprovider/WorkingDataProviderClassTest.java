package com.github.ndionisi.junit.dataprovider;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isOneOf;

@RunWith(DataProviderRunner.class)
public class WorkingDataProviderClassTest {

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {1, "foo"},
                {2, "bar"}
        };
    }

    @Test
    @UseDataProvider("data")
    public void workingDataProvider(int executionCount, String string) {
        assertThat(executionCount, isOneOf(1, 2));
        if (executionCount == 1) {
            assertThat(string, equalTo("foo"));
        } else {
            assertThat(string, equalTo("bar"));
        }
    }

}
