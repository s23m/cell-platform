package org.s23m.cell.communication;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;

/*
 * Simple demonstration of Java 8 features (should alert us to compilation issues)
 */
public class Java8DemoTest {
	
	@Test
	public void testFilter() 
	{
		Set<Integer> numbers = ImmutableSet.of(1, 2, 3, 4, 5, 6);
		
		Set<Integer> evenNumbers = numbers.stream()
				.filter(p -> p % 2 == 0)
				.collect(Collectors.toSet());
		assertEquals(ImmutableSet.of(2, 4, 6), evenNumbers);

		Set<Integer> oddNumbers = numbers.stream()
				.filter(p -> p % 2 == 1)
				.collect(Collectors.toSet());
		assertEquals(ImmutableSet.of(1, 3, 5), oddNumbers);
	}

}
