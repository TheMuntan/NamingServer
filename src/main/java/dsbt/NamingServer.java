package dsbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class NamingServer {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(NamingServer.class);
		app.setDefaultProperties(Collections.<String, Object>singletonMap("server.port", "5001"));
		app.run(args);
	}

}
