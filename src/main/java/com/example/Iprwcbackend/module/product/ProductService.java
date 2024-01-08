package com.example.Iprwcbackend.module.product;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void updateProduct(Long productId, Product product){
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setAmount(product.getAmount());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setImagePath(product.getImagePath());


        productRepository.save(existingProduct);
    }

    public List<Product> getProductsByCategory(String category){return productRepository.findAllByCategory(category);}
}