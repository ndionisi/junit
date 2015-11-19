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
public class DataProviderWithFailureClassTest {

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {1, "foo"},
                {2, "bar"},
                {3, "test"}
        };
    }

    @Test
    @UseDataProvider("data")
    // Will fail on 2 of the 3 executions (enable to check integration of parametrized class with IDE in case of failure)
    public void workingDataProvider(int executionCount, String string) {
        assertThat(executionCount, isOneOf(1, 2, 3));
        if (executionCount == 1) {
            assertThat(string, equalTo("bar"));
        } else if(executionCount == 2){
            assertThat(string, equalTo("foo"));
        } else {
            assertThat(string, equalTo("test"));
        }
    }

}
