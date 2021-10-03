package com.programming.rg.productservice.Controller;

import com.programming.rg.productservice.Repository.ProductRepository;
import com.programming.rg.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("allProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.OK)
    public void createProduct(@RequestBody Product product) {
        productRepository.save(product);
    }
}
