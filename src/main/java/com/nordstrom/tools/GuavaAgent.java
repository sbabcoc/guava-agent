package com.nordstrom.tools;

import static net.bytebuddy.matcher.ElementMatchers.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.Transformer;
import net.bytebuddy.asm.ModifierAdjustment;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.utility.JavaModule;

/**
 * This class implements the transformations that allow use of newer <b>Guava</b> releases with Selenium 2.
 */
public class GuavaAgent {

    private GuavaAgent() {
        throw new AssertionError("GuavaAgent is a static utility class that cannot be instantiated");
    }
    
    /**
     * This is the main entry point for the Java agent used to expose the constructor of {@code SimpleTimeLimiter} and
     * the {@code callWithTimeout} method of {@code TimeLimiter}.
     *  
     * @param agentArgs agent options
     * @param instrumentation {@link Instrumentation} object used to transform Guava core classes
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        installTransformer(instrumentation);
    }
    
    /**
     * Install the {@code Byte Buddy} byte code transformations to {@code TimeLimiter} and {@code SimpleTimeLimiter}.
     * 
     * @param instrumentation {@link Instrumentation} object used to transform Guava core classes
     * @return The installed class file transformer
     */
    public static ClassFileTransformer installTransformer(Instrumentation instrumentation) {
    	return new AgentBuilder.Default()
    	  .type(named("com.google.common.util.concurrent.TimeLimiter"))
          .transform(new Transformer() {
              @Override
              public Builder<?> transform(Builder<?> builder, TypeDescription type,
                              ClassLoader classloader, JavaModule module) {
                  return builder.defineMethod("callWithTimeout", Object.class, Visibility.PUBLIC).withParameters(Callable.class, long.class, TimeUnit.class, boolean.class).withoutCode();
              }
          })
    	  .type(named("com.google.common.util.concurrent.SimpleTimeLimiter"))
          .transform(new Transformer() {
              @Override
              public Builder<?> transform(Builder<?> builder, TypeDescription type,
                              ClassLoader classloader, JavaModule module) {
                  return builder.visit(new ModifierAdjustment().withInvokableModifiers(isConstructor().or(named("callWithTimeout")), Visibility.PUBLIC));
              }
          })
          .installOn(instrumentation);
    }
}
