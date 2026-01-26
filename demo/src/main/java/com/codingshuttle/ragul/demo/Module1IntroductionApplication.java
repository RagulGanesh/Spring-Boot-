package com.codingshuttle.ragul.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Module1IntroductionApplication implements CommandLineRunner {

	@Autowired
	PaymentService paymentService;

	public static void main(String[] args) {

		SpringApplication.run(Module1IntroductionApplication.class, args);

		//PaymentService paymentService = new PaymentService();
		//paymentService.pay();

	}

	@Override
	public void run(String... args) throws Exception {
		paymentService.pay();
	}
}
