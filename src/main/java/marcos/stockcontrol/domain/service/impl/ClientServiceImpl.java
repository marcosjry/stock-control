package marcos.stockcontrol.domain.service.impl;

import marcos.stockcontrol.domain.model.Client;
import marcos.stockcontrol.domain.repository.ClientRepository;
import marcos.stockcontrol.domain.service.ClientService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Client> showClient() {
        return clientRepository.findAll();
    }

    @Override
    public Client create(Client clientToCreate) {
        if(
                clientRepository.existsByCpf(clientToCreate.getCpf()) ||
                clientRepository.existsByEmail(clientToCreate.getEmail()) ||
                clientRepository.existsByPhone(clientToCreate.getPhone())) {

            throw new IllegalArgumentException("This Client already exists on our System.");

        }
        var date = LocalDateTime.now();
        clientToCreate.setDate(date);
        return clientRepository.save(clientToCreate);
    }
}
