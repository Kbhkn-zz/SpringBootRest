package com.kbhkn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kbhkn.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.email = :email")
	public boolean isCustomerExist(@Param("email") String email);

	public List<Customer> findByEmail(String email);
}
