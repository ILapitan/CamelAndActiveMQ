package com.blogspot.ilialapitan;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Test class for testing services for working
 * with Apache ActiveMQ from Apache Camel.
 *
 * @author Ilya Lapitan
 */
public class CamelServiceTest {
    private static final String TEST_STRING = "TEST_STRING";

    @Test
    public void testServiceSpring() throws Exception {
        CamelServiceSpringDSL service = new CamelServiceSpringDSL();

        service.send(TEST_STRING);

        //wait some time
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));

        assertEquals(TEST_STRING, service.receive());
    }

    @Test
    public void testServiceJava() throws Exception {
        CamelServiceJavaDSL service = new CamelServiceJavaDSL();

        service.send(TEST_STRING);

        //wait some time
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));

        assertEquals(TEST_STRING, service.receive());
    }
}
