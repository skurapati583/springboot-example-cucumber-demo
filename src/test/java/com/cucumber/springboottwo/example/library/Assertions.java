package com.cucumber.springboottwo.example.library;

import static org.assertj.core.api.Assertions.assertThat;

public class Assertions {

    public static void checkNotNull(Object obj) {
        assertThat(obj).isNotNull();
    }

    public static void checkTrue(boolean condition) {
        assertThat(condition).isTrue();
    }

}
