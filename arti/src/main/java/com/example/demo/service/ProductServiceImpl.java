package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import	java.util.Optional ;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	 private ProductRepository productRepository;
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}
	@Override
	public void saveProduct(Product product) {
		this.productRepository.save(product);
	
	}
	@Override
	public Product getProductById(long id) {
		Optional<Product> optional = productRepository.findById(id);
		    Product product = null;
		    if (optional.isPresent()) {
		        product = optional.get();
		    } else {
		        throw new RuntimeException(" Product not found for id :: " + id);
		    }
		    return product;
		
		
	}
	
    @Override
public void deleteProductById(long id) {
this.productRepository.deleteById(id);
}

}
