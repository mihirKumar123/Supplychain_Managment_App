package org.jsp.supllychainmanagment.repository;

import org.jsp.supllychainmanagment.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
