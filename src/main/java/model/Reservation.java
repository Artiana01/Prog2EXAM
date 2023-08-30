package model;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Reservation {
    private Long id_reservation;
    private Date date_reservation;
}

