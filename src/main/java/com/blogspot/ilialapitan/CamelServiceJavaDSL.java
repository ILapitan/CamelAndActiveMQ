package com.blogspot.ilialapitan;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * Service for work with Apache ActiveMQ with Apache Camel help.
 * This is class configured by Java DSL.
 *
 * @author Ilya Lapitan
 */
public final class CamelServiceJavaDSL  implements CamelService{
    private static final String IN_ENDPOINT = "direct:in";
    private static final String OUT_ENDPOINT = "activemq:queue:test-queue";

    private CamelContext context;
    private ProducerTemplate producer;
    private ConsumerTemplate consumer;

    /**
     * Default constructor. Make class final for avoid
     * finalizer attack according to Secure Coding Guidelines
     * for the Java Programming Language.
     *
     * @throws Exception
     */
    public CamelServiceJavaDSL() throws Exception {
        context = new DefaultCamelContext();

        //describe route for communications between Camel and ActiveMQ
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(IN_ENDPOINT).to(OUT_ENDPOINT);
            }
        });

        //configure and add component for work with ActiveMQ from Camel
        context.addComponent("activemq", activeMQComponent("tcp://localhost:61616"));

        //create default producer and consumer
        producer = context.createProducerTemplate();
        consumer = context.createConsumerTemplate();

        //start Camel context manually
        context.start();
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
