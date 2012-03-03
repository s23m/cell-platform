package org.s23m.cell.communication;

import org.s23m.cell.Set;

/**
 * Handles conversion to and from {@link Set} to type <code>T</code>
 * 
 * @param <T> the type to use in conversion
 */
public interface SetMarshaller<T> {
	
	T serialise(Set input) throws SetMarshallingException;
	
	Set deserialise(T input) throws SetMarshallingException;
}
