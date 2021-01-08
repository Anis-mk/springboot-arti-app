package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;



@Controller
public class ProductController {
  
	@Autowired
	private ProductService productService;
	
	//affiche la liste des produits
	@GetMapping("/")
	public String viewHomePage(Model model) {
	
		model.addAttribute("listProducts", productService.getAllProducts());
		return "index";
	
	}
	
	
	 @GetMapping("/showNewProduct")
	 public String showNewProduct(Model model) {
	     // create model attribute to bind form data
	     Product product= new Product();
	     model.addAttribute("product", product);
	     return "new_product";
	 }
	 
	 @PostMapping("/saveProduct")
	 public String saveProuct(@ModelAttribute("product") Product product) {
	     // save product to database
	     productService.saveProduct(product);
	     return "redirect:/";
	 }
	 
	 @GetMapping("/showFormForUpdate/{id}")
	 public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
	  
	  // get product from the service
	  	Product product = productService.getProductById(id);
	  
	  // set product as a model attribute to pre-populate the form
	  model.addAttribute("product", product);
	  return "update_product";
	 }

	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable (value = "id") long id) {
	 
	 // call delete product method 
	 this.productService.deleteProductById(id);
	 return "redirect:/";
	}
	
}
