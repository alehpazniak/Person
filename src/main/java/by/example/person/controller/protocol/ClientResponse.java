package by.example.person.controller.protocol;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
