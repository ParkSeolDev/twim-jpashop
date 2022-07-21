package jpabook.jpashop.socket;

import jpabook.jpashop.api.controller.OrderController;
import jpabook.jpashop.db.entity.Order;
import jpabook.jpashop.db.entity.User;
import jpabook.jpashop.db.repository.OrderRepositorySupport;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ObjectThread extends Thread {
    Socket socket;

    public ObjectThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {

                System.out.println("Client connect success!");
                InputStream in = socket.getInputStream();
                System.out.println(in);
                DataInputStream dis = new DataInputStream(in);
                String message = dis.readUTF();

                OutputStream out = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(out);
                dos.writeUTF(message + "(from Server!)");

                User user = new User("111111111111111","1");

//                OutputStream out = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(user);
                oos.flush();
//                oos.close();
//                dis.close();
//                out.close();
//                socket.close();
//                System.out.println("client socket close...");


//                OutputStream out = socket.getOutputStream();
//
//                ObjectOutputStream oos = new ObjectOutputStream(out);
//                String text = "Text";
////                List<Order> orders = (List<Order>) OrderRepositorySupport.findAll();
////                text = orders.toString();
////                oos.writeObject(orders);
//                oos.writeUTF(text);
//                oos.flush();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}