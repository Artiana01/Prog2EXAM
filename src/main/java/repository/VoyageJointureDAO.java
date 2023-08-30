package repository;

import model.Client;
import model.Voyage;
import model.VoyageJointure;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VoyageJointureDAO implements VoyageJointureDAOInterface {
    private Connection connection;

    public VoyageJointureDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public VoyageJointure insert(VoyageJointure voyageJointure) throws SQLException {
        String sql = "INSERT INTO voyage (id_voyage, destination, date_depart, date_retour, prix, id_client) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, voyageJointure.getId_voyage());
            preparedStatement.setString(2, voyageJointure.getDestination());
            preparedStatement.setTimestamp(3, voyageJointure.getDate_depart());
            preparedStatement.setTimestamp(4, voyageJointure.getDate_retour());
            preparedStatement.setDouble(5, voyageJointure.getPrix());
            preparedStatement.setInt(6, voyageJointure.getClient().getId_client());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                voyageJointure.setId_voyage(generatedKeys.getLong(1));
            }
        }

        return voyageJointure;
    }

    @Override
    public List<VoyageJointure> getAll() throws SQLException {
        List<VoyageJointure> allVoyagesJointures = new ArrayList<>();
        String sql = "SELECT * FROM voyage " +
                "JOIN client ON voyage.id_client = client.id_client";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                convertToList(allVoyagesJointures, result);
            }
        }

        return allVoyagesJointures;
    }
    @Override
    public List<VoyageJointure> getVoyagesJointureByDestination(String destination) throws SQLException {
        List<VoyageJointure> voyagesJointures = new ArrayList<>();
        String sql = "SELECT * FROM voyage " +
                "JOIN client ON voyage.id_client = client.id_client " +
                "WHERE destination = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, destination);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                convertToList(voyagesJointures, result);
            }
        }

        return voyagesJointures;
    }

    @Override
    public VoyageJointure getById(Long id_voyage) throws SQLException {
        String sql = "SELECT * FROM voyage " +
                "JOIN client ON voyage.id_client = client.id_client " +
                "WHERE id_voyage = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_voyage);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                return convertToVoyageJointure(result);
            } else {
                return null;
            }
        }
    }

    @Override
    public VoyageJointure update(Long id_voyage, VoyageJointure voyageJointure) throws SQLException {
        String sql = "UPDATE voyage SET destination = ?, date_depart = ?, date_retour = ?, prix = ?, id_client = ? WHERE id_voyage = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, voyageJointure.getDestination());
            preparedStatement.setTimestamp(2, voyageJointure.getDate_depart());
            preparedStatement.setTimestamp(3, voyageJointure.getDate_retour());
            preparedStatement.setDouble(4, voyageJointure.getPrix());
            preparedStatement.setInt(5, voyageJointure.getClient().getId_client());
            preparedStatement.setLong(6, id_voyage);

            preparedStatement.executeUpdate();
        }

        return voyageJointure;
    }

    @Override
    public void delete(Long id_voyage) throws SQLException {
        String sql = "DELETE FROM voyage WHERE id_voyage = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_voyage);
            preparedStatement.executeUpdate();
        }
    }

    private void convertToList(List<VoyageJointure> allVoyagesJointures, ResultSet result) throws SQLException {
        VoyageJointure voyageJointure = new VoyageJointure(
                result.getLong("id_voyage"),
                result.getString("destination"),
                result.getTimestamp("date_depart"),
                result.getTimestamp("date_retour"),
                result.getDouble("prix"),
                new Client(
                        result.getInt("id_client"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("telephone")
                )
        );
        allVoyagesJointures.add(voyageJointure);
    }

    private VoyageJointure convertToVoyageJointure(ResultSet result) throws SQLException {
        return new VoyageJointure(
                result.getLong("id_voyage"),
                result.getString("destination"),
                result.getTimestamp("date_depart"),
                result.getTimestamp("date_retour"),
                result.getDouble("prix"),
                new Client(
                        result.getInt("id_client"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("telephone")
                )
        );
    }
}
