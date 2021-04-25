package by.example.person.controller;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {
    private int id;
    private Date date;
    @NotEmpty
    private List<ProductResponse> products;

    @Data
    public static class ProductResponse {
        @NotBlank
        private int id;
        private String brand;
        private String goods;
    }
}
