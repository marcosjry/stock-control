package marcos.stockcontrol.domain.controller;

import marcos.stockcontrol.domain.model.Client;
import marcos.stockcontrol.domain.service.ClientService;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Primary
@RestController
@RequestMapping(path = "/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping
    public ResponseEntity<List<Client>> listClient() {
        return ResponseEntity.accepted().body(clientService.showClient());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> get(@PathVariable Long id) {
        var client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client clientToCreate) {
        var toCreate = clientService.create(clientToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(toCreate.getId())
                .toUri();
        return ResponseEntity.created(location).body(toCreate);

    }

}
