package by.example.person.client.mapper;

import by.example.person.client.controller.protocol.ClientRequest;
import by.example.person.client.domain.AddressEntity;
import by.example.person.client.domain.ClientEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

class ClientMapperTest {

    @Test
    void shouldMapByQualifiedByName() {
        //given
        ClientRequest clientRequest = createClientRequest(Fields.FIRST_NAME, Fields.SECOND_NAME);

        //when
        ClientEntity clientEntity = ClientMapper.INSTANCE.map(clientRequest);

        //then
        assertEquals(Fields.FIRST_NAME, clientEntity.getFirstName());
        assertEquals(Fields.SECOND_NAME, clientEntity.getSecondName());
        assertEquals(Fields.FIRST_NAME + " " + Fields.SECOND_NAME, clientEntity.getFullName());
    }

    @Test
    void shouldMapByAfterMapping() {
        //given
        ClientRequest clientRequest = createClientRequest(Fields.FIRST_NAME, Fields.SECOND_NAME);

        //when
        ClientEntity clientEntity = ClientMapper2.INSTANCE.map(clientRequest);

        //then
        assertEquals(Fields.FIRST_NAME, clientEntity.getFirstName());
        assertEquals(Fields.SECOND_NAME, clientEntity.getSecondName());
        assertEquals(Fields.FIRST_NAME + " " + Fields.SECOND_NAME, clientEntity.getFullName());
    }

    @Test
    void shouldMapByExpression() {
        //given
        ClientRequest clientRequest = createClientRequest(Fields.FIRST_NAME, Fields.SECOND_NAME);

        //when
        ClientEntity clientEntity = ClientMapper3.INSTANCE.map(clientRequest);

        //then
        assertEquals(Fields.FIRST_NAME, clientEntity.getFirstName());
        assertEquals(Fields.SECOND_NAME, clientEntity.getSecondName());
        assertEquals(Fields.FIRST_NAME + " " + Fields.SECOND_NAME, clientEntity.getFullName());
    }

    @Test
    void shouldMapToAddressEntity() {
        //given
        ClientRequest.AddressRequest addressRequest = createAddressRequest(
                Fields.CITY, Fields.STREET, Fields.HOUSE, Fields.FLAT);
        //when
        AddressEntity addressEntity = ClientMapper.INSTANCE.map(addressRequest);
        //then
        assertEquals(Fields.CITY, addressEntity.getCity());
        assertEquals(Fields.STREET, addressEntity.getStreet());
        assertEquals(Fields.HOUSE, addressEntity.getHouse());
        assertEquals(Fields.FLAT, addressEntity.getFlat());
    }

    private ClientRequest createClientRequest(String firstName, String secondName) {
        return ClientRequest.builder()
                .firstName(firstName)
                .lastName(secondName)
                .build();
    }

    private ClientRequest.AddressRequest createAddressRequest (
            String city, String street, String house, String flat) {
        return ClientRequest.AddressRequest.builder()
                .city(city)
                .street(street)
                .house(house)
                .flat(flat)
                .build();
    }

    static class Fields {
        private static final String FIRST_NAME = "first_name_1";
        private static final String SECOND_NAME = "second_name_1";
        private static final String CITY = "city";
        private static final String STREET = "city";
        private static final String HOUSE = "city";
        private static final String FLAT = "city";

    }
}