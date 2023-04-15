package main.FiberTools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controllers.FiberTools", "models.FiberTools"})
public class FiberToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiberToolsApplication.class, args);
	}

}
