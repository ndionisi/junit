package com.github.ndionisi.junit.parametrized;

import com.github.ndionisi.junit.JunitApplication;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
@SpringApplicationConfiguration(classes = JunitApplication.class)
public class ParametrizedClassWithSpringTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Inject
    private ApplicationContext applicationContext;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Collections.singletonList(
                new Object[]{1, "foo"}
        );
    }

    private int executionCount;
    private String string;

    public ParametrizedClassWithSpringTest(int executionCount, String string) {
        this.executionCount = executionCount;
        this.string = string;
    }

    @Test
    public void workingParametrizedClass() {
        assertThat(executionCount, equalTo(1));
        assertThat(string, equalTo("foo"));
        assertThat(applicationContext, notNullValue());
    }

}
