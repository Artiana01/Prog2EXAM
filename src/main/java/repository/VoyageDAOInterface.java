package repository;

import model.Voyage;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface VoyageDAOInterface {
    Voyage insert(Long id_voyage, String destination, Timestamp date_depart, Timestamp date_retour, Double prix) throws SQLException;

    List<Voyage> getAll() throws SQLException;

    Voyage getById(Long id_voyage) throws SQLException;

    Voyage update(Long id_voyage, String destination, Timestamp date_depart, Timestamp date_retour, Double prix) throws SQLException;

    void delete(Long id_voyage) throws SQLException;
}
