package repository;

import org.springframework.stereotype.Repository;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDAO implements ClientDAOInterface {
    private Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Client insert(int id_client, String nom, String prenom, String email, String telephone) throws SQLException {
        Client client = new Client(id_client, nom, prenom, email, telephone);
        String sql = "INSERT INTO client (id_client, nom, prenom, email, telephone) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_client);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, prenom);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, telephone);
            preparedStatement.executeUpdate();
        }
        return client;
    }

    @Override
    public List<Client> getAll() throws SQLException {
        List<Client> allClients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                convertToList(allClients, result);
            }
        }
        return allClients;
    }

    @Override
    public Client getById(int id_client) throws SQLException {
        String sql = "SELECT * FROM client WHERE id_client = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_client);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return new Client(
                        result.getInt("id_client"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("telephone")
                );
            }
        }
        return null;
    }

    @Override
    public Client getByNom(String nom) throws SQLException {
        String sql = "SELECT * FROM client WHERE nom = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return new Client(
                        result.getInt("id_client"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("telephone")
                );
            }
        }
        return null;
    }

    @Override
    public Client update(int id_client, String nom, String prenom, String email, String telephone) throws SQLException {
        Client updatedClient = new Client(id_client, nom, prenom, email, telephone);
        String sql = "UPDATE client SET nom = ?, prenom = ?, email = ?, telephone = ? WHERE id_client = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, telephone);
            preparedStatement.setInt(5, id_client);
            preparedStatement.executeUpdate();
        }
        return updatedClient;
    }

    @Override
    public void delete(int id_client) throws SQLException {
        String sql = "DELETE FROM client WHERE id_client = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_client);
            preparedStatement.executeUpdate();
        }
    }

    private void convertToList(List<Client> allClients, ResultSet result) throws SQLException {
        allClients.add(new Client(
                result.getInt("id_client"),
                result.getString("nom"),
                result.getString("prenom"),
                result.getString("email"),
                result.getString("telephone")
        ));
    }
}
