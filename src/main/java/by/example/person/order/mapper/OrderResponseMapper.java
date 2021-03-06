package by.example.person.order.mapper;

import by.example.person.order.controller.protocol.OrderResponse;
import by.example.person.order.domain.OrderEntity;
import by.example.person.order.domain.ProductEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OrderResponseMapper {
    public static OrderResponse map(OrderEntity orderEntity) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(orderEntity.getId());
        orderResponse.setCreatedDate(orderEntity.getCreatedDate());
        orderResponse.setProducts(mapToProducts(orderEntity.getProducts()));
        return orderResponse;
    }

    public static List<OrderResponse.ProductResponse> mapToProducts(Collection<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(productEntity -> mapToProduct(productEntity))
                .collect(Collectors.toList());
    }

    public static OrderResponse.ProductResponse mapToProduct(ProductEntity productEntity) {
        OrderResponse.ProductResponse productResponse = new OrderResponse.ProductResponse();
        productResponse.setId(productEntity.getId());
        productResponse.setBrand(productEntity.getBrand());
        productResponse.setGoods(productEntity.getGoods());
        return productResponse;
    }
}
