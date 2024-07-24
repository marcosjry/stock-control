package marcos.stockcontrol.domain.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_client")
public class Client extends Account{

    @OneToMany
    private List<Acquisition> acquisitions;

    public Client() {
        this.acquisitions = new ArrayList<>();
    }

    public List<Acquisition> getAcquisitions() {
        return acquisitions;
    }

    public void setAcquisitions(List<Acquisition> acquisitions) {
        this.acquisitions = acquisitions;
    }

    public void addAcquisition(Acquisition acquisition) {
        this.acquisitions.add(acquisition);
    }

}
