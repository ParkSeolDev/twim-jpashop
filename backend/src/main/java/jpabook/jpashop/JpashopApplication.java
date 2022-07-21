package jpabook.jpashop;

import jpabook.jpashop.api.controller.OrderController;
import jpabook.jpashop.socket.ObjectThread;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.*;
import java.net.*;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class JpashopApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(JpashopApplication.class, args);

		ServerSocket serverSocket = new ServerSocket(9999);

		while (true) {
			System.out.println("대기중...");
			Socket socket = serverSocket.accept();

			new ObjectThread(socket).start();
		}

/*
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(9999);
			System.out.println("Server ready....");
		} catch (Exception e){
			e.printStackTrace();
		}
		while (true){
			try {
				Socket socket = ss.accept();
				System.out.println("client connect success!");
//				InputStream in = socket.getInputStream();
//				DataInputStream dis = new DataInputStream(in);
//				ObjectInputStream ois = new ObjectInputStream(dis);
//
////				String message = dis.readUTF();
//				User user = (User) ois.readObject();
//
//				OutputStream out = socket.getOutputStream();
//				ObjectOutputStream oos = new ObjectOutputStream(out);
//				DataOutputStream dos = new DataOutputStream(oos);
////				dos.writeUTF("[ECHO]" + message + "(from Server!)");
//				oos.writeUTF(String.valueOf(user));
//				dos.close();
//				dis.close();
//
//				oos.close();
//				ois.close();
//				socket.close();

//				InputStream is = socket.getInputStream();
//				OutputStream os = socket.getOutputStream();
//				ObjectInputStream ois = new ObjectInputStream(is);
//				ObjectOutputStream oos = new ObjectOutputStream(os);
//
//
//
//				User user = (User) ois.readObject();
//
//				String name = user.getName();
//				List<Order> order = user.getOrders();
//				System.out.println(name + " 님은 " + order + " 을 주문하셨습니다.");
//
//				oos.writeObject(name+ " 님 주문 성공.");
//
//				oos.close();
//				ois.close();
//				os.close();
//				is.close();



				System.out.println("client socket close...");
			}catch (Exception e){
				e.printStackTrace();
			}
		}//while


 */
	}

}
