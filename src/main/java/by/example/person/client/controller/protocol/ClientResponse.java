package by.example.person.client.controller.protocol;

import lombok.Data;

import java.util.List;

@Data
public class ClientResponse {
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
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
