package by.example.person.controller;

import by.example.person.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ClientResponse> getClients() {
        return clientService.findAll();
    }

    @GetMapping("/name/{name}")
    public List<ClientResponse> getClientsByName(@PathVariable(value = "name") String clientName) {
        return clientService.findClientByName(clientName);
    }

    @GetMapping("/id/{id}")
    public ClientResponse getClientById(@PathVariable (value = "id") int id) {
        return clientService.findClientById(id);
    }

    @GetMapping("/address/{city}")
    public List<ClientResponse> getAddressByCity(@PathVariable(value = "city") String city) {
        return clientService.findAddressesByCity(city);
    }

//    @GetMapping("/popularCity")
//    public String GetMostPopularCity() {
//        return clientService.getMostPopularCity();
//    }

    @PostMapping
    public ClientResponse addClient(@Valid @RequestBody ClientRequest clientRequest) {
        return clientService.saveClient(clientRequest);
    }

    @PostMapping("/clients/{id}/address")
    public ClientResponse addAddressToClient(
            @PathVariable(value = "id") int id,
            @RequestBody ClientRequest.AddressRequest address) {
        return clientService.saveAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable(value = "id") int id) {
        clientService.deleteClientById(id);
    }

    @PutMapping("/{id}")
    public ClientResponse updateClient(@PathVariable(value = "id") int id,
                                       @RequestBody ClientRequest client) {
        return clientService.updateClient(id, client);
    }
}
