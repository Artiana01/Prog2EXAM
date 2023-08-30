package repository;

import model.VoyageJointure;

import java.sql.SQLException;
import java.util.List;

public interface VoyageJointureDAOInterface {
    VoyageJointure insert(VoyageJointure voyageJointure) throws SQLException;

    List<VoyageJointure> getAll() throws SQLException;
    List<VoyageJointure> getVoyagesJointureByDestination(String destination) throws SQLException;


    VoyageJointure getById(Long id_voyage) throws SQLException;

    VoyageJointure update(Long id_voyage, VoyageJointure voyageJointure) throws SQLException;

    void delete(Long id_voyage) throws SQLException;
}






