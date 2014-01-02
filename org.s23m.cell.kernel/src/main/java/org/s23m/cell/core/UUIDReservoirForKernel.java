/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is S23M.
 *
 * The Initial Developer of the Original Code is
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.core;

import static org.s23m.cell.S23MKernel.coreSets;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.s23m.cell.core.collections.ImmutableOrderedUUIDSet;
import org.s23m.cell.core.collections.KernelUUIDSequence;
import org.s23m.cell.core.collections.KernelUUIDSequence.SequenceIdentifier;
import org.s23m.cell.core.collections.LazyValue;

class UUIDReservoirForKernel {
	/*
	 * Initial OSC values:
	 * 02848590-89cb-11df-a4ee-0800200c9a66 -> 3346548188874059411649264713034668646 [ -6562298810401514906, 181416740835234271]
	 * 02848591-89cb-11df-a4ee-0800200c9a66 -> 3346548268102221925913602306578618982 [ -6562298810401514906, 181416745130201567]
	 * 02848592-89cb-11df-a4ee-0800200c9a66 -> 3346548347330384440177939900122569318 [ -6562298810401514906, 181416749425168863]
	 * 
	 * Initial anonymous values:
	 * 02848590-89cb-11df-a4ee-0800200c9a67 -> 3346548188874059411649264713034668647 [ , 181416740835234271]
	 * 02848591-89cb-11df-a4ee-0800200c9a67 -> 3346548268102221925913602306578618983 [ -6562298810401514905, 181416745130201567]
	 * 02848592-89cb-11df-a4ee-0800200c9a67 -> 3346548347330384440177939900122569319 [ -6562298810401514905, 181416749425168863]
	 * 
	 * Initial Ext values:
	 * 02848590-89cb-11df-a4ee-0800200c9a68 -> 3346548188874059411649264713034668648 [ -6562298810401514904, 181416740835234271]
	 * 02848591-89cb-11df-a4ee-0800200c9a68 -> 3346548268102221925913602306578618984 [ -6562298810401514904, 181416745130201567]
	 * 02848592-89cb-11df-a4ee-0800200c9a68 -> 3346548347330384440177939900122569320 [ -6562298810401514904, 181416749425168863]
	 */

	private static final int MAX_NAMED_IDENTIFIERS = 5000;

	private static final int MAX_ANONYMOUS_IDENTIFIERS = 12000;

	private static final long STARTING_LEAST_SIGNIFICANT_BITS = 181416740835234271l;

	private final AtomicInteger anonymousIdentifierWrapper;

	private final LazyValue<ImmutableOrderedUUIDSet> reservoirForOpenSourceCoreHolder;

	private final LazyValue<ImmutableOrderedUUIDSet> reservoirForAnonymousUseHolder;

	private final LazyValue<ImmutableOrderedUUIDSet> reservoirForExtendedJavaBootstrappingHolder;

	public UUIDReservoirForKernel() {
		anonymousIdentifierWrapper = new AtomicInteger(0);

		reservoirForAnonymousUseHolder = createReservoir(SequenceIdentifier.ANONYMOUS_USE, MAX_ANONYMOUS_IDENTIFIERS);
		reservoirForOpenSourceCoreHolder = createReservoir(SequenceIdentifier.EXTENDED_JAVA_BOOTSTRAPPING, 10000);
		reservoirForExtendedJavaBootstrappingHolder = createReservoir(SequenceIdentifier.OPEN_SOURCE_CORE, 8000);
	}

	protected UUID getNextUUIDForAnonymousUse() {
		final int index = anonymousIdentifierWrapper.getAndIncrement();
		if (index < MAX_ANONYMOUS_IDENTIFIERS) {
			return reservoirForAnonymousUseHolder.get().get(index);
		} else {
			System.err.println("index is beyond max identifier for anonymous use: " + index);
			raiseError();
			return null;
		}
	}

	protected UUID getOpenSourceCoreUUID(final int nameRegistryIndex) {
		if (nameRegistryIndex < MAX_NAMED_IDENTIFIERS && nameRegistryIndex > -1) {
			return reservoirForOpenSourceCoreHolder.get().get(nameRegistryIndex);
		} else {
			System.err.println("index is beyond max identifier for open source core use: " + nameRegistryIndex);
			raiseError();
			return null;
		}
	}

	protected UUID getExtendedJavaBootstrappingUUID(final int nameRegistryIndex) {
		if (nameRegistryIndex < MAX_NAMED_IDENTIFIERS && nameRegistryIndex > -1) {
			return reservoirForExtendedJavaBootstrappingHolder.get().get(nameRegistryIndex);
		} else {
			System.err.println("index is beyond max identifier for extended Java bootstrapping use: " + nameRegistryIndex);
			raiseError();
			return null;
		}
	}

	private void raiseError() {
		F_InstantiationImpl.raiseError(coreSets.kernelDefect_KernelHasReachedAnIllegalState.identity(), coreSets.kernelDefect);
	}

	private LazyValue<ImmutableOrderedUUIDSet> createReservoir(final SequenceIdentifier sequenceIdentifier, final int sequenceLength) {
		return new LazyValue<ImmutableOrderedUUIDSet>() {
			@Override protected ImmutableOrderedUUIDSet computeValue() {
				final KernelUUIDSequence sequence = new KernelUUIDSequence(sequenceIdentifier, sequenceLength);
				return new ImmutableOrderedUUIDSet(sequence);
			}
		};
	}

	protected boolean isPartOfKernel(final UUID input) {
		return reservoirForOpenSourceCoreHolder.get().contains(input)
				|| reservoirForAnonymousUseHolder.get().contains(input)
				|| reservoirForExtendedJavaBootstrappingHolder.get().contains(input);
	}
}
