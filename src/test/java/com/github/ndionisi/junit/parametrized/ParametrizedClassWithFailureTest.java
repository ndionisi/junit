package com.github.ndionisi.junit.parametrized;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isOneOf;

@RunWith(Parameterized.class)
public class ParametrizedClassWithFailureTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{1, "foo"},
                new Object[]{2, "bar"},
                new Object[]{3, "test"}
        );
    }

    private int executionCount;
    private String string;

    public ParametrizedClassWithFailureTest(int executionCount, String string) {
        this.executionCount = executionCount;
        this.string = string;
    }

    @Test
    // Will fail on 2 of the 3 executions (enable to check integration of parametrized class with IDE in case of failure)
    public void failureParametrizedClass() {
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
