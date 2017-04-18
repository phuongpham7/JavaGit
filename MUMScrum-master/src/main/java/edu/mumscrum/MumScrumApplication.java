package edu.mumscrum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import edu.mumscrum.hrSubSystem.HRSystemFacade;
import edu.mumscrum.hrSubSystem.IHRSubsystem;

@ComponentScan(value={
		"edu.mumscrum.web",
		"edu.mumscrum.domain",
		"edu.mumscrum.service",
		"edu.mumscrum.aop",
		"edu.mumscrum.hrSubSystem",
		"edu.mumscrum.test"})
@SpringBootApplication
public class MumScrumApplication {

	public static void main(String[] args) {
//		IHRSubsystem hr= HRSystemFacade.getInstance();
//		hr.hello();
		SpringApplication.run(MumScrumApplication.class, args);
	}
}
