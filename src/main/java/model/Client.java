package model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Client {
    private int id_client;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
}
