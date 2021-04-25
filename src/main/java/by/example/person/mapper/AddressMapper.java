package by.example.person.mapper;

import by.example.person.controller.protocol.ClientRequest;
import by.example.person.controller.protocol.ClientResponse;
import by.example.person.domain.AddressEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AddressMapper {

    public static AddressEntity map(ClientRequest.AddressRequest addressRequest) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(addressRequest.getCity());
        addressEntity.setStreet(addressRequest.getStreet());
        addressEntity.setHouse(addressRequest.getHouse());
        addressEntity.setFlat(addressRequest.getFlat());
        return addressEntity;
    }

    public static List<AddressEntity> mapToAddresses(Collection<ClientRequest.AddressRequest> addressRequest) {
        return addressRequest.stream()
                .map(a -> mapToAddress(a))
                .collect(Collectors.toList());
    }

    public static AddressEntity mapToAddress(ClientRequest.AddressRequest addressRequest) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(addressRequest.getCity());
        addressEntity.setStreet(addressRequest.getStreet());
        addressEntity.setHouse(addressRequest.getHouse());
        addressEntity.setFlat(addressRequest.getFlat());
        return addressEntity;
    }

    public static ClientResponse.AddressResponse mapToAddressResponse(AddressEntity addressEntity) {
        ClientResponse.AddressResponse addressResponse = new ClientResponse.AddressResponse();
        addressResponse.setCity(addressEntity.getCity());
        addressResponse.setStreet(addressEntity.getStreet());
        addressResponse.setHouse(addressEntity.getHouse());
        addressResponse.setFlat(addressEntity.getFlat());
        return addressResponse;
    }
}
