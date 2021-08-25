package com.google.common.base;

/**
 * This dummy class overlaps an actual Guava class that triggers an annoying
 * Java 7 compatibility warning. The header of the original class confesses
 * that it doesn't actually do anything, but due to the target for which it
 * was compiled will trigger an {@link UnsupportedClassVersionError} if it's
 * loaded by a pre-Java 8 class loader.
 */
final class Java8Usage {

    static String performCheck() {
        return "";
    }

    private Java8Usage() { }
}
