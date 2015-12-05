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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
@SpringApplicationConfiguration(classes = JunitApplication.class)
public class DataProviderWithTransactionsTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Inject
    private JdbcTemplate jdbcTemplate;

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                new Object[]{1, "foo"}
        };
    }

    /**
     * Creates table data_provider_test_transactional and inserts data into it. Since test method is transactional, it
     * should be rolled back at the end of the test (Spring default configuration).
     */
    @Test
    @Transactional
    @UseDataProvider("data")
    public void transactionalMethod(int executionCount, String string) {
        assertThat(executionCount, equalTo(1));
        assertThat(string, equalTo("foo"));
        jdbcTemplate.update("DROP TABLE IF EXISTS data_provider_test_transactional");
        jdbcTemplate.update("CREATE TABLE data_provider_test_transactional (id INTEGER NOT NULL)");
        jdbcTemplate.update("INSERT INTO data_provider_test_transactional (id) VALUES (1)");
    }

    /**
     * Creates table data_provider_test_non_transactional and inserts data into it. Since test method is *not*
     * transactional, it should *not* be rolled back at the end of the test.
     */
    @Test
    @UseDataProvider("data")
    public void nonTransactionalMethod(int executionCount, String string) {
        assertThat(executionCount, equalTo(1));
        assertThat(string, equalTo("foo"));
        jdbcTemplate.update("DROP TABLE IF EXISTS data_provider_test_non_transactional");
        jdbcTemplate.update("CREATE TABLE data_provider_test_non_transactional (id INTEGER NOT NULL)");
        jdbcTemplate.update("INSERT INTO data_provider_test_non_transactional (id) VALUES (1)");
    }

}
