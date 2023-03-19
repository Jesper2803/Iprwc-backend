package com.example.Iprwcbackend.module.product;
import com.example.Iprwcbackend.module.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long productId){
        return productRepository.findById(productId);
    }

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("product with id " + productId + " does not exist"));
        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(Long productId, String productName, String category, int amount, double price, String imagePath) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("product with id " + productId + " does not exist"));
        if (productName != null && productName.length() > 0){
            product.setProductName(productName);
        }

        if (category != null && category.length() > 0){
            product.setCategory(category);
        }

        if (amount > 0){
            product.setAmount(amount);
        }

        product.setPrice(price);

        if (imagePath != null && imagePath.length() > 0){
            product.setImagePath(imagePath);
        }
    }

    public List<Product> getProductsByCategory(String category){return productRepository.findAllByCategory(category);}
}