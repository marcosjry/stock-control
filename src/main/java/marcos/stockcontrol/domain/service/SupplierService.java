package marcos.stockcontrol.domain.service;

import marcos.stockcontrol.domain.model.Client;
import marcos.stockcontrol.domain.model.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier findById(Long id);

    List<Supplier> showSupplier();

    Supplier create(Supplier clientToCreate);

    Supplier update(Supplier clientToUpdate);
}
