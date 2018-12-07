package com.ninuxgithub.dataserver.repository;

import com.ninuxgithub.dataserver.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {


}
