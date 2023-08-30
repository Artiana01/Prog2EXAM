package controller;

import org.springframework.web.bind.annotation.*;
import model.VoyageJointure;
import Service.VoyageJointureService;

import java.sql.SQLException;
import java.util.List;

@RestController
public class VoyageJointureController {
    private VoyageJointureService service;

    public VoyageJointureController(VoyageJointureService service) {
        this.service = service;
    }

    @GetMapping("/get/voyageJointures")
    public List<VoyageJointure> getAllVoyagesJointures() throws SQLException {
        return service.getAll();
    }

    @GetMapping("/get/voyageJointure/{id_voyage}")
    public VoyageJointure getVoyageJointureById(@PathVariable Long id_voyage) throws SQLException {
        return service.getVoyageJointureById(id_voyage);
    }
    @GetMapping("/get/voyageJointure/destination/{destination}")
    public List<VoyageJointure> getVoyageJointureByDestination(@PathVariable String destination) throws SQLException {
        return service.getVoyagesJointureByDestination(destination);
    }
    @PostMapping("/insert/voyageJointure")
    public VoyageJointure insertVoyageJointure(@RequestBody VoyageJointure voyageJointure) throws SQLException {
        return service.insertVoyageJointure(voyageJointure);
    }

    @PutMapping("/update/voyageJointure/{id_voyage}")
    public VoyageJointure updateVoyageJointure(@PathVariable Long id_voyage, @RequestBody VoyageJointure voyageJointure) throws SQLException {
        return service.updateVoyageJointure(id_voyage, voyageJointure);
    }

    @DeleteMapping("/delete/voyageJointure/{id_voyage}")
    public void deleteVoyageJointure(@PathVariable Long id_voyage) throws SQLException {
        service.deleteVoyageJointure(id_voyage);
    }
}

