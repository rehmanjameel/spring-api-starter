package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dto.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDto> getProducts(
            @RequestParam(required = false, name = "categoryId") Byte categoryId
    ) {

        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findAllByCategoryId(categoryId);
        } else {
//            products = productRepository.findAll();
            products = productRepository.findAllWithCategory();
        }
        return products
                .stream().map(productMapper::userToProductDto).toList();
    }

    // add product
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,
                                                    UriComponentsBuilder uriBuilder) {

        // we don't have categoryId in the entity so have to fetch separately from database
        var findCategoryId = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (findCategoryId == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productMapper.toEntity(productDto);

        // set category Id before saving
        product.setCategory(findCategoryId);

        // save the data in database via repository
        productRepository.save(product);

        // go with baby steps
        productDto.setId(product.getId());

//        return ResponseEntity.ok(productDto);
        // create the uri for response method 'created' using product id
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();

        // return product created with 201 response
        return ResponseEntity.created(uri).body(productMapper.userToProductDto(product));

    }

    // update product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(name = "id") Long id,
                                                    @RequestBody ProductDto productDto) {
        // we don't have categoryId in the entity so have to fetch separately from database
        var findCategoryId = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (findCategoryId == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productMapper.updateProduct(productDto, product);

        product.setCategory(findCategoryId);
        productDto.setId(product.getId());
        productRepository.save(product);

        return ResponseEntity.ok(productDto);
    }

    // delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
