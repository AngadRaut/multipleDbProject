package com.lcwd.db.multi_db.postgres.repo;

import com.lcwd.db.multi_db.postgres.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
}
