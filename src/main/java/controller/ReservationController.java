package controller;

import model.Client;
import org.springframework.web.bind.annotation.*;
import model.Reservation;
import Service.ReservationService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RestController
public class ReservationController {
    private ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("/get/reservations")
    public List<Reservation> getAllReservations() throws SQLException {
        return service.getAllReservations();
    }

    @GetMapping("/get/reservation/{id_reservation}")
    public Reservation getReservationById_reservation(@PathVariable Long id_reservation) throws SQLException {
        return service.getReservationById(id_reservation);
    }
    @GetMapping("/get/reservation/date_reservation/{date_reservation}")
    public List<Reservation> getReservationsByDateReservation(@PathVariable String date_reservation) throws SQLException {
        return service.getReservationsByDateReservation(date_reservation);
    }
    @PostMapping("/insert/reservation")
    public Reservation insertReservation(@RequestBody Reservation reservation) throws SQLException {
        return service.insertReservation(reservation.getId_reservation(), reservation.getDate_reservation());
    }

    @PutMapping("/update/reservation/{id_reservation}")
    public Reservation updateReservation(@PathVariable Long id_reservation, @RequestBody Reservation reservation) throws SQLException {
        return service.updateReservation(id_reservation, reservation.getDate_reservation());
    }

    @DeleteMapping("/delete/reservation")
    public void deleteReservation(@RequestBody Reservation reservation) throws SQLException {
        service.deleteReservation(reservation.getId_reservation());
    }
}
