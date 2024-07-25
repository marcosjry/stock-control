package marcos.stockcontrol.domain.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_client")
public class Client extends Account{

    @OneToMany
    private List<Sale> saleList;

    public Client() {
        this.saleList = new ArrayList<>();
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }

    public void addSale(Sale acquisition) {
        this.saleList.add(acquisition);
    }

}
