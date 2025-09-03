package com.rachel.E_Commerce.Site.controller;

import com.rachel.E_Commerce.Site.model.Product;
import com.rachel.E_Commerce.Site.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public String add(@RequestBody Product product){
        productService.saveProduct(product);
        return "New product is added";
    }

    @GetMapping("/getAll")
    public List<Product> list(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id){
        try{
            Product product = productService.get(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product){
        try{
            //Throw error when id does not exist
            Product currProduct = productService.get(id);
            product.setId(id);
            productService.saveProduct(product);
            return new ResponseEntity<Product>(HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        try {
            Product currProduct = productService.get(id);
            productService.delete(id);
            return ("Deleted product" + id);
        }catch(NoSuchElementException e){
            return ("NO such product!");
        }
    }
}
