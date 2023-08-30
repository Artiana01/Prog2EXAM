package controller;

import org.springframework.web.bind.annotation.*;
import model.Paiement;
import Service.PaiementService;

import java.sql.SQLException;
import java.util.List;

@RestController
public class PaiementController {
    private PaiementService service;

    public PaiementController(PaiementService service) {
        this.service = service;
    }

    @GetMapping("/get/paiements")
    public List<Paiement> getAllPaiements() throws SQLException {
        return service.getAllPaiements();
    }

    @GetMapping("/get/paiement/{id_paiement}")
    public Paiement getPaiementById_paiement(@PathVariable Long id_paiement) throws SQLException {
        return service.getPaiementById(id_paiement);
    }


    @PostMapping("/insert/paiement")
    public Paiement insertPaiement(@RequestBody Paiement paiement) throws SQLException {
        return service.insertPaiement(paiement.getId_paiement(), paiement.getDate_paiement());
    }

    @PutMapping("/update/paiement/{id_paiement}")
    public Paiement updatePaiement(@PathVariable Long id_paiement, @RequestBody Paiement paiement) throws SQLException {
        return service.updatePaiement(id_paiement, paiement.getDate_paiement());
    }

    @DeleteMapping("/delete/paiement")
    public void deletePaiement(@RequestBody Paiement paiement) throws SQLException {
        service.deletePaiement(paiement.getId_paiement());
    }
}
