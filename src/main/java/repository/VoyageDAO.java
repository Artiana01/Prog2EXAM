package repository;

import model.Client;
import org.springframework.stereotype.Repository;
import model.Voyage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VoyageDAO implements VoyageDAOInterface {
    private Connection connection;

    public VoyageDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Voyage insert(Long id_voyage, String destination, Timestamp date_depart, Timestamp date_retour, Double prix) throws SQLException {
        Voyage voyage = new Voyage(id_voyage, destination, date_depart, date_retour, prix);
        String sql = "INSERT INTO voyage (id_voyage, destination, date_depart, date_retour, prix) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_voyage);
            preparedStatement.setString(2, destination);
            preparedStatement.setTimestamp(3, date_depart);
            preparedStatement.setTimestamp(4, date_retour);
            preparedStatement.setDouble(5, prix);
            preparedStatement.executeUpdate();
        }
        return voyage;
    }

    @Override
    public List<Voyage> getAll() throws SQLException {
        List<Voyage> allVoyages = new ArrayList<>();
        String sql = "SELECT * FROM voyage";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                convertToList(allVoyages, result);
            }
        }
        return allVoyages;
    }

    public Voyage getByDestination(String destination) throws SQLException {
        String sql = "SELECT * FROM voyage WHERE destination = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, destination);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return new Voyage(
                        result.getLong("id_voyage"),
                        result.getString("destination"),
                        result.getTimestamp("date_depart"),
                        result.getTimestamp("date_retour"),
                        result.getDouble("prix")
                );
            }
        }
        return null;
    }
    @Override
    public Voyage getById(Long id_voyage) throws SQLException {
        String sql = "SELECT * FROM voyage WHERE id_voyage = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_voyage);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return new Voyage(
                        result.getLong("id_voyage"),
                        result.getString("destination"),
                        result.getTimestamp("date_depart"),
                        result.getTimestamp("date_retour"),
                        result.getDouble("prix")
                );
            }
        }
        return null;
    }

    @Override
    public Voyage update(Long id_voyage, String destination, Timestamp date_depart, Timestamp date_retour, Double prix) throws SQLException {
        Voyage updatedVoyage = new Voyage(id_voyage, destination, date_depart, date_retour, prix);
        String sql = "UPDATE voyage SET destination = ?, date_depart = ?, date_retour = ?, prix = ? WHERE id_voyage = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, destination);
            preparedStatement.setTimestamp(2, date_depart);
            preparedStatement.setTimestamp(3, date_retour);
            preparedStatement.setDouble(4, prix);
            preparedStatement.setLong(5, id_voyage);
            preparedStatement.executeUpdate();
        }
        return updatedVoyage;
    }

    @Override
    public void delete(Long id_voyage) throws SQLException {
        String sql = "DELETE FROM voyage WHERE id_voyage = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_voyage);
            preparedStatement.executeUpdate();
        }
    }

    private void convertToList(List<Voyage> allVoyages, ResultSet result) throws SQLException {
        allVoyages.add(new Voyage(
                result.getLong("id_voyage"),
                result.getString("destination"),
                result.getTimestamp("date_depart"),
                result.getTimestamp("date_retour"),
                result.getDouble("prix")
        ));
    }
}
