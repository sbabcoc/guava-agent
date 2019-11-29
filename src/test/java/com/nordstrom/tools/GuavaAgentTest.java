package com.nordstrom.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class GuavaAgentTest {

	private static final AtomicInteger THREAD_COUNTER = new AtomicInteger(1);
	private static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool(new ThreadFactory() {
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r, "UrlChecker-" + THREAD_COUNTER.incrementAndGet()); // Thread safety reviewed
			t.setDaemon(true);
			return t;
		}
	});

	@Test
	@SuppressWarnings("unused")
	public void testSimpleTimeLimiter() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<?> clazz = Class.forName("com.google.common.util.concurrent.SimpleTimeLimiter");
		Constructor<?> ctor = clazz.getConstructor(new Class[] { ExecutorService.class });
		Object limiter = ctor.newInstance(THREAD_POOL);
	}

}
