package by.example.person.order.controller.protocol;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {
    private int id;
    private Date createdDate;
    private List<ProductResponse> products;

    @Data
    public static class ProductResponse {
        private int id;
        private String brand;
        private String goods;
    }
}
