package com.github.ndionisi.junit.parametrized;

import com.github.ndionisi.junit.JunitApplication;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
@SpringApplicationConfiguration(classes = JunitApplication.class)
public class ParametrizedClassWithTransactionsTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Collections.singletonList(
                new Object[]{1, "foo"}
        );
    }

    private int executionCount;
    private String string;

    public ParametrizedClassWithTransactionsTest(int executionCount, String string) {
        this.executionCount = executionCount;
        this.string = string;
    }

    /**
     * Creates table parametrized_test_transactional and inserts data into it. Since test method is transactional, it should be
     * rolled back at the end of the test (Spring default configuration).
     */
    @Test
    @Transactional
    public void transactionalMethod() {
        assertThat(executionCount, equalTo(1));
        assertThat(string, equalTo("foo"));
        jdbcTemplate.update("DROP TABLE IF EXISTS parametrized_test_transactional");
        jdbcTemplate.update("CREATE TABLE parametrized_test_transactional (id INTEGER NOT NULL)");
        jdbcTemplate.update("INSERT INTO parametrized_test_transactional (id) VALUES (1)");
    }

    /**
     * Creates table parametrized_test_non_transactional and inserts data into it. Since test method is *not* transactional, it
     * should *not* be rolled back at the end of the test.
     */
    @Test
    public void nonTransactionalMethod() {
        assertThat(executionCount, equalTo(1));
        assertThat(string, equalTo("foo"));
        jdbcTemplate.update("DROP TABLE IF EXISTS parametrized_test_non_transactional");
        jdbcTemplate.update("CREATE TABLE parametrized_test_non_transactional (id INTEGER NOT NULL)");
        jdbcTemplate.update("INSERT INTO parametrized_test_non_transactional (id) VALUES (1)");
    }

}
