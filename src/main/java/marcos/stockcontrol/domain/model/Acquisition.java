package marcos.stockcontrol.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_acquisition")
public class Acquisition {

    public Acquisition() {
        this.products = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;
    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "acquisition_id")
    private List<RegisterProduct> products;

    private BigDecimal amount;

    @Column(name = "items_quantity")
    private Long quantity;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity() {
        this.quantity = this.products.stream()
                .mapToLong(RegisterProduct::getQuantity)
                .sum();
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount() {
        this.amount = this.products.stream()
                .map(registerProduct -> registerProduct.getPrice()
                        .multiply(BigDecimal.valueOf(registerProduct.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public List<RegisterProduct> getProducts() {
        return products;
    }

    public void setProducts(List<RegisterProduct> products) {
        this.products = products;
    }

    public void addProduct(RegisterProduct product) {
        this.products.add(product);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}
