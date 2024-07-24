package marcos.stockcontrol.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name = "tb_supplier")
public class Supplier extends Account{

    @OneToMany
    private List<Sale> sale;

    public List<Sale> getSale() {
        return sale;
    }

    public void setSale(List<Sale> sale) {
        this.sale = sale;
    }


}
