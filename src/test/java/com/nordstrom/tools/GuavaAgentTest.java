package com.nordstrom.tools;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class GuavaAgentTest {

	@Test
	@SuppressWarnings("unused")
	public void testTimeLimiterMethod() throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		Class<?> iface = Class.forName("com.google.common.util.concurrent.TimeLimiter");
		Method method = iface.getDeclaredMethod("callWithTimeout", Callable.class, long.class, TimeUnit.class, boolean.class);
		assertTrue(Modifier.isPublic(method.getModifiers()));
	}

	@Test
	public void testSimpleTimeLimiterCtor() throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		Class<?> clazz = Class.forName("com.google.common.util.concurrent.SimpleTimeLimiter");
		Constructor<?> ctor = clazz.getDeclaredConstructor(new Class[] { ExecutorService.class });
		assertTrue(Modifier.isPublic(ctor.getModifiers()));
	}
	
	@Test
	public void testSimpleTimeLimiterMethod() throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		Class<?> clazz = Class.forName("com.google.common.util.concurrent.SimpleTimeLimiter");
		Method method = clazz.getDeclaredMethod("callWithTimeout", Callable.class, long.class, TimeUnit.class, boolean.class);
		assertTrue(Modifier.isPublic(method.getModifiers()));
	}

}
