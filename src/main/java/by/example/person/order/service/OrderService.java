package by.example.person.order.service;

import by.example.person.client.domain.ClientEntity;
import by.example.person.client.domain.ClientRepository;
import by.example.person.client.exeption.ClientNotFountException;
import by.example.person.order.controller.protocol.OrderRequest;
import by.example.person.order.controller.protocol.OrderResponse;
import by.example.person.order.mapper.OrderMapper;
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
    public List<OrderResponse> addOrderToClient(long id, OrderRequest orderRequest) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFountException("Client with id: " + id + " wasn't found"));
        clientEntity.addOrder(OrderMapper.INSTANCE.map(orderRequest));
        return clientEntity.getOrders().stream().map(OrderMapper.INSTANCE::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getClientOrders(long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFountException("Client with id: " + id + " wasn't found"));
        return clientEntity.getOrders().stream().map(OrderMapper.INSTANCE::mapToResponse)
                .collect(Collectors.toList());
    }
}
