package marcos.stockcontrol.domain.service.impl;

import marcos.stockcontrol.domain.DTO.ProductDTO;
import marcos.stockcontrol.domain.DTO.SaleDTO;
import marcos.stockcontrol.domain.controller.exception.SaleException;
import marcos.stockcontrol.domain.model.Client;
import marcos.stockcontrol.domain.model.Product;
import marcos.stockcontrol.domain.model.Sale;
import marcos.stockcontrol.domain.repository.SaleRepository;
import marcos.stockcontrol.domain.service.ClientService;
import marcos.stockcontrol.domain.service.ProductService;
import marcos.stockcontrol.domain.service.SaleService;
import marcos.stockcontrol.domain.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale create(SaleDTO saleDTO) throws SaleException {

        Sale saleToCreate = saleDTO.objectTransform();
        saleToCreate.setAmount();
        saleToCreate.setQuantity();

        for(ProductDTO productDTO: saleDTO.getProductList()) {
            if(productService.searchByName(productDTO.getName())) {
                Product productDb = productService.findById(productDTO.getId());
                if(productDb.getQuantity() >= productDTO.getQuantity()) {
                    productDb.setQuantity(productDb.getQuantity() - productDTO.getQuantity());
                    productService.update(productDb);
                } else {
                    throw new SaleException("Insufficient Stock");
                }
            } else {
                throw new SaleException("trying to register the sale of a product that does not exist.");
            }
        }
        saleToCreate.setStatus(true);
        Sale savedSale = saleRepository.save(saleToCreate);

        var supplier = clientService.findById(savedSale.getClientId());
        supplier.addSale(saleToCreate);
        clientService.update(supplier);

        return savedSale;
    }

    @Override
    public Sale showSale(Long id) {
        return saleRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
