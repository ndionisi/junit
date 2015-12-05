package com.github.ndionisi.junit.parametrized;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Tests the case of an incorrect data-type given by the {@link @Parameterized.Parameters} method.
 * Enables to see how the failure looks like and how it is integrated in the IDE.
 */
@RunWith(Parameterized.class)
public class IncorrectDataTypeParametrizedClassTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{1},
                new Object[]{"foo"}
        );
    }

    private String string;

    public IncorrectDataTypeParametrizedClassTest(String string) {
        this.string = string;
    }

    @Test
    public void workingParametrizedClass(int anInt) {
        // Should never go there since initialization should fail
    }

}
