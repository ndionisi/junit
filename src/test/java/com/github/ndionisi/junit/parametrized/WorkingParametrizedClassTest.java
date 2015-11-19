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
public class WorkingParametrizedClassTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{1, "foo"},
                new Object[]{2, "bar"}
        );
    }

    private int executionCount;
    private String string;

    public WorkingParametrizedClassTest(int executionCount, String string) {
        this.executionCount = executionCount;
        this.string = string;
    }

    @Test
    public void workingParametrizedClass() {
        assertThat(executionCount, isOneOf(1, 2));
        if (executionCount == 1) {
            assertThat(string, equalTo("foo"));
        } else {
            assertThat(string, equalTo("bar"));
        }
    }

}
