package Service;


import org.springframework.stereotype.Service;
import model.Client;
import repository.ClientDAO;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClientService {
    private ClientDAO dao;

    public ClientService(ClientDAO dao) {
        this.dao = dao;
    }

    public List<Client> getAllClients() throws SQLException {
        return dao.getAll();
    }

    public Client insertClient(int id_client, String nom, String prenom, String email, String telephone) throws SQLException {
        return dao.insert(id_client, nom, prenom, email, telephone);
    }

    public Client getClientById(int id_client) throws SQLException {
        Client client = dao.getById(id_client);
        if (client == null) {
            System.out.println("cet ID n est pas dans la BD");
        }
        return client;
    }

    public Client getClientByNom(String nom) throws SQLException {
        Client client = dao.getByNom(nom);
        if (client == null) {
            System.out.println("ce 'nom' n est pas dans la BD");
        }
        return client;
    }


    public Client updateClient(int id_client, String nom, String prenom, String email, String telephone) throws SQLException {
        return dao.update(id_client, nom, prenom, email, telephone);
    }

    public void deleteClient(int id_client) throws SQLException {
        dao.delete(id_client);
    }
}
