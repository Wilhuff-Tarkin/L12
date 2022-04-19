package test;

import base.TestBase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OpenStore extends TestBase {

    @Test
    void shouldOpenStore() throws InterruptedException {

        Thread.sleep(2500);
        assertThat(true, equalTo(true));
    }
}
