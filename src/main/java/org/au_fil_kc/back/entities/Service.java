package org.au_fil_kc.back.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Service {
    @Id
    private String id;
    private String nom;
    private String description;
    private double prix;

    //Prix a l'heure ou pour le service entier ?
}
