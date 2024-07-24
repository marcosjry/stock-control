package marcos.stockcontrol.domain.controller;


import marcos.stockcontrol.domain.model.Product;
import marcos.stockcontrol.domain.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> listProduct() {
        return ResponseEntity.accepted().body(productService.productList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        var product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        var toCreate = productService.create(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(toCreate.getId())
                .toUri();
        return ResponseEntity.created(location).body(toCreate);
    }

    @PatchMapping (path = "/{id}")
    public ResponseEntity<Product> productUpdate(@PathVariable Long id, @RequestBody Product product) {
        var toPatch = productService.findById(id);
        if(product.getPrice() != null) {
            toPatch.setPrice(product.getPrice());
        }
        if(product.getQuantity() != null) {
            toPatch.setQuantity(product.getQuantity());
        }
        if(product.getDescription() != null) {
            toPatch.setDescription(product.getDescription());
        }
        if(product.getName() != null) {
            toPatch.setName(product.getName());
        }
        productService.update(toPatch);
        return ResponseEntity.ok(toPatch);
    }
}
