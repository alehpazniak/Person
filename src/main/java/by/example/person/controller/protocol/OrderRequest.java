package by.example.person.controller.protocol;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
        @NotNull
        private Integer id;
        private String brand;
        private String goods;
    }
}
