package model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VoyageJointure {
    private Long id_voyage;
    private String destination;
    private Timestamp date_depart;
    private Timestamp date_retour;
    private Double prix;
    private Client client; // Adding the Client reference here
}
