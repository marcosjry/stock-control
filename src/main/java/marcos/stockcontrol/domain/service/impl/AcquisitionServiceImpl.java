package marcos.stockcontrol.domain.service.impl;

import marcos.stockcontrol.domain.DTO.AcquisitionDTO;
import marcos.stockcontrol.domain.DTO.ProductDTO;
import marcos.stockcontrol.domain.controller.exception.ProductException;
import marcos.stockcontrol.domain.controller.exception.SaleException;
import marcos.stockcontrol.domain.model.Acquisition;
import marcos.stockcontrol.domain.model.Product;
import marcos.stockcontrol.domain.model.RegisterProduct;
import marcos.stockcontrol.domain.model.Sale;
import marcos.stockcontrol.domain.repository.AcquisitionRepository;
import marcos.stockcontrol.domain.service.AcquisitionService;
import marcos.stockcontrol.domain.service.ClientService;
import marcos.stockcontrol.domain.service.ProductService;
import marcos.stockcontrol.domain.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class AcquisitionServiceImpl implements AcquisitionService {

    @Autowired
    private AcquisitionRepository acquisitionRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    @Override
    public Acquisition create(AcquisitionDTO dto) {
        Acquisition acquisitionToCreate = dto.objectTransform();
        acquisitionToCreate.setAmount();
        acquisitionToCreate.setQuantity();

        for(ProductDTO productDTO: dto.getProductList()) {
            if(productService.searchByName(productDTO.getName())) {
                Product productDb = productService.findById(productDTO.getId());
                productDb.setQuantity(productDb.getQuantity() + productDTO.getQuantity());
                productService.update(productDb);
            }
        }
        acquisitionToCreate.setStatus(true);
        Acquisition acquisitionSaved = acquisitionRepository.save(acquisitionToCreate);

        var supplier = supplierService.findById(acquisitionSaved.getSupplierId());
        supplier.addAcquisitions(acquisitionToCreate);
        supplierService.update(supplier);

        return acquisitionSaved;
    }


    @Override
    public Acquisition showAcquisition(Long id) {
        return acquisitionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}




