package ie.distilledsch.technicalchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechnicalChallengeApplication {

	/**
	 * The main method starts the embedded Tomcat application server
	 * and H2 database (inserting the rows in data.sql in the Car table)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TechnicalChallengeApplication.class, args);
	}
}
