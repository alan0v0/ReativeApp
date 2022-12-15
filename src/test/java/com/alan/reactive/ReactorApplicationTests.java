package com.alan.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReactorApplicationTests {
    @Autowired
    ReactorApplication application;

    @Test
    void contextLoads() {
        assert application != null;
    }

}
