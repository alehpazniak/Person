package by.example.person.order.mapper;

import by.example.person.order.controller.protocol.OrderRequest;
import by.example.person.order.controller.protocol.OrderResponse;
import by.example.person.order.domain.OrderEntity;
import by.example.person.order.domain.ProductEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderEntity map(OrderRequest orderRequest);

    @InheritInverseConfiguration
    OrderResponse mapToResponse(OrderEntity orderEntity);

    ProductEntity map(OrderRequest.ProductRequest productRequest);

    @AfterMapping
    default void mapOrderIdToProduct(@MappingTarget OrderEntity orderEntity) {
        orderEntity.getProducts()
                .forEach(productEntity -> productEntity.setOrders(orderEntity));
    }

    OrderResponse.ProductResponse mapToResponse(ProductEntity productEntity);
}
