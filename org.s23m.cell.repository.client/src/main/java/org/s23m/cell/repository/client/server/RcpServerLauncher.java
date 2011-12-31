/* ***** BEGIN LICENSE BLOCK *****
 * Version: SMTL 1.0
 *
 * The contents of this file are subject to the Sofismo Model Technology License Version
 * 1.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.sofismo.ch/SMTL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis.
 * See the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is Gmodel Semantic Extensions Edition.
 *
 * The Initial Developer of the Original Code is
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2012 Sofismo AG.
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
						System.err.println("Repository Client Server started.");
						server.mainloop();
					}
				} catch (final IOException ex) {
					throw new IllegalStateException("Server faliled to start", ex);
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
