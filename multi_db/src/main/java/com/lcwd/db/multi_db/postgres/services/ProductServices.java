package com.lcwd.db.multi_db.postgres.services;

import com.lcwd.db.multi_db.postgres.entities.Product;

import java.util.List;

public interface ProductServices {
    public Product addProduct(Product product);
    public List<Product> getAllProducts();
    public Product getProductById(int id);
    public Product updateProduct(int id,Product product);
    public boolean deleteById(int id);
    public List<Product> saveAll(List<Product> productList);
}
