package com.wesleyem.smolurl;

import com.wesleyem.smolurl.service.impl.SmolUrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SmolUrlApplication {

	@Autowired
	public static SmolUrlServiceImpl service;

	public static void main(String[] args) {
		SpringApplication.run(SmolUrlApplication.class, args);
	}
}

