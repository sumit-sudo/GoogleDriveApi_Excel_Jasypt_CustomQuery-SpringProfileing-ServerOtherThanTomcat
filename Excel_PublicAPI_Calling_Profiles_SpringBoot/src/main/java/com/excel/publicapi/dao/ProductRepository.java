
package com.excel.publicapi.dao;



import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.excel.publicapi.entities.Product;





@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
