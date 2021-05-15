package by.example.person.client.controller.protocol;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Data
public class ClientRequest {
    @Size(min = 3)
    private String firstName;
    @Size(min = 3)
    private String lastName;
    private int age;
    private String phoneNumber;
    @Email
    private String email;
    @Valid
    @NotEmpty
    private List<AddressRequest> addresses;

    @Builder
    @Data
    public static class AddressRequest {
        @NotBlank
        private String city;
        private String street;
        private String house;
        private String flat;
    }
}
