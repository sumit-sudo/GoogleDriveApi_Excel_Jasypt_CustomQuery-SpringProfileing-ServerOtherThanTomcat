package com.excel.publicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excel.publicapi.entities.Product;
import com.excel.publicapi.services.ProductServiceImpl;
;





@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/product")
@RestController

public class ProductController {
	
	@Autowired
	ProductServiceImpl pservice;
	
	@GetMapping("/{id}")
	public Product findProduct(@PathVariable(value="id",required = true) int id)   
	{
		Product findProduct = pservice.findProduct(id);
		return findProduct;
	}
	@GetMapping("/products")	
	public List<Product> findProduct()
	{
		return pservice.listProduct();
	}
	
	@GetMapping("/search")
	
	@RequestMapping(value="/updateProduct", method = RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product)
	{
		Product p=pservice.findProduct(product.getId());
		p.setDescription(product.getDescription());
		p.setName(product.getName());
		return pservice.updateProduct(p);
	}
	
	
}
