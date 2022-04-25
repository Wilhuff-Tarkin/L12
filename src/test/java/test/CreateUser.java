package test;

import configuration.handler.UserFactory;
import org.junit.jupiter.api.Test;

public class CreateUser {



    @Test
    void shouldCreateUser() {

        System.out.println(UserFactory.getRandomUser());


    }
}
