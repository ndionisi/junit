package com.github.ndionisi.junit.dataprovider;

import com.github.ndionisi.junit.JunitApplication;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(DataProviderRunner.class)
@SpringApplicationConfiguration(classes = JunitApplication.class)
public class DataProviderWithSpringTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Inject
    private ApplicationContext applicationContext;


    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                new Object[]{1, "foo"}
        };
    }

    @Test
    @UseDataProvider("data")
    public void workingParametrizedClass(int executionCount, String string) {
        assertThat(executionCount, equalTo(1));
        assertThat(string, equalTo("foo"));
        assertThat(applicationContext, notNullValue());
    }

}
