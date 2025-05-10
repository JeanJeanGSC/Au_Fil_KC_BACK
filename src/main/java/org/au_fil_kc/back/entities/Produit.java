package org.au_fil_kc.back.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    private String id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String taille;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String entretien;

    @Column(nullable = false)
    private double prixVente;

    @Column(nullable = true)
    private double prixProd;

    @Column(nullable = true)
    private double prixRabais;

    @Column(nullable = true)
    private int inventaire;

    private boolean enSolde;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;
}
