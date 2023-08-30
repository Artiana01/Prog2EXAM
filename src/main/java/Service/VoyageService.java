package Service;

import model.Client;
import org.springframework.stereotype.Service;
import model.Voyage;
import repository.VoyageDAO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class VoyageService {
    private VoyageDAO dao;

    public VoyageService(VoyageDAO dao) {
        this.dao = dao;
    }

    public List<Voyage> getAllVoyages() throws SQLException {
        return dao.getAll();
    }
    public Voyage insertVoyage(Long id_voyage, String destination, Timestamp date_depart, Timestamp date_retour, Double prix) throws SQLException {
        return dao.insert(id_voyage, destination, date_depart, date_retour, prix);
    }

    public Voyage getVoyageById(Long id_voyage) throws SQLException {
        Voyage voyage = dao.getById(id_voyage);
        if (voyage == null) {
            System.out.println("This ID is not registered in the database.");
        }
        return voyage;
    }
    public Voyage getVoyageByDestination(String destination) throws SQLException {
        Voyage voyage = dao.getByDestination(destination);
        if (voyage == null) {
            System.out.println("cet destination n'existe pas dans la BD");
        }
        return voyage;
    }

    public Voyage updateVoyage(Long id_voyage, String destination, Timestamp date_depart, Timestamp date_retour, Double prix) throws SQLException {
        return dao.update(id_voyage, destination, date_depart, date_retour, prix);
    }

    public void deleteVoyage(Long id_voyage) throws SQLException {
        dao.delete(id_voyage);
    }
}

