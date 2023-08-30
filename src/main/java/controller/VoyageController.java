package controller;

import org.springframework.web.bind.annotation.*;
import model.Voyage;
import Service.VoyageService;

import java.sql.SQLException;
import java.util.List;

@RestController
public class VoyageController {
    private VoyageService service;

    public VoyageController(VoyageService service) {
        this.service = service;
    }

    @GetMapping("/get/voyages")
    public List<Voyage> getAllVoyages() throws SQLException {
        return service.getAllVoyages();
    }

    @GetMapping("/get/voyage/{id_voyage}")
    public Voyage getVoyageById_voyage(@PathVariable Long id_voyage) throws SQLException {
        return service.getVoyageById(id_voyage);
    }

    @GetMapping("/get/voyage/destination/{destination}")
    public Voyage getVoyageByDestination(@PathVariable String destination) throws SQLException {
        return service.getVoyageByDestination(destination);
    }

    @PostMapping("/insert/voyage")
    public Voyage insertVoyage(@RequestBody Voyage voyage) throws SQLException {
        return service.insertVoyage(voyage.getId_voyage(), voyage.getDestination(), voyage.getDate_depart(), voyage.getDate_retour(), voyage.getPrix());
    }

    @PutMapping("/update/voyage/{id_voyage}")
    public Voyage updateVoyage(@PathVariable Long id_voyage, @RequestBody Voyage voyage) throws SQLException {
        return service.updateVoyage(id_voyage, voyage.getDestination(), voyage.getDate_depart(), voyage.getDate_retour(), voyage.getPrix());
    }

    @DeleteMapping("/delete/voyage")
    public void deleteVoyage(@RequestBody Voyage voyage) throws SQLException {
        service.deleteVoyage(voyage.getId_voyage());
    }
}
