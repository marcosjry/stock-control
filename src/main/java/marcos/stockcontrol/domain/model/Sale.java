package marcos.stockcontrol.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_sale")
public class Sale {

    public Sale() {
        this.sales = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;
    private Boolean status;

    @OneToMany
    private List<Product> sales;

    private BigDecimal amount;

    @Column(name = "items_quantity")
    private Long quantity;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity() {
        this.quantity = this.sales.stream()
                .mapToLong(Product::getQuantity)
                .sum();
    }


    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount() {
        this.amount = this.sales.stream()
                .map(product -> product.getPrice()
                        .multiply(BigDecimal.valueOf(product.getQuantity())))
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
