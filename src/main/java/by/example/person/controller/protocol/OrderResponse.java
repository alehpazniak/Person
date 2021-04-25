package by.example.person.controller.protocol;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {
    private int id;
    private Date date;
    private List<ProductResponse> products;

    @Data
    public static class ProductResponse {
        private int id;
        private String brand;
        private String goods;
    }
}