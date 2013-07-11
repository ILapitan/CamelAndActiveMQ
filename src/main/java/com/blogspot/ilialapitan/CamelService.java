package com.blogspot.ilialapitan;

/**
 * Service for work with Apache ActiveMQ with Apache Camel help.
 *
 * @author Ilya Lapitan
 */
public interface CamelService {

    /**
     * Send message to ActvieMQ broker.
     *
     * @param message to send.
     */
    public void send(String message);

    /**
     * Receive message from ActiveMQ broker.
     *
     * @return String received message.
     */
    public String receive();
}
