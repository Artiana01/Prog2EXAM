package repository;

import org.springframework.stereotype.Repository;
import model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ReservationDAO implements ReservationDAOInterface {
    private Connection connection;

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Reservation insert(Long id_reservation, Date date_reservation) throws SQLException {
        Reservation reservation = new Reservation(id_reservation, date_reservation);
        String sql = "INSERT INTO reservation (id_reservation, date_reservation) " +
                "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_reservation);
            preparedStatement.setDate(2, new java.sql.Date(date_reservation.getTime()));
            preparedStatement.executeUpdate();
        }
        return reservation;
    }

    @Override
    public List<Reservation> getAll() throws SQLException {
        List<Reservation> allReservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                convertToList(allReservations, result);
            }
        }
        return allReservations;
    }

    @Override
    public Reservation getById(Long id_reservation) throws SQLException {
        String sql = "SELECT * FROM reservation WHERE id_reservation = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_reservation);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                return new Reservation(
                        result.getLong("id_reservation"),
                        result.getDate("date_reservation")
                );
            }
        }
        return null;
    }

    @Override
    public List<Reservation> getByDateReservation(java.sql.Date date_reservation) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE date_reservation = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, date_reservation);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Reservation reservation = new Reservation(
                        result.getLong("id_reservation"),
                        result.getDate("date_reservation")
                );
                reservations.add(reservation);
            }
        }
        return reservations;
    }



    @Override
    public Reservation update(Long id_reservation, Date date_reservation) throws SQLException {
        Reservation updatedReservation = new Reservation(id_reservation, date_reservation);
        String sql = "UPDATE reservation SET date_reservation = ? WHERE id_reservation = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(date_reservation.getTime()));
            preparedStatement.setLong(2, id_reservation);
            preparedStatement.executeUpdate();
        }
        return updatedReservation;
    }

    @Override
    public void delete(Long id_reservation) throws SQLException {
        String sql = "DELETE FROM reservation WHERE id_reservation = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id_reservation);
            preparedStatement.executeUpdate();
        }
    }

    private void convertToList(List<Reservation> allReservations, ResultSet result) throws SQLException {
        allReservations.add(new Reservation(
                result.getLong("id_reservation"),
                result.getDate("date_reservation")
        ));
    }
}

