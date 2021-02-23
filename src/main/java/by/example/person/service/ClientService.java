package by.example.person.service;

import by.example.person.controller.ClientRequest;
import by.example.person.controller.ClientResponse;
import by.example.person.dao.ClientRepository;
import by.example.person.domain.ClientEntity;
import by.example.person.exeption.ClientNotFountException;
import by.example.person.mapper.AddressMapper;
import by.example.person.mapper.ClientRequestMapper;
import by.example.person.mapper.ClientResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientResponse> findAll() {
        return clientRepository.findAll().stream()
                .map(clientEntity -> ClientResponseMapper.map(clientEntity))
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
                .map(ClientResponseMapper::map)     //clientEntity -> ClientResponseMapper.map(clientEntity)
                .collect(Collectors.toList());
    }

    public void deleteClientById(int id) {
        clientRepository.deleteById(id);
    }

    public ClientResponse updateClient(int id, ClientRequest clientRequest) {
        ClientEntity clientEntity = clientRepository.findById(id);
        clientEntity.setName(clientRequest.getName());
        clientEntity.setEmail(clientEntity.getEmail());
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
                .map(clientEntity -> ClientResponseMapper.map(clientEntity))
                //              .filter(address -> address.getCity().equals(city))
                .collect(Collectors.toList());
//        return clientRepository.findAll().stream()
//                .map(clientEntity -> ClientResponseMapper.map(clientEntity))
//                .flatMap(clientResponse -> clientResponse.getAddresses().stream())
//                .filter(address -> address.getCity().equals(city))
//                .collect(Collectors.toList());
    }

    public ClientResponse findClientById(int id) {
        ClientEntity clientEntity = clientRepository.findById(id);
        if (clientEntity == null) {
            throw new ClientNotFountException("Client with id: " + id + " wasn't found");
        }
        return ClientResponseMapper.map(clientEntity);
    }
}
