package org.jsp.supllychainmanagment.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.supllychainmanagment.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

	
	public List<Orders> getOrdersByCustomerId(int id);
	
	public Optional<Orders> getOrdersByTrackingNumber(String tno);
}
