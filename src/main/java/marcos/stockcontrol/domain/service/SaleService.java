package marcos.stockcontrol.domain.service;

import marcos.stockcontrol.domain.DTO.SaleDTO;
import marcos.stockcontrol.domain.controller.exception.SaleException;
import marcos.stockcontrol.domain.model.Sale;

public interface SaleService {

    Sale create(SaleDTO saleDTO) throws SaleException;

    Sale showSale(Long id);
}
