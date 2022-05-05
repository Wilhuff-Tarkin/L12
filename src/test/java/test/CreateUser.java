package test;

import configuration.handler.UserFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateUser {

    private static final Logger log = LoggerFactory.getLogger("User creation");


    @Test
    void shouldCreateUser() {
        log.info("User created with following data: " + UserFactory.getRandomUser());
    }
}
