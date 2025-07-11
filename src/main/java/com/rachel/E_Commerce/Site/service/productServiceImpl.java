package com.rachel.E_Commerce.Site.service;

import com.rachel.E_Commerce.Site.model.Product;
import com.rachel.E_Commerce.Site.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class productServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product get(Integer id){
        return productRepository.findById(id).get();
    }
    @Override
    public void delete(Integer id){
        productRepository.deleteById(id);
    }
}
