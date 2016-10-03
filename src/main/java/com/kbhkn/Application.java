package com.kbhkn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.kbhkn.domain.Customer;
import com.kbhkn.domain.User;
import com.kbhkn.repository.CustomerRepository;
import com.kbhkn.repository.UserRepository;

/**
 * @author kbhkn
 *
 * 17 Sep 2016
 */
@SpringBootApplication
@EnableWebMvc
public class Application extends SpringBootServletInitializer implements CommandLineRunner{
	
	@Autowired private UserRepository userRepository;
	@Autowired private CustomerRepository cRepos;
	@Autowired private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.saveAndFlush(new User("kbhkn", passwordEncoder.encode("123456"), "ADMIN"));
		userRepository.saveAndFlush(new User("hakan", passwordEncoder.encode("123456"), "USER"));
		List<Customer> c =  cRepos.findByLastNameOrderByNameDesc("KABASAKAL");
		c.forEach(System.out::println);
	}
}
