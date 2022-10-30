package com.mykola.eshopmykola;

import com.mykola.eshopmykola.models.user.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EShopMykolaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EShopMykolaApplication.class, args);
		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
		System.out.println(encoder.encode("pass"));
	}

}
