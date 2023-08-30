package Service;

import model.Client;
import org.springframework.stereotype.Service;
import model.Reservation;
import repository.ReservationDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ReservationService {
    private ReservationDAO dao;

    public ReservationService(ReservationDAO dao) {
        this.dao = dao;
    }

    public List<Reservation> getAllReservations() throws SQLException {
        return dao.getAll();
    }

    public Reservation insertReservation(Long id_reservation, Date date_reservation) throws SQLException {
        return dao.insert(id_reservation, date_reservation);
    }

    public Reservation getReservationById(Long id_reservation) throws SQLException {
        Reservation reservation = dao.getById(id_reservation);
        if (reservation == null) {
            System.out.println("This ID is not registered in the database.");
        }
        return reservation;
    }
    public List<Reservation> getReservationsByDateReservation(String date_reservation) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;
        try {
            java.util.Date utilDate = dateFormat.parse(date_reservation);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dao.getByDateReservation(sqlDate);
    }


    public Reservation updateReservation(Long id_reservation, Date date_reservation) throws SQLException {
        return dao.update(id_reservation, date_reservation);
    }

    public void deleteReservation(Long id_reservation) throws SQLException {
        dao.delete(id_reservation);
    }



}
