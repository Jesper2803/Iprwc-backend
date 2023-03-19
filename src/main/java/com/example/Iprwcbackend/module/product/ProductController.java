package com.example.Iprwcbackend.module.product;

import com.example.Iprwcbackend.module.user.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void registerNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "/admin/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
    }

    @PutMapping(path = "/admin/{productId}")
    public void updateUser(@PathVariable("productId") Long productId,
                           @RequestBody Product product){
        String productName = product.getProductName();
        String category = product.getCategory();
        int amount = product.getAmount();
        double price = product.getPrice();
        String imagePath = product.getImagePath();
        productService.updateProduct(productId, productName, category, amount, price, imagePath);
    }

    @GetMapping(path = "category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){return productService.getProductsByCategory(category);}
}

