package by.example.person.client.mapper;

import by.example.person.client.controller.protocol.ClientRequest;
import by.example.person.client.domain.AddressEntity;
import by.example.person.client.domain.ClientEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRequestMapper {
    public static ClientEntity map(ClientRequest clientRequest) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(clientRequest.getName());
        clientEntity.setEmail(clientRequest.getEmail());
        clientEntity.setAddresses(mapToAddresses(clientRequest.getAddresses(), clientEntity));
        return clientEntity;
    }

    private static List<AddressEntity> mapToAddresses(Collection<ClientRequest.AddressRequest> addressesRequest,
                                                      ClientEntity clientEntity) {
        return addressesRequest.stream()
                .map(a -> mapToAddress(a, clientEntity))
                .collect(Collectors.toList());
    }

    private static AddressEntity mapToAddress(ClientRequest.AddressRequest addressRequest, ClientEntity clientEntity) {
        AddressEntity address = new AddressEntity();
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        address.setHouse(addressRequest.getHouse());
        address.setFlat(addressRequest.getFlat());
        address.setClient(clientEntity);
        return address;
    }
}
