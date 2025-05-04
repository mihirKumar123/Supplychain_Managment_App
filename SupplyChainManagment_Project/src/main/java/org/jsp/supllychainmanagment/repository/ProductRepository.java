package org.jsp.supllychainmanagment.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.supllychainmanagment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select p from Product p where p.supplier.id=?1")
	List<Product> getProductBySupplierId(int id);
}
