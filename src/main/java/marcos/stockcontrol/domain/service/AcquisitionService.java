package marcos.stockcontrol.domain.service;


import marcos.stockcontrol.domain.DTO.AcquisitionDTO;
import marcos.stockcontrol.domain.controller.exception.ProductException;
import marcos.stockcontrol.domain.model.Acquisition;


public interface AcquisitionService {

    Acquisition create(AcquisitionDTO dto) throws ProductException;

    Acquisition showAcquisition(Long id);
}
