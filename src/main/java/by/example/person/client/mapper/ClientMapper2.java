package by.example.person.client.mapper;

import by.example.person.client.controller.protocol.ClientRequest;
import by.example.person.client.domain.ClientEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper2 {

    ClientMapper2 INSTANCE = Mappers.getMapper(ClientMapper2.class);

    @Mapping(target = "secondName", source = "lastName")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    ClientEntity map(ClientRequest clientRequest);

    @AfterMapping
    default void mapToFullName(@MappingTarget ClientEntity clientEntity) {
        clientEntity.setFullName(String.join(" ", clientEntity.getFirstName(), clientEntity.getSecondName()));
    }
}
