package by.example.person.client.controller;

import by.example.person.client.controller.protocol.ClientRequest;
import by.example.person.client.controller.protocol.ClientResponse;
import by.example.person.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientResponse> getClients() {
        return clientService.findAll();
    }

    @GetMapping("/{name}")
    public List<ClientResponse> getClientsByName(@PathVariable(value = "name") String clientName) {
        return clientService.findClientByName(clientName);
    }

    @GetMapping("/id/{id}")
    public ClientResponse getClientById(@PathVariable(value = "id") int id) {
        return clientService.findClientById(id);
    }

    @GetMapping("/address/{city}")
    public List<ClientResponse> getAddressByCity(@PathVariable(value = "city") String city) {
        return clientService.findAddressesByCity(city);
    }

    @GetMapping("/product/{goods}")
    public List<ClientResponse> getClientByProduct(
            @PathVariable(value = "goods") String goods) {
        return clientService.findClientByProduct(goods);
    }


    @PostMapping
    public ClientResponse addClient(@Valid @RequestBody ClientRequest clientRequest) {
        return clientService.saveClient(clientRequest);
    }

    @PostMapping("/{id}/address")
    public ClientResponse addAddressToClient(@PathVariable(value = "id") int id,
                                             @RequestBody ClientRequest.AddressRequest address) {
        return clientService.saveAddress(id, address);
    }



    @PutMapping("/{id}")
    public ClientResponse updateClient(@PathVariable(value = "id") int id,
                                       @RequestBody ClientRequest client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable(value = "id") int id) {
        clientService.deleteClientById(id);
    }
}
