package by.example.person.controller;

import by.example.person.domain.Product;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
public class OrderRequest {
    private int id;
    private Date date;
    @NotEmpty
    private List<ProductRequest> products;

    @Data
    public static class ProductRequest {
        @NotBlank
        private int id;
        private String brand;
        private String goods;
    }
}
