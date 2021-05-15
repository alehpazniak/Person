package by.example.person.client.service;

import by.example.person.client.controller.protocol.ClientRequest;
import by.example.person.client.controller.protocol.ClientResponse;
import by.example.person.client.domain.ClientEntity;
import by.example.person.client.domain.ClientRepository;
import by.example.person.client.exeption.ClientNotFountException;
import by.example.person.client.mapper.ClientMapper;
import by.example.person.order.controller.protocol.OrderResponse;
import by.example.person.order.mapper.OrderResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientResponse> findAll() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .map(ClientMapper.INSTANCE::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getClientOrders(long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFountException("Client with id: " + id + " wasn't found"));
        return clientEntity.getOrders().stream().map(OrderResponseMapper::map)
                .collect(Collectors.toList());
    }

    public ClientResponse saveClient(ClientRequest clientRequest) {
        ClientEntity clientEntity = clientRepository.save(ClientMapper.INSTANCE.map(clientRequest));
        return ClientMapper.INSTANCE.mapToResponse(clientEntity);
    }

    public List<ClientResponse> findClientByName(String name) {
        if (name == null) {
            throw new ClientNotFountException("Client wasn't found");
        }
        return clientRepository.findClientByFirstName(name).stream()
                .map(ClientMapper.INSTANCE::mapToResponse)
                .collect(Collectors.toList());
    }

    public void deleteClientById(long id) {
        clientRepository.deleteById(id);
    }

    public ClientResponse updateClient(long id, ClientRequest clientRequest) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFountException("Client with id: " + id + " wasn't found"));
        clientEntity.setFirstName(clientRequest.getFirstName());
        clientEntity.setEmail(clientRequest.getEmail());
        clientRepository.save(clientEntity);
        return ClientMapper.INSTANCE.mapToResponse(clientEntity);
    }

    @Transactional
    public ClientResponse saveAddress(long id, ClientRequest.AddressRequest addressRequest) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFountException("Client with id: " + id + " wasn't found"));
        clientEntity.addAddress(ClientMapper.INSTANCE.map(addressRequest));
        return ClientMapper.INSTANCE.mapToResponse(clientEntity);
    }

    public List<ClientResponse> findAddressesByCity(String city) {
        return clientRepository.findAllClientsByCity(city).stream()
                .map(ClientMapper.INSTANCE::mapToResponse)
                .collect(Collectors.toList());
    }

    public ClientResponse findClientById(long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFountException("Client with id: " + id + " wasn't found"));
        return ClientMapper.INSTANCE.mapToResponse(clientEntity);
    }

    public List<ClientResponse> findClientByProduct(String goods) {
        return clientRepository.findAllClientsByProduct(goods).stream()
                .map(ClientMapper.INSTANCE::mapToResponse)
                .collect(Collectors.toList());
    }
}
