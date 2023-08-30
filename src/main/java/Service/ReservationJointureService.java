package Service;

import org.springframework.stereotype.Service;
import model.Client;
import model.ReservationJointure;
import model.Voyage;
import repository.ReservationJointureDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class ReservationJointureService {
    private ReservationJointureDAO dao;

    public ReservationJointureService(ReservationJointureDAO dao) {
        this.dao = dao;
    }

    public ReservationJointure insertReservation(Long id_reservation, Date date_reservation, Client client, Voyage voyage) throws SQLException {
        return dao.insert(id_reservation, date_reservation, client, voyage);
    }

    public List<ReservationJointure> getAllReservations() throws SQLException {
        return dao.getAll();
    }

    public ReservationJointure getReservationById(Long id_reservation) throws SQLException {
        return dao.getById(id_reservation);
    }


}
