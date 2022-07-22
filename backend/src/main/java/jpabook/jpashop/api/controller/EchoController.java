package jpabook.jpashop.api.controller;

import jpabook.jpashop.socket.Connection;

@TcpController
public class EchoController {

    public void receiveData(Connection connection, byte[] data) {
        String s = new String(data);
        connection.send(s.toUpperCase().getBytes());
        System.out.println(s);
    }

    public void connect(Connection connection) {
        System.out.println("New connection " + connection.getAddress().getCanonicalHostName());
    }

    public void disconnect(Connection connection) {
        System.out.println("Disconnect " + connection.getAddress().getCanonicalHostName());
    }
}
