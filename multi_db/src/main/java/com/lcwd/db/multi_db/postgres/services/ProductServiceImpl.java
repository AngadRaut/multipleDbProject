package com.lcwd.db.multi_db.postgres.services;

import com.lcwd.db.multi_db.postgres.entities.Product;
import com.lcwd.db.multi_db.postgres.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductServices{

    @Autowired
    private ProductRepo productRepo;
    // add new product
    @Override
    public Product addProduct(Product product) {
        Product product1 = this.productRepo.save(product);
        return product1;
    }
    // find all products from the db
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = this.productRepo.findAll();
        return products;
    }

    // find product by id service.....
    @Override
    public Product getProductById(int id) {
        Optional<Product> product = this.productRepo.findById(id);
        return product.orElse(null);
    }

    @Override
    public Product updateProduct(int id,Product product) {
        Optional<Product> product1 = this.productRepo.findById(id);
        if(product1.isPresent()){
            Product product2 = product1.get();
            product2.setProductName(product.getProductName());

            return product2;
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        Optional<Product> product = this.productRepo.findById(id);
        if(product.isPresent()){
            this.productRepo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    // adding multiple records for the product field
    @Override
    public List<Product> saveAll(List<Product> productList) {
        return this.productRepo.saveAll(productList);
    }
}
