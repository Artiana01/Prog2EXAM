package controller;

import org.springframework.web.bind.annotation.*;
import model.Client;
import model.ReservationJointure;
import model.Voyage;
import Service.ReservationJointureService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RestController
public class ReservationJointureController {
    private ReservationJointureService service;

    public ReservationJointureController(ReservationJointureService service) {
        this.service = service;
    }

    @PostMapping("/insert/reservationJointure")
    public ReservationJointure insertReservation(@RequestBody ReservationJointure reservationJointure) throws SQLException {
        Long id_reservation = reservationJointure.getId_reservation();
        Date date_reservation = reservationJointure.getDate_reservation();
        Client client = reservationJointure.getClient();
        Voyage voyage = reservationJointure.getVoyage();

        return service.insertReservation(id_reservation, date_reservation, client, voyage);
    }

    @GetMapping("/get/reservationsJointures")
    public List<ReservationJointure> getAllReservationsJointures() throws SQLException {
        return service.getAllReservations();
    }

    @GetMapping("/get/reservationJointure/{id_reservation}")
    public ReservationJointure getReservationJointureById(@PathVariable Long id_reservation) throws SQLException {
        return service.getReservationById(id_reservation);
    }


}
