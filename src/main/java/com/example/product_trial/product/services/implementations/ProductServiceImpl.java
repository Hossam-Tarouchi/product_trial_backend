package com.example.product_trial.product.services.implementations;

import com.example.product_trial.product.models.Product;
import com.example.product_trial.product.models.ProductEntity;
import com.example.product_trial.product.repositories.ProductRepository;
import com.example.product_trial.product.services.interfaces.ProductService;
import com.example.product_trial.product.utils.constants.ConstantMessages;
import com.example.product_trial.product.utils.exceptions.ProductDoesntExistException;
import com.example.product_trial.product.utils.mappers.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProduct).toList();
    }

    @Override
    public Product createProduct(Product product) {
        // Convert Product to ProductEntity
        ProductEntity productEntity = productMapper.toProductEntity(product);
        // Save the new product
        productEntity = productRepository.save(productEntity);
        // Convert the saved ProductEntity to Product and return it back
        return productMapper.toProduct(productEntity);
    }

    @Override
    public Product getProductById(Long id) throws ProductDoesntExistException {
        // Try to fetch the product by its id
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        // Check if there is any product with the given id
        if (productEntityOptional.isPresent()) {
            // True: Convert and return the product
            return productMapper.toProduct(productEntityOptional.get());
        } else {
            // False: Raise ProductDoesntExist Exception
            throw new ProductDoesntExistException(ConstantMessages.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductDoesntExistException {

        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        // Check if there is any product with the given id
        if (productEntityOptional.isPresent()) {
            // True: Convert Product to ProductEntity
            product.setId(id);
            ProductEntity productEntity = productMapper.toProductEntity(product);
            // Update the product
            productEntity = productRepository.save(productEntity);
            // Convert the updated product and return it back
            return productMapper.toProduct(productEntity);
        } else {
            // False: Raise ProductDoesntExist Exception
            throw new ProductDoesntExistException(ConstantMessages.PRODUCT_NOT_FOUND);
        }

    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
