package marcos.stockcontrol.domain.controller;

import marcos.stockcontrol.domain.DTO.AcquisitionDTO;
import marcos.stockcontrol.domain.controller.exception.ProductException;
import marcos.stockcontrol.domain.model.Acquisition;
import marcos.stockcontrol.domain.service.AcquisitionService;
import marcos.stockcontrol.domain.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(path = "/acquisition")
public class AcquisitionController {

    private final AcquisitionService acquisitionService;
    private final ClientService clientService;

    public AcquisitionController(AcquisitionService acquisitionService, ClientService clientService) {
        this.acquisitionService = acquisitionService;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Acquisition> create(@RequestBody AcquisitionDTO dto) throws ProductException {
        var toCreate = acquisitionService.create(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(toCreate.getClientId())
                .toUri();
        return ResponseEntity.created(location).body(toCreate);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Acquisition> showAcquisition(@PathVariable Long id) {
        var acquisition = acquisitionService.showAcquisition(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(acquisition.getId())
                .toUri();
        return ResponseEntity.created(location).body(acquisition);
    }
}
