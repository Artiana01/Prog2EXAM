package Service;

import model.VoyageJointure;
import org.springframework.stereotype.Service;
import repository.VoyageJointureDAO;

import java.sql.SQLException;
import java.util.List;

@Service
public class VoyageJointureService {
    private VoyageJointureDAO dao;

    public VoyageJointureService(VoyageJointureDAO dao) {
        this.dao = dao;
    }

    public List<VoyageJointure> getAll() throws SQLException {
        return dao.getAll();
    }

    public VoyageJointure getVoyageJointureById(Long id_voyage) throws SQLException {
        return dao.getById(id_voyage);
    }



    public List<VoyageJointure> getVoyagesJointureByDestination(String destination) throws SQLException {
        return dao.getVoyagesJointureByDestination(destination);
    }
    public VoyageJointure insertVoyageJointure(VoyageJointure voyageJointure) throws SQLException {
        return dao.insert(voyageJointure);
    }

    public VoyageJointure updateVoyageJointure(Long id_voyage, VoyageJointure voyageJointure) throws SQLException {
        return dao.update(id_voyage, voyageJointure);
    }

    public void deleteVoyageJointure(Long id_voyage) throws SQLException {
        dao.delete(id_voyage);
    }
}

