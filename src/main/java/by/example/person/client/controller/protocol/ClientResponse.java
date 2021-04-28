package by.example.person.client.controller.protocol;

import lombok.Data;

import java.util.List;

@Data
public class ClientResponse {
    private String name;
    private String email;
    private List<AddressResponse> addresses;

    @Data
    public static class AddressResponse {
        private String city;
        private String street;
        private String house;
        private String flat;
    }
}
