package marcos.stockcontrol.domain.service.impl;

import marcos.stockcontrol.domain.DTO.AcquisitionDTO;
import marcos.stockcontrol.domain.DTO.ProductDTO;
import marcos.stockcontrol.domain.controller.exception.ProductException;
import marcos.stockcontrol.domain.model.Acquisition;
import marcos.stockcontrol.domain.model.Client;
import marcos.stockcontrol.domain.model.Product;
import marcos.stockcontrol.domain.model.PurchasedProduct;
import marcos.stockcontrol.domain.repository.AcquisitionRepository;
import marcos.stockcontrol.domain.repository.ProductRepository;
import marcos.stockcontrol.domain.service.AcquisitionService;
import marcos.stockcontrol.domain.service.ClientService;
import marcos.stockcontrol.domain.service.ProductService;
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
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Override
    public Acquisition create(AcquisitionDTO dto) throws ProductException {
        Acquisition acquisition = new Acquisition();
        acquisition.setClientId(dto.getClientId());
        acquisition.setDate(LocalDateTime.now());

        List<PurchasedProduct> purchasedProducts = new ArrayList<>();

        for (ProductDTO productDTO : dto.getProductList()) {
            Product existingProduct = productService.findById(productDTO.getId());
            if(productDTO.getQuantity() <= existingProduct.getQuantity()) {

                // Cria um novo produto para a aquisição com a quantidade comprada
                PurchasedProduct purchasedProduct = new PurchasedProduct();
                purchasedProduct.setProductId(existingProduct.getId());
                purchasedProduct.setName(existingProduct.getName());
                purchasedProduct.setDescription(existingProduct.getDescription());
                purchasedProduct.setQuantity(productDTO.getQuantity());
                purchasedProduct.setPrice(existingProduct.getPrice());

                purchasedProducts.add(purchasedProduct);

                // Atualizar a quantidade no estoque
                existingProduct.setQuantity(existingProduct.getQuantity() - productDTO.getQuantity());
                productService.update(existingProduct);
            } else {
                throw new ProductException();
            }
        }
        acquisition.setStatus(true);
        acquisition.setProducts(purchasedProducts);
        acquisition.setAmount();
        acquisition.setQuantity();

        // Salva a aquisição no repositório
        Acquisition savedAcquisition = acquisitionRepository.save(acquisition);
        var client = clientService.findById(acquisition.getClientId());
        client.addAcquisition(acquisition);
        clientService.update(client);

        // Retorna a aquisição salva
        return savedAcquisition;
    }


    @Override
    public Acquisition showAcquisition(Long id) {
        return acquisitionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}




