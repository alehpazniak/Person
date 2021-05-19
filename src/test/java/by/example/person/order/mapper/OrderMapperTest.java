package by.example.person.order.mapper;

import by.example.person.order.controller.protocol.OrderRequest;
import by.example.person.order.controller.protocol.OrderResponse;
import by.example.person.order.domain.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderMapperTest {
    private OrderRequest.ProductRequest createProductRequest;

    @Test
    void shouldMapToProductEntity() {
        //given
        OrderRequest.ProductRequest productRequest = createProductRequest(Fields.BRAND, Fields.GOODS);
        //when
        ProductEntity productEntity = OrderMapper.INSTANCE.map(productRequest);
        //then
        assertEquals(Fields.BRAND, productEntity.getBrand());
        assertEquals(Fields.GOODS, productEntity.getGoods());
    }

    private OrderRequest.ProductRequest createProductRequest(String brand, String goods) {
        return OrderRequest.ProductRequest.builder()
                .brand(brand)
                .goods(goods)
                .build();
    }

    @Test
    void shouldMapToProductResponse() {
        //given
        ProductEntity productEntity = createProductProductEntity(Fields.BRAND, Fields.GOODS);
        //when
        OrderResponse.ProductResponse productResponse = OrderMapper.INSTANCE.mapToResponse(productEntity);
        //then
        assertEquals(Fields.BRAND, productResponse.getBrand());
        assertEquals(Fields.GOODS, productResponse.getGoods());
    }

    private ProductEntity createProductProductEntity(String brand, String goods) {
        return ProductEntity.builder()
                .brand(brand)
                .goods(goods)
                .build();
    }

    static class Fields {
        private static final String BRAND = "Adidas";
        private static final String GOODS = "Buty";
    }
}