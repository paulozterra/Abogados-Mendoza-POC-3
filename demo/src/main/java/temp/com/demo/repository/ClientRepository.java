package temp.com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import temp.com.demo.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByDni(String dni);
}
