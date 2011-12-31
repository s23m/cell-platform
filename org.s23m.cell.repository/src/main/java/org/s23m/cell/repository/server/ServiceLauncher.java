package org.s23m.cell.repository.server;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jetlang.channels.MemoryChannel;
import org.jetlang.core.Callback;
import org.jetlang.fibers.Fiber;
import org.jetlang.fibers.PoolFiberFactory;

import com.rabbitmq.client.RpcServer;

public class ServiceLauncher {

	private static final String START_PROMPT = "start";
	private final Fiber fiber;
	private final RpcServer server;
	private final ExecutorService service;
	private final PoolFiberFactory fact;
	private MemoryChannel<String> channel;
	private final CountDownLatch cd;

	public ServiceLauncher(final RpcServer server) {
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
