package br.com.andrefilipeos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Startup {
	public static void main(String[] args) {
		
		//main Class with sinalization to spring that this class is the our inicializer application
		SpringApplication.run(Startup.class, args);
	}
}
