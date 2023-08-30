package repository;

import model.Client;
import model.ReservationJointure;
import model.Voyage;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ReservationJointureDAOInterface {
    ReservationJointure insert(Long id_reservation, Date date_reservation, Client client, Voyage voyage) throws SQLException;

    List<ReservationJointure> getAll() throws SQLException;

    ReservationJointure getById(Long id_reservation) throws SQLException;

    ReservationJointure update(Long id_reservation, Date date_reservation, Client client, Voyage voyage) throws SQLException;

    void delete(Long id_reservation) throws SQLException;
}
