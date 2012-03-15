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
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Timer {

	private final Map<String, TimerRecord> timerRecords;

	private Timer() {
		timerRecords = new HashMap<String, TimerRecord>();
	}

	private static class TimerHolder {
		public static final Timer INSTANCE = new Timer();
	}

	public static Timer getInstance() {
		return TimerHolder.INSTANCE;
	}

	public synchronized void start(final String id) {
		final TimerRecord timerRecord = new TimerRecord(id);
		timerRecord.setStartTime( System.nanoTime());
		timerRecords.put(id, timerRecord);
	}

	public synchronized long time(final String id, final TimeUnit unit) throws IllegalArgumentException{
		final long endTime = System.nanoTime();
		final TimerRecord timerRecord = timerRecords.get(id);
		if (timerRecord != null) {
			timerRecord.setEndTime(endTime);
			return unit.convert(timerRecord.getTimeTakenInMilliseconds(), TimeUnit.MILLISECONDS);
		} else {
			throw new IllegalArgumentException("No timer record is found");
		}
	}

	public synchronized TimerRecord[] getTimerRecords() {
		return (TimerRecord[]) this.timerRecords.values().toArray();
	}

	public synchronized TimerRecord getTimerRecords(final String id) {
		return timerRecords.get(id);
	}

}