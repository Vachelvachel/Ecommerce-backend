package com.rachel.E_Commerce.Site.service;

import com.rachel.E_Commerce.Site.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    public Product saveProduct(Product product);
    public List<Product> getAllProducts();
    public Product get(Integer id);

    public void delete(Integer id);

}


