package by.example.person.order.mapper;

import by.example.person.order.controller.protocol.OrderRequest;
import by.example.person.order.domain.OrderEntity;
import by.example.person.order.domain.ProductEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRequestMapper {
    public static OrderEntity map(OrderRequest orderRequest) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderRequest.getId());
        orderEntity.setDate(orderRequest.getDate());
        orderEntity.setProducts(mapToProducts(orderRequest.getProducts(), orderEntity));
        return orderEntity;
    }

    public static List<ProductEntity> mapToProducts(Collection<OrderRequest.ProductRequest> productRequests,
                                                    OrderEntity orderEntity) {
        return productRequests.stream()
                .map(productRequest -> mapToProduct(productRequest, orderEntity))
                .collect(Collectors.toList());
    }

    public static ProductEntity mapToProduct(OrderRequest.ProductRequest productRequest, OrderEntity orderEntity) {
        ProductEntity product = new ProductEntity();
        product.setId(productRequest.getId());
        product.setBrand(productRequest.getBrand());
        product.setGoods(productRequest.getGoods());
        product.setOrders(orderEntity);
        return product;
    }
}
