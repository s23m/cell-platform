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
 * The Original Code is Gmodel.
 *
 * The Initial Developer of the Original Code is
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.statistics;

import java.util.concurrent.TimeUnit;

public class TimerRecord {

	public static final int INITIAL_VALUE = -1;
	private long endTime  = INITIAL_VALUE;
	private long startTime = INITIAL_VALUE;

	private String info;
	private final String id;

	public TimerRecord(final String id) {
		this.id = id;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TimerRecord other = (TimerRecord) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	protected long getEndTime() {
		return endTime;
	}

	public String getId() {
		return id;
	}

	public String getInfo() {
		return info;
	}

	protected long getStartTime() {
		return startTime;
	}

	public long getTimeTakenInMilliseconds() {
		if (startTime > -1 && endTime > -1) {
			return TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
		} else {
			return TimerRecord.INITIAL_VALUE;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "TimerRecord [endTime=" + endTime + ", id=" + id
		+ ", startTime=" + startTime + "]";
	}

	protected void setEndTime(final long endTime) {
		this.endTime = endTime;
	}

	public void setInfo(final String info) {
		this.info = info;
	}

	protected void setStartTime(final long startTime) {
		this.startTime = startTime;
	}

}
