package by.example.person.order.service;

import by.example.person.client.domain.ClientEntity;
import by.example.person.client.domain.ClientRepository;
import by.example.person.order.controller.protocol.OrderResponse;
import by.example.person.order.controller.protocol.OrderRequest;
import by.example.person.order.mapper.OrderRequestMapper;
import by.example.person.order.mapper.OrderResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ClientRepository clientRepository;

    @Transactional
    public List<OrderResponse> addOrderToClient(int id, OrderRequest orderRequest) {
        ClientEntity clientEntity = clientRepository.findById(id);
        clientEntity.addOrder(OrderRequestMapper.map(orderRequest));
        return clientEntity.getOrders().stream().map(OrderResponseMapper::map)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getClientOrders(int id) {
        ClientEntity clientEntity = clientRepository.findById(id);
        return clientEntity.getOrders().stream().map(OrderResponseMapper::map)
                .collect(Collectors.toList());
    }
}
