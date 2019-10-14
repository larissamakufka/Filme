package com.filme;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan({ "com.filme" })
@SpringBootApplication
public class MainApplication {

	@Autowired
	private Csv csv;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	
	@PostConstruct
    public void init() {
		csv.run();
    }
}
