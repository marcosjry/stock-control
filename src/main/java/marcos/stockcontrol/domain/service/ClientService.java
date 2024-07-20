package marcos.stockcontrol.domain.service;

import marcos.stockcontrol.domain.model.Client;

import java.util.List;

public interface ClientService {

    Client findById(Long id);

    List<Client> showClient();

    Client create(Client clientToCreate);
}
