package by.example.person.client.mapper;

import by.example.person.client.controller.protocol.ClientRequest;
import by.example.person.client.controller.protocol.ClientResponse;
import by.example.person.client.domain.AddressEntity;
import by.example.person.client.domain.ClientEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "secondName", source = "lastName")
    @Mapping(target = "fullName", source = "clientRequest", qualifiedByName = "fullNameMapping")
    @Mapping(target = "id", ignore = true)
    ClientEntity map(ClientRequest clientRequest);

    @Named("fullNameMapping")
    default String mapToFullName(ClientRequest clientRequest) {
        return String.join(" ", clientRequest.getFirstName(), clientRequest.getLastName());
    }

    AddressEntity map(ClientRequest.AddressRequest addressRequest);

    @AfterMapping
    default void mapClientIdToAddress(@MappingTarget ClientEntity clientEntity) {
        clientEntity.getAddresses()
                .forEach(addressEntity -> addressEntity.setClient(clientEntity));
    }

    ClientResponse mapToResponse(ClientEntity clientEntity);

    ClientResponse.AddressResponse mapToResponse(AddressEntity addressEntity);
}
