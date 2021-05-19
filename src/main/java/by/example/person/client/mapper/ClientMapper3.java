package by.example.person.client.mapper;

import by.example.person.client.controller.protocol.ClientRequest;
import by.example.person.client.domain.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper3 {

    ClientMapper3 INSTANCE = Mappers.getMapper(ClientMapper3.class);

    @Mapping(target = "secondName", source = "lastName")
    @Mapping(target = "fullName", expression = "java(String.join(\" \", clientEntity.getFirstName(), clientEntity.getSecondName()))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    ClientEntity map(ClientRequest clientRequest);
}
