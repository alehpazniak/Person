package by.example.person.mapper;

import by.example.person.controller.ClientResponse;
import by.example.person.domain.AddressEntity;
import by.example.person.domain.ClientEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClientResponseMapper {
    public static ClientResponse map(ClientEntity clientEntity) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setName(clientEntity.getName());
        clientResponse.setEmail(clientEntity.getEmail());
        clientResponse.setAddresses(mapToAddresses(clientEntity.getAddresses()));
        return clientResponse;
    }

    public static List<ClientResponse.AddressResponse> mapToAddresses(Collection<AddressEntity> addressEntities) {
        return addressEntities.stream()
                .map(a -> mapToAddress(a))
                .collect(Collectors.toList());
    }

    public static ClientResponse.AddressResponse mapToAddress(AddressEntity addressEntity) {
        ClientResponse.AddressResponse addressResponse = new ClientResponse.AddressResponse();
        addressResponse.setCity(addressEntity.getCity());
        addressResponse.setStreet(addressEntity.getStreet());
        addressResponse.setHouse(addressEntity.getHouse());
        addressResponse.setFlat(addressEntity.getFlat());
        return addressResponse;
    }
}
