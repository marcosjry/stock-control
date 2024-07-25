package marcos.stockcontrol.domain.DTO;

import marcos.stockcontrol.domain.model.Product;
import marcos.stockcontrol.domain.model.RegisterProduct;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDTO {
    private String name;
    private Long id;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private String img;

    public ProductDTO() {

    }
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private LocalDateTime date;

    public Product productTransform() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setQuantity(this.quantity);
        product.setImg(this.img);
        product.setDate(LocalDateTime.now());
        return product;
    }

    public RegisterProduct dtoToPurchased() {

        RegisterProduct product = new RegisterProduct();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setQuantity(this.quantity);

        return product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
