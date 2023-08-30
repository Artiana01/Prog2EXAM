package repository;

import model.Client;
import model.Reservation;
import model.ReservationJointure;
import model.Voyage;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ReservationJointureDAO implements ReservationJointureDAOInterface {
    private Connection connection;

    public ReservationJointureDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public ReservationJointure insert(Long id_reservation, Date date_reservation, Client client, Voyage voyage) throws SQLException {
        String sql = "INSERT INTO reservation (id_reservation, date_reservation, id_client, id_voyage) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_reservation);
            preparedStatement.setDate(2, new java.sql.Date(date_reservation.getTime()));
            preparedStatement.setInt(3, client.getId_client());
            preparedStatement.setLong(4, voyage.getId_voyage());

            preparedStatement.executeUpdate();
        }

        // Assuming you fetch the generated ID after the insert, if applicable.
        ReservationJointure insertedReservation = getById(id_reservation);
        return insertedReservation;
    }

    @Override
    public List<ReservationJointure> getAll() throws SQLException {
        List<ReservationJointure> allReservations = new ArrayList<>();
        String sql = "SELECT\n" +
                "    r.id_reservation,\n" +
                "    r.date_reservation,\n" +
                "    c.id_client,\n" +
                "    c.nom,\n" +
                "    c.prenom,\n" +
                "    c.email,\n" +
                "    c.telephone,\n" +
                "    v.id_voyage,\n" +
                "    v.destination,\n" +
                "    v.date_depart,\n" +
                "    v.date_retour,\n" +
                "    v.prix\n" +
                "FROM reservation r\n" +
                "JOIN client c ON r.id_client = c.id_client\n" +
                "JOIN voyage v ON r.id_voyage = v.id_voyage;\n";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                convertToList(allReservations, result);
            }
        }
        return allReservations;
    }



    @Override
    public ReservationJointure getById(Long id_reservation) throws SQLException {
        String sql = "SELECT " +
                "    r.id_reservation, " +
                "    r.date_reservation, " +
                "    c.id_client, " +
                "    c.nom, " +
                "    c.prenom, " +
                "    c.email, " +
                "    c.telephone, " +
                "    v.id_voyage, " +
                "    v.destination, " +
                "    v.date_depart, " +
                "    v.date_retour, " +
                "    v.prix " +
                "FROM reservation r " +
                "JOIN client c ON r.id_client = c.id_client " +
                "JOIN voyage v ON r.id_voyage = v.id_voyage " +
                "WHERE r.id_reservation = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_reservation);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                return convertToReservationJointure(result);
            } else {
                return null;
            }
        }
    }

    private ReservationJointure convertToReservationJointure(ResultSet result) throws SQLException {
        return new ReservationJointure(
                result.getLong("id_reservation"),
                result.getDate("date_reservation"),
                new Client(
                        result.getInt("id_client"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("telephone")
                ),
                new Voyage(
                        result.getLong("id_voyage"),
                        result.getString("destination"),
                        result.getTimestamp("date_depart"),
                        result.getTimestamp("date_retour"),
                        result.getDouble("prix")
                )
        );
    }


    @Override
    public ReservationJointure update(Long id_reservation, Date date_reservation, Client client, Voyage voyage) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id_reservation) throws SQLException {

    }

    private void convertToList(List<ReservationJointure> allReservations, ResultSet result) throws SQLException {
        ReservationJointure reservationJointure = new ReservationJointure(
                result.getLong("id_reservation"),
                result.getDate("date_reservation"),

                new Client(
                        result.getInt("id_client"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("telephone")
                ),

                new Voyage(
                        result.getLong("id_voyage"),
                        result.getString("destination"),
                        result.getTimestamp("date_depart"),
                        result.getTimestamp("date_retour"),
                        result.getDouble("prix")
                )
        );
        allReservations.add(reservationJointure);
    }

}
