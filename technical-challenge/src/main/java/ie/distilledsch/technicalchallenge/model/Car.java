package ie.distilledsch.technicalchallenge.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * Standard Java Bean
 *
 */
@Entity
public class Car {

	// auto-incremented id
	@Id
	@GeneratedValue
	private Long id;

	private String make;
	
	private String model;
	
	private Integer year;
	
	// Only allows for deserialization combined with JsonIgnore on the getter method
	@JsonProperty(access = Access.WRITE_ONLY)
	private String chassis_id;
	
	private Date last_updated;
	
	private Double price;
	
	public Car() {}
	
	public Car(Long id, String make, String model, Integer year, String chassis_id, Date last_updated, Double price) {
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.chassis_id = chassis_id;
		this.last_updated = last_updated;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	@JsonIgnore
	public String getChassis_id() {
		return chassis_id;
	}
	
	public void setChassis_id(String chassis_id) {
		this.chassis_id = chassis_id;
	}
	public Date getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
