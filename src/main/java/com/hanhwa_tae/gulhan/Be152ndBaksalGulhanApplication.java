package com.hanhwa_tae.gulhan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({
		"com.hanhwa_tae.gulhan.review.query.mapper",
		"com.hanhwa_tae.gulhan.packages.query.mapper",
		"com.hanhwa_tae.gulhan.user.query.mapper"
})
public class Be152ndBaksalGulhanApplication {

	public static void main(String[] args) {
		SpringApplication.run(Be152ndBaksalGulhanApplication.class, args);
	}

}
