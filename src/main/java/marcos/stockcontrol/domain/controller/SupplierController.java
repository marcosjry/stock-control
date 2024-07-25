package marcos.stockcontrol.domain.controller;


import marcos.stockcontrol.domain.model.Client;
import marcos.stockcontrol.domain.model.Supplier;
import marcos.stockcontrol.domain.service.ClientService;
import marcos.stockcontrol.domain.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;


    @GetMapping
    public ResponseEntity<List<Supplier>> listClient() {
        return ResponseEntity.accepted().body(supplierService.showSupplier());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Supplier> get(@PathVariable Long id) {
        var supplier = supplierService.findById(id);
        return ResponseEntity.ok(supplier);
    }

    @PostMapping
    public ResponseEntity<Supplier> create(@RequestBody Supplier supplierToCreate) {
        var toCreate = supplierService.create(supplierToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(toCreate.getId())
                .toUri();
        return ResponseEntity.created(location).body(toCreate);

    }


}
