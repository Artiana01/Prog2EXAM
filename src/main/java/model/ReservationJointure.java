package model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class ReservationJointure {
    private Long id_reservation;
    private Date date_reservation;
    private Client client;
    private Voyage voyage;

}
