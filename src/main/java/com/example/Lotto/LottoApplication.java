package com.example.Lotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;


import com.example.Lotto.domain.LottoriviRepository;
import com.example.Lotto.domain.UserRepository;

@SpringBootApplication
public class LottoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LottoApplication.class, args);
	}
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	
	@Bean
	public CommandLineRunner customers(LottoriviRepository repository, UserRepository urepository) {
		return(args) ->{
			
			
			
		};
	}
}
