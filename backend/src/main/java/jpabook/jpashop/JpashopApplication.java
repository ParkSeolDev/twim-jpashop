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

	}

}
