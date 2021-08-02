package com.example.readingisgood.util;

import java.util.UUID;

public class CoreUtil {

    public static String generateID() {
        return UUID.randomUUID().toString();
    }
}
