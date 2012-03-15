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
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.repository.client.server;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jetlang.channels.MemoryChannel;
import org.jetlang.core.Callback;
import org.jetlang.fibers.Fiber;
import org.jetlang.fibers.PoolFiberFactory;

import com.rabbitmq.client.RpcServer;

public class RcpServerLauncher {

	private static final String START_PROMPT = ConfigValues.getString("RepositoryClientServer.START_PROMPT");

	private final Fiber fiber;

	private final RpcServer server;

	private final ExecutorService service;

	private final PoolFiberFactory fact;

	private MemoryChannel<String> channel;

	private final CountDownLatch cd;

	public RcpServerLauncher(final RpcServer server) {
		this.server = server;
		this.cd = new CountDownLatch(1);
		service = Executors.newCachedThreadPool();
		fact = new PoolFiberFactory(service);
		fiber = fact.create();
	}

	public CountDownLatch getCountDownLatch() {
		return cd;
	}

	public void start() throws IllegalStateException {
		fiber.start();
		channel = new MemoryChannel<String>();
		final Callback<String> serverRunnable = new Callback<String>() {
			public void onMessage(final String msg) {
				try {
					if (msg.equals(START_PROMPT)) {
						System.out.println("Repository Client Server started.");
						server.mainloop();
					}
				} catch (final IOException ex) {
					throw new IllegalStateException("Server failed to start", ex);
				}
			}
		};
		channel.subscribe(fiber, serverRunnable);
		channel.publish(START_PROMPT);
	}

	public void terminate() throws InterruptedException {
		server.terminateMainloop();
		fiber.dispose();
		fact.dispose();
		service.shutdown();
		cd.countDown();
	}

}
