package Service;

import org.springframework.stereotype.Service;
import model.Paiement;
import repository.PaiementDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class PaiementService {
    private PaiementDAO dao;

    public PaiementService(PaiementDAO dao) {
        this.dao = dao;
    }

    public List<Paiement> getAllPaiements() throws SQLException {
        return dao.getAll();
    }

    public Paiement insertPaiement(Long id_paiement, Date date_paiement) throws SQLException {
        return dao.insert(id_paiement, date_paiement);
    }

    public Paiement getPaiementById(Long id_paiement) throws SQLException {
        Paiement paiement = dao.getById(id_paiement);
        if (paiement == null) {
            System.out.println("This ID is not registered in the database.");
        }
        return paiement;
    }

    public Paiement updatePaiement(Long id_paiement, Date date_paiement) throws SQLException {
        return dao.update(id_paiement, date_paiement);
    }

    public void deletePaiement(Long id_paiement) throws SQLException {
        dao.delete(id_paiement);
    }
}
