package repository;

import model.Paiement;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface PaiementDAOInterface {
    Paiement insert(Long id_paiement, Date date_paiement) throws SQLException;

    List<Paiement> getAll() throws SQLException;

    Paiement getById(Long id_paiement) throws SQLException;

    Paiement update(Long id_paiement, Date date_paiement) throws SQLException;

    void delete(Long id_paiement) throws SQLException;
}
