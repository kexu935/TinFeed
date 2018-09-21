package com.kex.tinfeed.common;

import org.jetbrains.annotations.Contract;

public class Util {
    private Util() {}
    @Contract("null -> true")
    public static boolean isStringEmpty(String string) {
        return string == null || string.length() == 0;
    }
}