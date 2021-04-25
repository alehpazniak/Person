package by.example.person.dao;

import by.example.person.domain.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    List<ClientEntity> findClientByName(String name);
    ClientEntity findById(int id);
    List<ClientEntity> findAll();

    @Query(value = "select * from clients " +
            "left join addresses a on a.client_id = clients.id " +
            "where city = :city",
            nativeQuery = true)
    Collection<ClientEntity> findAllClientsByCity(String city);

    @Query(value = "select * from clients " +
            "left join orders on orders.client_id = clients.id " +
            "left join product on product.orders_id = orders.id " +
            "where product.goods = :goods",
            nativeQuery = true)
    Collection<ClientEntity> findAllClientsByProduct(String goods);

    Collection<ClientEntity> findAllClientsByAddressesCity(String city);

}
