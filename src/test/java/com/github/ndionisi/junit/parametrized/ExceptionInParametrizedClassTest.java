package com.github.ndionisi.junit.parametrized;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

@RunWith(Parameterized.class)
public class ExceptionInParametrizedClassTest {

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		throw new UnsupportedOperationException();
	}

	@Test
	public void workingParametrizedClass() {
		// Should never go there since exception is thrown in method data
	}

}
