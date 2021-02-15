package by.example.person.controller;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ClientRequest {
    @Size(min = 3)
    private String name;
    @NotBlank    // не пустой
    private String email;
    @Valid
    @NotEmpty
    private List<AddressRequest> addresses;


    @Data
    public static class AddressRequest {
        @NotBlank
        private String city;
        private String street;
        private String house;
        private String flat;

    }

}
