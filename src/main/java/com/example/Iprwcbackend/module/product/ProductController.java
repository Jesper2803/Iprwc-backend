package com.example.Iprwcbackend.module.product;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("{productId}")
    public Optional<Product> getProduct(@PathVariable("productId") Long productId){ return productService.getProduct(productId);}

    @PostMapping("/admin/new")
    public ResponseEntity<Void> registerNewProduct(@Valid @RequestBody Product product){
        productService.addNewProduct(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/admin/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
    }

    @PutMapping("/admin/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId, @Valid @RequestBody Product product) {
        productService.updateProduct(productId, product);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){return productService.getProductsByCategory(category);}
}

