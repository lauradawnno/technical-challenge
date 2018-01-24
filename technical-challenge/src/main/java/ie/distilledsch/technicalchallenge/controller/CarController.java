package ie.distilledsch.technicalchallenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ie.distilledsch.technicalchallenge.dao.CarRepository;
import ie.distilledsch.technicalchallenge.model.Car;


@RestController
public class CarController {
	
	@Autowired
	private CarRepository repo;
	
	
	@GetMapping("/car")
	public ResponseEntity<List<Car>> retrieveAllCars() {
		return new ResponseEntity<List<Car>>(repo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/car/{id}")
	public ResponseEntity<Car> retrieveCar(@PathVariable long id) {
		Optional<Car> car = Optional.ofNullable(repo.findOne(id));
		
		if(!car.isPresent())
			return new ResponseEntity<Car>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<Car>(car.get(), HttpStatus.OK);
	}

	@PostMapping("/car")
	public ResponseEntity<Void> createCar(@RequestBody Car car) {
		repo.save(car);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PostMapping("/avgprice")
	public ResponseEntity<Double> avgprice (@RequestBody Car car){
		Double avg = repo.findAvgPrice(car.getMake(), car.getModel(), car.getYear());
		return new ResponseEntity<Double>(avg, HttpStatus.OK);
	}
	
	
}
