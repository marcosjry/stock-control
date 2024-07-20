package marcos.stockcontrol.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name = "tb_supplier")
public class Supplier extends Account{

    @OneToMany
    private List<Product> productList;


    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }



}
