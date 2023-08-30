package repository;

import model.Paiement;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PaiementDAO implements PaiementDAOInterface {
    private Connection connection;

    public PaiementDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Paiement insert(Long id_paiement, Date date_paiement) throws SQLException {
        Paiement paiement = new Paiement(id_paiement, date_paiement);
        String sql = "INSERT INTO paiement (id_paiement, date_paiement) " +
                "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_paiement);
            preparedStatement.setDate(2, new java.sql.Date(date_paiement.getTime()));
            preparedStatement.executeUpdate();
        }
        return paiement;
    }

    @Override
    public List<Paiement> getAll() throws SQLException {
        List<Paiement> allPaiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                convertToList(allPaiements, result);
            }
        }
        return allPaiements;
    }

    @Override
    public Paiement getById(Long id_paiement) throws SQLException {
        String sql = "SELECT * FROM paiement WHERE id_paiement = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_paiement);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return new Paiement(
                        result.getLong("id_paiement"),
                        result.getDate("date_paiement")
                );
            }
        }
        return null;
    }

    @Override
    public Paiement update(Long id_paiement, Date date_paiement) throws SQLException {
        Paiement updatedPaiement = new Paiement(id_paiement, date_paiement);
        String sql = "UPDATE paiement SET date_paiement = ? WHERE id_paiement = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(date_paiement.getTime()));
            preparedStatement.setLong(2, id_paiement);
            preparedStatement.executeUpdate();
        }
        return updatedPaiement;
    }

    @Override
    public void delete(Long id_paiement) throws SQLException {

    }

    private void convertToList(List<Paiement> allPaiements, ResultSet result) throws SQLException {
        allPaiements.add(new Paiement(
                result.getLong("id_paiement"),
                result.getDate("date_paiement")
        ));
    }
}

