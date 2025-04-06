package com.hanhwa_tae.gulhan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Be152ndBaksalGulhanApplication {

	public static void main(String[] args) {
		SpringApplication.run(Be152ndBaksalGulhanApplication.class, args);
	}

}
