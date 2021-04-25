package by.example.person.mapper;

import by.example.person.controller.OrderRequest;
import by.example.person.domain.ProductEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public ProductEntity map(OrderRequest.ProductRequest productRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productRequest.getId());
        productEntity.setBrand(productRequest.getBrand());
        productEntity.setGoods(productRequest.getGoods());
        return productEntity;
    }

    public List<ProductEntity> mapToProducts(Collection<OrderRequest.ProductRequest> productRequests) {
        return productRequests.stream()
                .map(this::mapToProduct) //productRequest -> mapToProduct(productRequest)
                .collect(Collectors.toList());
    }

    public ProductEntity mapToProduct(OrderRequest.ProductRequest productRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productRequest.getId());
        productEntity.setBrand(productRequest.getBrand());
        productEntity.setGoods(productRequest.getGoods());
        return productEntity;
    }
}
