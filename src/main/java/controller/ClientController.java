package controller;

import org.springframework.web.bind.annotation.*;
import model.Client;
import Service.ClientService;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ClientController {
    private ClientService service;

    public ClientController(ClientService service) {

        this.service = service;
    }

    @GetMapping("/get/clients")
    public List<Client> getAllClients() throws SQLException {
        return service.getAllClients();
    }

    @GetMapping("/get/client/{id_client}")
    public Client getClientById_client(@PathVariable int id_client) throws SQLException {
        return service.getClientById(id_client);
    }
    @GetMapping("/get/client/nom/{nom}")
    public Client getClientByNom(@PathVariable String nom) throws SQLException {
        return service.getClientByNom(nom);
    }


    @PostMapping("/insert/client")
    public Client insertClient(@RequestBody Client client) throws SQLException {
        return service.insertClient(client.getId_client(), client.getNom(), client.getPrenom(), client.getEmail(), client.getTelephone());
    }

    @PutMapping("/update/client/{id_client}")
    public Client updateClient(@PathVariable int id_client, @RequestBody Client client) throws SQLException {
        return service.updateClient(id_client, client.getNom(), client.getPrenom(), client.getEmail(), client.getTelephone());
    }

    @DeleteMapping("/delete/client/{id_client}")
    public void deleteClient(@PathVariable int id_client) throws SQLException {
        service.deleteClient(id_client);
    }
}
