package marcos.stockcontrol.domain.controller;

import marcos.stockcontrol.domain.DTO.SaleDTO;
import marcos.stockcontrol.domain.controller.exception.SaleException;
import marcos.stockcontrol.domain.model.Sale;
import marcos.stockcontrol.domain.service.SaleService;
import marcos.stockcontrol.domain.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/sale")
public class SaleController {


        @Autowired
        private SaleService saleService;

        @Autowired
        private SupplierService supplierService;


        @PostMapping
        public ResponseEntity<Sale> create(@RequestBody SaleDTO dto) throws SaleException {
            var toCreate = saleService.create(dto);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(toCreate.getClientId())
                    .toUri();
            return ResponseEntity.created(location).body(toCreate);
        }

        @GetMapping(path = "/{id}")
        public ResponseEntity<Sale> showAcquisition(@PathVariable Long id) {
            var acquisition = saleService.showSale(id);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(acquisition.getId())
                    .toUri();
            return ResponseEntity.created(location).body(acquisition);
        }
}

