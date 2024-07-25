package marcos.stockcontrol.domain.service.impl;

import marcos.stockcontrol.domain.model.Supplier;
import marcos.stockcontrol.domain.repository.SupplierRepository;
import marcos.stockcontrol.domain.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Supplier> showSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier create(Supplier supplierToCreate) {
        if(
                supplierRepository.existsByCpf(supplierToCreate.getCpf()) ||
                        supplierRepository.existsByEmail(supplierToCreate.getEmail()) ||
                        supplierRepository.existsByPhone(supplierToCreate.getPhone())) {

            throw new IllegalArgumentException("This Supplier already exists.");

        }
        supplierToCreate.setDate(LocalDateTime.now());
        return supplierRepository.save(supplierToCreate);
    }

    @Override
    public Supplier update(Supplier SupplierToUpdate) {
        return supplierRepository.save(SupplierToUpdate);
    }
}
