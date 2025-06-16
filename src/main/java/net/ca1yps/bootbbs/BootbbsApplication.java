package net.ca1yps.bootbbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling	//스케쥴을 사용
public class BootbbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootbbsApplication.class, args);
	}

}
