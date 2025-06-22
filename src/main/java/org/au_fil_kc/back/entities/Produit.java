package org.au_fil_kc.back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "produit", schema = "public")
public class Produit {
    public Produit() { }
    public Produit(String id, String nom, String taille, String description, String entretien, double prixVente,
                   double prixProd, double prixRabais, int inventaire, boolean enSolde, List<PhotoProduit> photos) {
        this.id = id;
        this.nom = nom;
        this.taille = taille;
        this.description = description;
        this.entretien = entretien;
        this.prixVente = prixVente;
        this.prixProd = prixProd;
        this.prixRabais = prixRabais;
        this.inventaire = inventaire;
        this.enSolde = enSolde;
        this.photos = photos;
    }

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

    @Column(name = "prix_vente", nullable = false)
    private double prixVente;

    @Column(name = "prix_prod")
    private double prixProd;

    @Column(name = "prix_rabais")
    private double prixRabais;

    private int inventaire;

    @Column(name = "en_solde")
    private boolean enSolde;

    @JsonIgnore
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotoProduit> photos;

    @Override
    public String toString() {
        return "Produit{" +
                "enSolde=" + enSolde +
                ", inventaire=" + inventaire +
                ", prixRabais=" + prixRabais +
                ", prixProd=" + prixProd +
                ", prixVente=" + prixVente +
                ", entretien='" + entretien + '\'' +
                ", description='" + description + '\'' +
                ", taille='" + taille + '\'' +
                ", nom='" + nom + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTaille() {
        return taille;
    }
    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntretien() {
        return entretien;
    }
    public void setEntretien(String entretien) {
        this.entretien = entretien;
    }

    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public double getPrixProd() {
        return prixProd;
    }
    public void setPrixProd(double prixProd) {
        this.prixProd = prixProd;
    }

    public double getPrixRabais() {
        return prixRabais;
    }
    public void setPrixRabais(double prixRabais) {
        this.prixRabais = prixRabais;
    }

    public int getInventaire() {
        return inventaire;
    }
    public void setInventaire(int inventaire) {
        this.inventaire = inventaire;
    }

    public boolean isEnSolde() {
        return enSolde;
    }
    public void setEnSolde(boolean enSolde) {
        this.enSolde = enSolde;
    }

    public List<PhotoProduit> getPhotos() {
        return photos;
    }
    public void setPhotos(List<PhotoProduit> photos) {
        this.photos = photos;
    }
}
