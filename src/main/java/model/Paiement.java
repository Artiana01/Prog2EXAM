package model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Paiement {
    private Long id_paiement;
    private Date date_paiement;
}

