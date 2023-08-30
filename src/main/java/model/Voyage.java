package model;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Voyage {
    private Long id_voyage;
    private String destination;
    private Timestamp date_depart;
    private Timestamp date_retour;
    private Double prix;
}
