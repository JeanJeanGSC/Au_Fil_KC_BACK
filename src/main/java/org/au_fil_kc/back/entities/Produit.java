package org.au_fil_kc.back.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Produit {
    @Id
    private String id;
    private String nom;
    private String description;

    private double prixVente;
    private double prixProd;
    private double prixRabais;

    private boolean enSolde;
    private int inventaire;
}
