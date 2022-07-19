package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

@EnableJpaAuditing
@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);

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
				InputStream in = socket.getInputStream();
				DataInputStream dis = new DataInputStream(in);
				String message = dis.readUTF();
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				dos.writeUTF("[ECHO]" + message + "(from Server!)");
				dos.close();
				dis.close();
				socket.close();
				System.out.println("client socket close...");
			}catch (Exception e){
				e.printStackTrace();
			}
		}//while
	}

}
