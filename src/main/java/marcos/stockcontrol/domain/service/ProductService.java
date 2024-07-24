package marcos.stockcontrol.domain.service;

import marcos.stockcontrol.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    void update(Product product);

    Product findById(Long id);

    Product create(Product productToCreate);

    List<Product> productList();
}
