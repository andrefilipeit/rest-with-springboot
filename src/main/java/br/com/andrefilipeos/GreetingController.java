package br.com.andrefilipeos;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello World";
	private final AtomicLong counter = new AtomicLong();
	
	//this annotation tells spring that this method is an endpoint 
	@RequestMapping("/greeting")
	public Greeting greetin(@RequestParam(value="name", defaultValue="World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
		
	}
}
