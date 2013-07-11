package com.blogspot.ilialapitan;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Service for work with Apache ActiveMQ with Apache Camel help.
 * This is class configured by Spring DSL.
 *
 * @author Ilya Lapitan
 */
public final class CamelServiceSpringDSL implements CamelService{
    private static final String IN_ENDPOINT = "direct:in";
    private static final String OUT_ENDPOINT = "activemq:queue:test-queue";

    private CamelContext camelContext;
    private ProducerTemplate producer;
    private ConsumerTemplate consumer;

    /**
     * Default constructor. Make class final for avoid
     * finalizer attack according to Secure Coding Guidelines
     * for the Java Programming Language.
     * (java.lang.RuntimeException can be occurred)
     *
     */
    public CamelServiceSpringDSL() {
        camelContext = (CamelContext) new ClassPathXmlApplicationContext("spring-camel-context.xml").getBean("camelContext");
        producer = camelContext.createProducerTemplate();
        consumer = camelContext.createConsumerTemplate();
    }

    /**
     * Send message to ActvieMQ broker.
     *
     * @param message to send.
     */
    @Override
    public void send(final String message){
        producer.sendBody(IN_ENDPOINT, message);
    }

    /**
     * Receive message from ActiveMQ broker.
     *
     * @return String received message.
     */
    @Override
    public String receive(){
        return (String) consumer.receiveBody(OUT_ENDPOINT);
    }
}
