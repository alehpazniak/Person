package by.example.person.client.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    List<ClientEntity> findClientByFirstName(String name);

    @Query(value = "select * from clients c " +
            "left join addresses a on a.client_id = c.id " +
            "where city = :city",
            nativeQuery = true)
    Collection<ClientEntity> findAllClientsByCity(String city);

    @Query(value = "select * from clients " +
            "left join orders on orders.client_id = clients.id " +
            "left join product on product.orders_id = orders.id " +
            "where product.goods = :goods",
            nativeQuery = true)
    Collection<ClientEntity> findAllClientsByProduct(String goods);

}
