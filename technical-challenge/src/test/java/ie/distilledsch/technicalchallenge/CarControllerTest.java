package ie.distilledsch.technicalchallenge;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ie.distilledsch.technicalchallenge.controller.CarController;
import ie.distilledsch.technicalchallenge.dao.CarRepository;
import ie.distilledsch.technicalchallenge.model.Car;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CarController.class, secure = false)  // States that a Spring Controller will be tested
public class CarControllerTest {
	
	@Autowired
	private MockMvc mockMvc;  // Mocks the RESTful service

	@MockBean
	private CarRepository repo; // Mocks the access to the database
	
	Car stubCar = new Car(new Long(1), "Nissan", "Micra", new Integer(2004), "TEST123", new Date(1485907200000L), new Double(300));

	@Test
	public void test_retrieveAllCars() throws Exception {
		// Mocks the call to the db, returning an array containing the stubCar object
		Mockito.when(repo.findAll()).thenReturn(Arrays.asList(stubCar));
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/car/").accept(MediaType.APPLICATION_JSON);
		// Mocks the actual call to the API
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		
		String expected = "[{id: 1,make:Nissan, model: Micra,year: 2004,last_updated: 1485907200000,price: 300}]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void test_retrieveCar_ok() throws Exception {
		Mockito.when(repo.findOne(Mockito.anyLong())).thenReturn(stubCar);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/car/1").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		
		String expected = "{id: 1,make:Nissan, model: Micra,year: 2004,last_updated: 1485907200000,price: 300}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	
	@Test
	public void test_retrieveCar_notExist() throws Exception {
				
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/car/9").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		
		
                MockHttpServletResponse response = result.getResponse();
		
        
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
	}
	
	
	@Test
	public void test_createCar_ok() throws Exception {
		Mockito.when(repo.save(stubCar)).thenReturn(stubCar);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.post("/car/")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"make\":\"Nissan\", \"model\": \"Micra\",\"year\": 2004,\"last_updated\": 1485907200000,\"price\": 300}")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
	}
	
	@Test
	public void test_avgprice_ok() throws Exception {
		
		Double avg = new Double(200);
		
		Mockito.when(repo.findAvgPrice(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(avg);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.post("/avgprice/")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"make\":\"Nissan\", \"model\": \"Micra\",\"year\": 2004}")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(response.getContentAsString(), avg.toString());
		
	}

}
