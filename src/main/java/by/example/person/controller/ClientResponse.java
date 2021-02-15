package by.example.person.controller;

import by.example.person.domain.AddressEntity;
import lombok.Data;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ClientResponse {
    @Size(min = 3)
    private String name;
    @NotBlank    // не пустой
    private String email;
    @Valid
    @NotEmpty
    private List<AddressResponse> addresses;


    @Data
    public static class AddressResponse{
        @NotBlank
        private String city;
        private String street;
        private String house;
        private String flat;

    }
}
