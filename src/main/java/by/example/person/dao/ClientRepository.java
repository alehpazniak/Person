package by.example.person.dao;

import by.example.person.domain.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    List<ClientEntity> findClientByName(String name);
    ClientEntity findById(int id);
    List<ClientEntity> findAll();

    @Query(value = "select * from client_entity ce " +
            "left join address_entity ae on ae.client_id = ce.id " +
            "where city = :city",
            nativeQuery = true)
    Collection<ClientEntity> findAllClientsByCity(String city);

    Collection<ClientEntity> findAllClientsByAddressesCity(String city);

}
