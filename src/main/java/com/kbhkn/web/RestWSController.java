package com.kbhkn.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbhkn.domain.Customer;
import com.kbhkn.repository.CustomerRepository;
import com.kbhkn.util.IpAddress;

@RestController
@RequestMapping("/admin/")
public class RestWSController {

	private static final Logger logger = LoggerFactory.getLogger(RestWSController.class);

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping(value = "/customer/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Customer>> listAllCustomers() {
		List<Customer> allCustomers = customerRepository.findAll();
		return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.OK);
	}

	@GetMapping(value = "/customer/{id:^[\\d]+$}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getCustomer(@PathVariable("id") long id) {
		logger.info("Fetching Customer with id {}", id);

		Customer customer = customerRepository.findOne(id);

		if (customer == null) {
			logger.error("{} id not found!", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@PostMapping(value = "/customer/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {

		if (customerRepository.isCustomerExist(customer.getEmail())) {
			logger.error("Customer already exist! Customer: {}", customer.toString());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		customerRepository.save(customer);

		logger.info("Creating new Customer: {}", customer.toString());

		return new ResponseEntity<Customer>(HttpStatus.CREATED);
	}

	@PutMapping(value = "/customer/{id:^[\\d]+$}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		logger.info("Updating Customer: {} with id: {}", customer.toString(), id);

		Customer currentCustomer = customerRepository.findOne(customer.getId());

		if (currentCustomer == null) {
			logger.error("Customer: {} with id: {} not found", customer.toString(), id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		customerRepository.save(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@DeleteMapping(value = "/customer/{id:^[\\d]+$}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") long id, HttpServletRequest request) {
		logger.info("Deleting Customer with id: {}, IP Address: {}", id, IpAddress.getClientRealIpAdress(request));

		Customer customer = customerRepository.findOne(id);

		if (customer == null) {
			logger.error("Customer doesn't deleted! Id: {} not found!", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		customerRepository.delete(customer);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
}
