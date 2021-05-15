package by.example.person.order.controller.protocol;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
public class OrderRequest {
    private int id;
    private Date createdDate;
    @NotEmpty
    private List<ProductRequest> products;

    @Builder
    @Data
    public static class ProductRequest {
        private int id;
        private String brand;
        private String goods;
    }
}
