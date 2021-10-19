package com.ds.azure.assignment1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class Assignment1Application {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		System.setProperty("name", "dipak");
		SpringApplication.run(Assignment1Application.class, args);
	}
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Assignment1Application.class);

	}
*/
	@GetMapping("/")
	public String sayHello(Model model) {
		var products = productService.getProducts();
		model.addAttribute("name", System.getProperty("name"));
		model.addAttribute("products", products);
		return "index";
	}

}