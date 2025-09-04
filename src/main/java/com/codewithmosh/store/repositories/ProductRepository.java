package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = "category")
    List<Product> findAllByCategoryId(Byte categoryId);

//    @Query("Select p From Product p Join fetch p.category")\
    @EntityGraph(attributePaths = "category")
    @Query("Select p From Product p")
    List<Product> findAllWithCategory();
}