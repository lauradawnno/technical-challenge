package ie.distilledsch.technicalchallenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ie.distilledsch.technicalchallenge.model.Car;

/**
 * This is the Data Access Object.
 * Extending JpaRepository provides automatic CRUD methods
 */
public interface CarRepository extends JpaRepository<Car, Long> {
	
	/**
	 * Searches for cars with the provided parameters and returns the average price
	 *
	 * @param make car manufacturer
	 * @param model car model
	 * @param year car year
	 * @return average price
	 */
	 @Query(value = "SELECT AVG(c.PRICE) FROM CAR c WHERE c.MAKE= ?1 AND c.MODEL= ?2 AND c.YEAR = ?3 ",nativeQuery = true)
	 Double findAvgPrice(String make, String model, Integer year);
	

}
