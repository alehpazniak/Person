package by.example.person.client.service;

import by.example.person.client.controller.protocol.ClientRequest;
import by.example.person.client.controller.protocol.ClientResponse;
import by.example.person.client.domain.ClientEntity;
import by.example.person.client.domain.ClientRepository;
import by.example.person.client.exeption.ClientNotFountException;
import by.example.person.client.mapper.AddressMapper;
import by.example.person.client.mapper.ClientRequestMapper;
import by.example.person.client.mapper.ClientResponseMapper;
import by.example.person.order.controller.protocol.OrderRequest;
import by.example.person.order.controller.protocol.OrderResponse;
import by.example.person.order.mapper.OrderRequestMapper;
import by.example.person.order.mapper.OrderResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientResponse> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientResponseMapper::map)
                .collect(Collectors.toList());
    }

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

    public ClientResponse saveClient(ClientRequest clientRequest) {
        ClientEntity clientEntity = clientRepository.save(ClientRequestMapper.map(clientRequest));
        return ClientResponseMapper.map(clientEntity);
    }

    public List<ClientResponse> findClientByName(String name) {
        if (name == null) {
            throw new ClientNotFountException("Client wasn't found");
        }
        return clientRepository.findClientByName(name).stream()
                .map(ClientResponseMapper::map)
                .collect(Collectors.toList());
    }

    public void deleteClientById(int id) {
        clientRepository.deleteById(id);
    }

    public ClientResponse updateClient(int id, ClientRequest clientRequest) {
        ClientEntity clientEntity = clientRepository.findById(id);
        clientEntity.setName(clientRequest.getName());
        clientEntity.setEmail(clientRequest.getEmail());
        clientRepository.save(clientEntity);
        return ClientResponseMapper.map(clientEntity);
    }

    @Transactional
    public ClientResponse saveAddress(int id, ClientRequest.AddressRequest addressRequest) {
        ClientEntity clientEntity = clientRepository.findById(id);
        clientEntity.addAddress(AddressMapper.map(addressRequest));
        return ClientResponseMapper.map(clientEntity);
    }

    public List<ClientResponse> findAddressesByCity(String city) {
        return clientRepository.findAllClientsByCity(city).stream()
                .map(ClientResponseMapper::map)
                .collect(Collectors.toList());
    }

    public ClientResponse findClientById(int id) {
        ClientEntity clientEntity = clientRepository.findById(id);
        if (clientEntity == null) {
            throw new ClientNotFountException("Client with id: " + id + " wasn't found");
        }
        return ClientResponseMapper.map(clientEntity);
    }

    public List<ClientResponse> findClientByProduct(String goods) {
        return clientRepository.findAllClientsByProduct(goods).stream()
                .map(ClientResponseMapper::map)
                .collect(Collectors.toList());
    }
}
