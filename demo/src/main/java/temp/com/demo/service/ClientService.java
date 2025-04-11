package temp.com.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import temp.com.demo.model.Client;
import temp.com.demo.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> buscarPorDni(String dni) {
        return clientRepository.findByDni(dni);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
