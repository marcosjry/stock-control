package marcos.stockcontrol.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_supplier")
public class Supplier extends Account{

    public Supplier() {
        this.acquisitions = new ArrayList<>();
    }

    @OneToMany
    private List<Acquisition> acquisitions;

    public List<Acquisition> getSale() {
        return acquisitions;
    }

    public void setSale(List<Acquisition> acquisitions) {
        this.acquisitions = acquisitions;
    }

    public void addAcquisitions(Acquisition acquisitions) {
        this.acquisitions.add(acquisitions);
    }


}
