package marcos.stockcontrol.domain.service.impl;

import marcos.stockcontrol.domain.model.Acquisition;
import marcos.stockcontrol.domain.model.Client;
import marcos.stockcontrol.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientService implements marcos.stockcontrol.domain.service.ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
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
        clientToCreate.setDate(LocalDateTime.now());
        return clientRepository.save(clientToCreate);
    }

    @Override
    public Client update(Client clientToUpdate) {
        return clientRepository.save(clientToUpdate);
    }


}
