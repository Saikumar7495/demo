package com.example.demo;

import com.example.demo.Controllers.UserController;
import com.example.demo.Dtos.SignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private UserController userController;
	//comment

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SignUpRequestDto signUpRequestDto=new SignUpRequestDto();
		signUpRequestDto.setEmail("Sai@gmail.com");
		signUpRequestDto.setPassword("sai@123");
		userController.signUp(signUpRequestDto);
//		System.out.println("userController.login(\"prashant@gmail.com\", \"prasant@123\") = "
//				+ userController.login("prashant@gmail.com", "prahsant123"));

	}
}
