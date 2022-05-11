package com.excel.publicapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excel.publicapi.dao.ProductRepository;
import com.excel.publicapi.entities.Product;





@Service
public class ProductServiceImpl {

	@Autowired
	ProductRepository prepo;
	
	public Product findProduct(int id) {
		
		return prepo.findById(id).orElse(null);
	}

	
	public List<Product> listProduct() {
		System.out.println("HI");
		return prepo.findAll(Sort.by("id").descending());
	}

	
	public Product searchByCategoryId(int id) {
		
		return prepo.findById(id).get();
	}


	public Product updateProduct(Product p) {
		return prepo.save(p);
	}


	

}
