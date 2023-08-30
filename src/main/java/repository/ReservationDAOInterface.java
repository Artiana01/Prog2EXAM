package repository;

import model.Client;
import model.Reservation;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ReservationDAOInterface {
    Reservation insert(Long id_reservation, Date date_reservation) throws SQLException;

    List<Reservation> getAll() throws SQLException;

    Reservation getById(Long id_reservation) throws SQLException;
    List<Reservation> getByDateReservation(java.sql.Date date_reservation) throws SQLException;

    Reservation update(Long id_reservation, Date date_reservation) throws SQLException;

    void delete(Long id_reservation) throws SQLException;
}
