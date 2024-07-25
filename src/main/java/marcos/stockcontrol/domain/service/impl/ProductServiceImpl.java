package marcos.stockcontrol.domain.service.impl;

import marcos.stockcontrol.domain.model.Product;
import marcos.stockcontrol.domain.repository.ProductRepository;
import marcos.stockcontrol.domain.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {



    private final ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Product create(Product productToCreate) {
        if(productRepository.existsByName(productToCreate.getName())) {
            throw new IllegalArgumentException("This Product already exists.");
        }
        productToCreate.setDate(LocalDateTime.now());
        return productRepository.save(productToCreate);
    }

    @Override
    public Boolean searchByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public List<Product> productList() {
        return productRepository.findAll();
    }
}
