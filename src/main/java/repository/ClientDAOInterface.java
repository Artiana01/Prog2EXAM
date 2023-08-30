package repository;

import model.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAOInterface {
    Client insert(int id_client, String nom, String prenom, String email, String telephone) throws SQLException;

    List<Client> getAll() throws SQLException;

    Client getById(int id_client) throws SQLException;

    Client getByNom(String nom) throws SQLException;

    Client update(int id_client, String nom, String prenom, String email, String telephone) throws SQLException;

    void delete(int id_client) throws SQLException;
}
