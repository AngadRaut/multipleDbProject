package com.lcwd.db.multi_db.postgres.controller;

import com.lcwd.db.multi_db.postgres.entities.Product;
import com.lcwd.db.multi_db.postgres.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductControllers {
    @Autowired
    private ProductServices productServices;
    // controller for add new product in the db
    @PostMapping("addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product product1 = this.productServices.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product1);
    }
    // fetch all products from database
    @GetMapping("/getall")
    public ResponseEntity<List<Product>> fetchProducts(){
        List<Product> productList = this.productServices.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    // fetch details of product with id
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
        Product product = this.productServices.getProductById(id);
        if(product != null){
            return ResponseEntity.status(HttpStatus.FOUND).body(product);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // update the product of particular id
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestParam int id,@RequestBody Product product){
        Product product1 = this.productServices.updateProduct(id,product);
        if(product != null){
            return ResponseEntity.status(HttpStatus.FOUND).body(product);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // delete product from the database
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")int id){
        boolean isPresent = this.productServices.deleteById(id);
        if(isPresent){
            return ResponseEntity.status(HttpStatus.OK).body("deleted Successfully.....");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Records of this id are not available in database...");
        }
    }
    // saving multiple products at a time
    @PostMapping("/save_multiple_products")
    public ResponseEntity<List<Product>> saveMultiple(@RequestBody List<Product> productList){
        List<Product> productList1 = this.productServices.saveAll(productList);
        return ResponseEntity.status(HttpStatus.CREATED).body(productList1);
    }
}